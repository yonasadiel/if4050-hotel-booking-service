package service;

import model.Booking;
import model.BookingStatusChangeRequest;
import retrofit2.http.*;

public interface BookingService {
    public String BASE_URL = "http://127.0.0.1:8001/";

    @GET("api/booking/{booking_id}/?format=json")
    Booking getBookingById(@Path("booking_id") int bookingId);

    @PUT("api/booking/{booking_id}/?format=json")
    Booking setBookingStatus(@Path("booking_id") int bookingId, @Body BookingStatusChangeRequest status);
}
