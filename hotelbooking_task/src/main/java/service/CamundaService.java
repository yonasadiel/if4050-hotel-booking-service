package service;

import model.CancelBookingCamunda;
import model.Container;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CamundaService {
    public String BASE_URL = "http://127.0.0.1:8006/";

    @POST("engine-rest/process-definition/key/cancel-booking/start")
    Call<Void> orderRoom(@Body Container<CancelBookingCamunda> booking);
}
