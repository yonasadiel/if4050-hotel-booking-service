package cancelbooking;

import com.google.gson.Gson;
import model.Booking;
import model.BookingStatus;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import service.BookingService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Date;

@WebService
public class CancelBookingImpl {

    private BookingService bookingService;

    @WebMethod
    public void cancelBooking(@WebParam(name = "booking") Booking booking) {
        initService();
        Date now = new Date();
        if (now.after(booking.checkIn)) {
            sendFailStatus(booking);
        } else {
            boolean status  = setBookingStatusAsCancelled(booking.id);
            refundPayment(booking.id);
            sendSuccessStatus(booking);
        }
    }

    private void initService() {
        if (bookingService == null) {
            bookingService = new Retrofit.Builder()
                    .baseUrl(BookingService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .build()
                    .create(BookingService.class);
        }
    }

    @WebMethod
    public void sendFailStatus(@WebParam(name = "booking") Booking booking) {
        // STUB
    }

    @WebMethod
    public boolean setBookingStatusAsCancelled(@WebParam(name = "bookingId") int bookingId) {
        boolean status;
        try {
            Booking booking = bookingService.updateBookingStatus(bookingId, new BookingStatus(BookingStatus.CANCELLED));
            status = true;
        } catch (Exception e) {
            status = false;
        }
        return status;
    }

    @WebMethod
    public void refundPayment(@WebParam(name = "bookingId") int bookingId) {
        // STUB
    }

    @WebMethod
    public void sendSuccessStatus(@WebParam(name = "booking") Booking booking) {
        // STUB
    }
}
