package cancelbooking;

import com.google.gson.Gson;
import model.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import service.BookingService;
import service.CamundaService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.io.IOException;
import java.util.Date;

@WebService
public class CancelBookingTask {
    private BookingService bookingService;
    private CamundaService camundaService;

    @WebMethod
    public void cancelBooking(@WebParam(name = "booking") Booking booking, @WebParam(name = "refundAccount") String refundAccount) {
        initService();
        try {
            camundaService.cancelBooking(
                    new Container<>(
                            new CancelBookingCamunda(
                                    booking.id,
                                    refundAccount
                            )
                    )
            ).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initService() {
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

    public Booking retrieveBookingData(int bookingId) throws IOException {
        initService();
        return bookingService.getBookingById(bookingId).execute().body();
    }

    public boolean checkCheckinTime(Date checkIn) {
        initService();
        Date now = new Date();
        return now.before(checkIn);
    }

    public void sendFailStatus(Booking booking) {
        // STUB
    }

    public void setBookingStatusAsCancelled(int bookingId) throws IOException {
        initService();
        BookingStatusChangeRequest bookingStatusChangeRequest = new BookingStatusChangeRequest();
        bookingStatusChangeRequest.status = BookingStatus.CANCELLED;
        bookingService.setBookingStatus(bookingId, bookingStatusChangeRequest).execute();
    }

    public void refundPayment(String refundAccount) {
        // will be implemented after payment gateway works
    }

    public void sendSuccessStatus(Booking booking) {
        // STUB
    }
}
