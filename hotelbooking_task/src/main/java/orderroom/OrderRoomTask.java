package orderroom;

import com.google.gson.Gson;
import model.Booking;
import model.Guest;
import model.Room;
import payphone.easypay.core.ws.PaymentService;
import payphone.easypay.core.ws.PaymentService_Service;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import service.BookingService;
import service.GuestService;
import service.RoomService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class OrderRoomTask {

    private GuestService guestService;
    private BookingService bookingService;
    private RoomService roomService;

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
        if (roomService == null) {
            roomService = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .baseUrl(RoomService.BASE_URL)
                    .build()
                    .create(RoomService.class);
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

    public String sendPaymentInformation(String paymentName, int roomType) throws IOException {
        List<Room> rooms = roomService.getRoomByType(roomType).execute().body();
        Room room = rooms != null ? rooms.get(0) : null;
        PaymentService service = new PaymentService_Service().getPayment();
        String paymentId = null;
        if (room != null) {
            paymentId = service.beginPayment(paymentName, new BigDecimal(room.roomType.price));
        }
        return paymentId;
    }
}
