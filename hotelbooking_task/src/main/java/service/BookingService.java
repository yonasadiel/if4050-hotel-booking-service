package service;

import model.Booking;
import model.BookingStatus;
import model.BookingStatusChangeRequest;
import retrofit2.http.*;

public interface BookingService {
    public String BASE_URL = "";

    @POST("api/booking/?format=json")
    Booking createBooking(@Body Booking booking);

    @GET("api/booking/{booking_id}")
    Booking getBookingById(@Path("booking_id") int bookingId);

    @PUT("api/booking/{booking_id}/?format=json")
    Booking setBookingStatus(@Path("booking_id") int bookingId, @Body BookingStatusChangeRequest status);
}
