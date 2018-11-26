package payment;

import com.google.gson.Gson;
import model.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import service.BookingService;
import service.GuestService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.io.IOException;

@WebService
public class PaymentTask {

    private GuestService guestService;
    private BookingService bookingService;

    @WebMethod
    public BookingDetail confirmPayment(@WebParam(name = "paymentConfirmationRequest") PaymentConfirmationRequest request) throws IOException {
        // STUB will always be run as confirmed
        confirmBookingStatus(request.bookingId);
        Booking booking = bookingService.getBookingById(request.bookingId).execute().body();
        Guest guest = guestService.getGuestById(booking.guestId).execute().body();

        confirmBookingStatus(booking.id);
        sendReceipt(booking.id, guest.id);

        return new BookingDetail(booking, guest);
    }

    public Booking retrieveBookingStatus(int bookingId) throws IOException {
        initService();
        return bookingService.getBookingById(bookingId).execute().body();
    }

    private void initService() {
        if (bookingService == null) {
            bookingService = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .baseUrl(BookingService.BASE_URL)
                    .build()
                    .create(BookingService.class);
        }
    }

    public void confirmBookingStatus(int bookingId) throws IOException {
        initService();
        BookingStatusChangeRequest bookingStatusChangeRequest = new BookingStatusChangeRequest();
        bookingStatusChangeRequest.status = BookingStatus.PAID;
        bookingService.setBookingStatus(bookingId, bookingStatusChangeRequest).execute();
    }

    public void sendReceipt(int bookingId, int guestId) {
        // STUB
    }
}
