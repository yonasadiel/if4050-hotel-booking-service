package service;

import model.Booking;
import model.BookingStatus;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BookingService {
    public String BASE_URL = "";

    @POST("api/booking/?format=json")
    Booking createBooking(@Body Booking booking);

    @GET("api/booking/{booking_id}")
    Booking getBookingById(@Path("booking_id") int bookingId);

    @POST("api/booking/{booking_id}")
    Booking updateBookingStatus(@Path("booking_id") int bookingId, @Body BookingStatus bookingStatus);
}
