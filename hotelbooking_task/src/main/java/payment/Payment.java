package payment;

import com.google.gson.Gson;
import model.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import service.BookingService;
import service.CamundaService;
import service.GuestService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.io.IOException;

@WebService
public class Payment {

    private CamundaService camundaService;

    @WebMethod
    public BookingDetail confirmPayment(@WebParam(name = "paymentConfirmationRequest") PaymentConfirmationRequest request) throws IOException {
//        // STUB will always be run as confirmed
//        confirmBookingStatus(request.bookingId);
//        Booking booking = bookingService.getBookingById(request.bookingId).execute().body();
//        Guest guest = guestService.getGuestById(booking.guestId).execute().body();
//
//        confirmBookingStatus(booking.id);
//        sendReceipt(booking.id, guest.id);
        initService();
        try {
            camundaService.paymentConfirmation(new Container<>(new PaymentConfirmationCamunda(request.bookingId))).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new BookingDetail(null, null);
    }

    private void initService() {
        if (camundaService == null) {
            camundaService = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .baseUrl(CamundaService.BASE_URL)
                    .build()
                    .create(CamundaService.class);
        }
    }
}
