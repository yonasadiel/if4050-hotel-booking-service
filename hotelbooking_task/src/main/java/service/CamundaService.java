package service;

import model.CancelBookingCamunda;
import model.Container;
import model.OrderRoomCamunda;
import model.PaymentConfirmationCamunda;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CamundaService {
    public String BASE_URL = "http://167.205.35.224:8080/";

    @POST("engine-rest/process-definition/key/cancel-booking/start")
    Call<Void> cancelBooking(@Body Container<CancelBookingCamunda> cancelBookingRequest);

    @POST("engine-rest/process-definition/key/order-room/start")
    Call<Void> orderRoom(@Body Container<OrderRoomCamunda> orderRoomRequest);

    @POST("engine-rest/process-definition/key/payment-confirmation/start")
    Call<Void> paymentConfirmation(@Body Container<PaymentConfirmationCamunda> paymentConfirmationRequest);
}
