package booking;

import com.google.gson.Gson;
import okhttp3.ResponseBody;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@WebService
public class BookingService {

    @WebMethod
    public boolean orderRoom() {
//        System.out.println(name);
//        if (checkOut.before(checkIn)) {
//            return false;
//        }
//        try {
//            int guestId = getGuestId(idCard);
//            if (guestId != -1) {
//                insertBookingData(guestId, roomType, checkIn, checkOut);
//            } else {
//                guestId = createNewGuest(name, idCard, email);
//                insertBookingData(guestId, roomType, checkIn, checkOut);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }

//        try {
//            createNewGuest("gery", "gery", "gery@123.com");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GuestService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();

        GuestService service = retrofit.create(GuestService.class);
        Call<Void> call = service.insertUser(new GuestRequest("gery", "gery", "gery@123.com", "020202"));
        call.enqueue(new Callback<Void>() {
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            public void onFailure(Call<Void> call, Throwable throwable) {

            }
        });
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
        GuestRequest request = new GuestRequest(name, idCard, email, "02020202");
        HttpPost httpPost = new HttpPost("http://aeb3010f.ngrok.io/api/guest/?format=json");

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
