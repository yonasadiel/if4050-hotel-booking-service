package booking;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

@WebService
public class Booking {

    @WebMethod
    public boolean orderRoom(String name, String idCard, String email, int roomType, Date checkIn, Date checkOut) {
        if (checkOut.before(checkIn)) {
            return false;
        }
        try {
            int guestId = getGuestId(idCard);
            if (guestId != -1) {
                insertBookingData(guestId, roomType, checkIn, checkOut);
            } else {
                guestId = createNewGuest(name, idCard, email);
                insertBookingData(guestId, roomType, checkIn, checkOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void sendBookingDetail() {
        // Send booking detail to Customer (Blackbox)
    }

    private void insertBookingData(int guestId, int roomType, Date checkin, Date checkOut) throws IOException {
        BookingRequest request = new BookingRequest(guestId, checkin, checkOut, "", "", roomType);
        HttpPost httpPost = new HttpPost("localhost:8001/api/booking/");

        StringEntity entity = new StringEntity(new Gson().toJson(request, BookingRequest.class));
        entity.setContentType("application/json");
        httpPost.setEntity(entity);

        HttpClient client = new DefaultHttpClient();
        client.execute(httpPost);
    }

    private int getGuestId(String idCard) throws IOException {
        HttpGet httpGet = new HttpGet("localhost:8002/api/guest/identity_number/" + idCard);
        HttpClient client = new DefaultHttpClient();
        HttpResponse httpResponse = client.execute(httpGet);

        if (httpResponse.getStatusLine().getStatusCode() == 400) {
            return -1;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
        String line = "";
        StringBuilder responseBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            responseBuilder.append(line);
        }

        String response = responseBuilder.toString();
        GuestResponse guestResponse = new Gson().fromJson(response, GuestResponse.class);
        return guestResponse.getId();
    }

    private int createNewGuest(String name, String idCard, String email) throws IOException {
        GuestRequest request = new GuestRequest(name, idCard, email, "");
        HttpPost httpPost = new HttpPost("localhost:8002/api/guest/");

        StringEntity entity = new StringEntity(new Gson().toJson(request, GuestRequest.class));
        entity.setContentType("application/json");
        httpPost.setEntity(entity);

        HttpClient client = new DefaultHttpClient();
        HttpResponse httpResponse = client.execute(httpPost);

        BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
        String line = "";
        StringBuilder responseBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            responseBuilder.append(line);
        }

        String response = responseBuilder.toString();
        GuestResponse guestResponse = new Gson().fromJson(response, GuestResponse.class);
        return guestResponse.getId();
    }
}
