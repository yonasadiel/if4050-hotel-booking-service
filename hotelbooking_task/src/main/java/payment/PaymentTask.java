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
public class PaymentTask {

    private GuestService guestService;
    private BookingService bookingService;
    private CamundaService camundaService;

    @WebMethod
    public BookingDetail confirmPayment(@WebParam(name = "paymentConfirmationRequest") PaymentConfirmationRequest request) {
        // STUB will always be run as confirmed
        confirmBookingStatus(request.bookingId);
        Booking booking = bookingService.getBookingById(request.bookingId);
        Guest guest = guestService.getGuestById(booking.guestId);

        confirmBookingStatus(booking.id);
        sendReceipt(booking.id, guest.id);

        try {
            camundaService.paymentConfirmation(new Container<>(new PaymentConfirmationCamunda(request.bookingId))).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new BookingDetail(booking, guest);
    }

    private void initService() {
        if (guestService == null) {
            guestService = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .baseUrl(GuestService.BASE_URL)
                    .build()
                    .create(GuestService.class);
        }

        if (bookingService == null) {
            bookingService = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .baseUrl(BookingService.BASE_URL)
                    .build()
                    .create(BookingService.class);
        }
        if (camundaService == null) {
            camundaService = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .baseUrl(CamundaService.BASE_URL)
                    .build()
                    .create(CamundaService.class);
        }
    }

    @WebMethod
    public void confirmBookingStatus(int bookingId) {
        // STUB
    }

    @WebMethod
    public void sendReceipt(int bookingId, int guestId) {
        // STUB
    }
}
