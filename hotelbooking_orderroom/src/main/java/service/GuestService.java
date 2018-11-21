package service;

import model.Guest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GuestService {
    public String BASE_URL = "http://127.0.0.1:8002/";

    @POST("api/guest/?format=json")
    Call<Guest> createGuest(@Body Guest request);

    @GET("api/guest/identity_number/{identity_number}/?format=json")
    Call<Guest> getGuestByIdCard(@Path("identity_number") String identityNumber);
}
