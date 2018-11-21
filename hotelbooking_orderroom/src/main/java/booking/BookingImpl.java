package booking;

import model.*;
import okhttp3.HttpUrl;
import service.BookingService;
import service.CamundaService;
import service.GuestService;
import com.google.gson.Gson;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.io.IOException;

@WebService
public class BookingImpl {

    private GuestService guestService;
    private BookingService bookingService;
    private CamundaService camundaService;

    @WebMethod
    public BookingDetail orderRoom(@WebParam(name = "booking") Booking booking, @WebParam(name = "guest") Guest guest) {
        initService();
        try {
            camundaService.orderRoom(
                    new Container<>(
                            new BookingCamunda(
                                    booking.paymentType,
                                    booking.paymentName,
                                    booking.typeRoom,
                                    booking.checkIn,
                                    booking.checkOut,
                                    guest.identityNumber,
                                    guest.mobilePhone,
                                    guest.name,
                                    guest.email
                            )
                    )
            ).execute().body();
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

    public Booking createBookingData(Booking booking) {
        initService();
        try {
            return bookingService.createBooking(booking).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getGuestId(String idCardNumber) {
        initService();
        Guest guest;
        try {
            guest = guestService.getGuestByIdCard(idCardNumber).execute().body();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return guest.id;
    }

    public Guest createGuest(Guest guest) {
        initService();
        try {
            return guestService.createGuest(guest).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
