package booking;

import model.Booking;
import model.BookingDetail;
import model.Guest;
import service.BookingService;
import service.GuestService;
import com.google.gson.Gson;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@WebService
public class BookingImpl {

    private GuestService guestService;
    private BookingService bookingService;

    @WebMethod
    public BookingDetail orderRoom(@WebParam(name = "booking") Booking booking, @WebParam(name = "guest") Guest guest) {
        initService();
        if (booking.checkOut.before(booking.checkIn)) {
            return new BookingDetail(booking, guest);
        }
        int guestId = getGuestId(guest.identityNumber);
        if (guestId != -1) {
            booking.guestId = guestId;
            booking = createBookingData(booking);
        } else {
            Guest newGuest = createGuest(guest);
            booking.guestId = newGuest.id;
            booking = createBookingData(booking);
        }
        return new BookingDetail(booking, guest);
    }

    private void initService() {
        if (guestService != null) {
            guestService = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .baseUrl(GuestService.BASE_URL)
                    .build()
                    .create(GuestService.class);
        }

        if (bookingService != null) {
            bookingService = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .baseUrl(BookingService.BASE_URL)
                    .build()
                    .create(BookingService.class);
        }
    }

    @WebMethod
    public Booking createBookingData(@WebParam(name = "booking") Booking booking) {
        initService();
        return bookingService.createBooking(booking);
    }

    @WebMethod
    private int getGuestId(@WebParam(name = "id_card_number") String idCardNumber) {
        initService();
        Guest guest = guestService.getGuestByIdCard(idCardNumber);
        return guest.id;
    }

    @WebMethod
    private Guest createGuest(@WebParam(name = "guest") Guest guest) {
        initService();
        return guestService.createGuest(guest);
    }
}
