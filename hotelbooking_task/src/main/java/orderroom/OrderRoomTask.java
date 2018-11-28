package orderroom;

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
public class OrderRoomTask {

    private GuestService guestService;
    private BookingService bookingService;
    private CamundaService camundaService;

    @WebMethod
    public BookingDetail orderRoom(@WebParam(name = "booking") Booking booking, @WebParam(name = "guest") Guest guest) {
        initService();
        try {
            camundaService.orderRoom(new Container<>(new OrderRoomCamunda(booking, guest))).execute().body();
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
    public Booking createBookingData(@WebParam(name = "booking") Booking booking) {
        initService();
        return bookingService.createBooking(booking);
    }

    @WebMethod
    public int getGuestId(@WebParam(name = "id_card_number") String idCardNumber) {
        initService();
        try {
            Guest guest = guestService.getGuestByIdCard(idCardNumber);
            return guest.id;
        } catch (Exception e) {
            return -1;
        }
    }

    @WebMethod
    public Guest createGuest(@WebParam(name = "guest") Guest guest) {
        initService();
        return guestService.createGuest(guest);
    }
}
