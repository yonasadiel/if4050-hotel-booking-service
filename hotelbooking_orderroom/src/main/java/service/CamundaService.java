package service;

import model.Booking;
import model.BookingCamunda;
import model.Container;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CamundaService {
    public String BASE_URL = "http://127.0.0.1:8080/";

    @POST("engine-rest/process-definition/key/order-room/start")
    Call<Void> orderRoom(@Body Container<BookingCamunda> booking);
}
