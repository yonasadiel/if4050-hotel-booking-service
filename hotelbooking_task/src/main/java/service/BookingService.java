package service;

import model.Booking;
import model.BookingStatus;
import model.BookingStatusChangeRequest;
import retrofit2.Call;
import retrofit2.http.*;

public interface BookingService {
    public String BASE_URL = "http://167.205.35.224:8001/";

    @POST("api/booking/?format=json")
    Call<Booking> createBooking(@Body Booking booking);

    @GET("api/booking/{booking_id}")
    Call<Booking> getBookingById(@Path("booking_id") int bookingId);

    @PUT("api/booking/{booking_id}/?format=json")
    Call<Booking> setBookingStatus(@Path("booking_id") int bookingId, @Body BookingStatusChangeRequest status);
}
