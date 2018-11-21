package service;

import model.Booking;
import model.BookingCamunda;
import model.Container;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BookingService {
    public String BASE_URL = "http://127.0.0.1:8001/";

    @POST("api/booking/?format=json")
    Call<Booking> createBooking(@Body Booking booking);
}
