package payment;

import com.google.gson.Gson;
import model.Booking;
import model.BookingStatus;
import model.BookingStatusChangeRequest;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import service.BookingService;
import service.GuestService;

import java.io.IOException;

public class PaymentTask {

    private GuestService guestService;
    private BookingService bookingService;

    public PaymentTask() {
        initService();
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
    }

    public Booking retrieveBookingStatus(int bookingId) throws IOException {
        initService();
        return bookingService.getBookingById(bookingId).execute().body();
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
