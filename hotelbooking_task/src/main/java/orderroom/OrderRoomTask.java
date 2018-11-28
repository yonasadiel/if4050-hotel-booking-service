package orderroom;

import com.google.gson.Gson;
import model.Booking;
import model.Guest;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import service.BookingService;
import service.GuestService;

import java.io.IOException;

public class OrderRoomTask {

    private GuestService guestService;
    private BookingService bookingService;

    public OrderRoomTask() {
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

    public Booking createBookingData(Booking booking) throws IOException {
        initService();
        return bookingService.createBooking(booking).execute().body();
    }

    public int getGuestId(String idCardNumber) {
        initService();
        try {
            Guest guest = guestService.getGuestByIdCard(idCardNumber).execute().body();
            return guest.id;
        } catch (Exception e) {
            return -1;
        }
    }

    public Guest createGuest(Guest guest) throws IOException {
        initService();
        return guestService.createGuest(guest).execute().body();
    }
}
