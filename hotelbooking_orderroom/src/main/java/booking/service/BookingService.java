package booking.service;

import booking.model.Booking;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BookingService {
    public String BASE_URL = "";

    @POST("api/booking/?format=json")
    Booking createBooking(@Body Booking booking);
}
