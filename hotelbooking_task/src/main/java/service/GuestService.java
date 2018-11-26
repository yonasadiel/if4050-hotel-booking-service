package service;

import model.Guest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GuestService {
    public String BASE_URL = "http://localhost:8002/";

    @POST("api/guest/?format=json")
    Call<Guest> createGuest(@Body Guest request);

    @GET("api/guest/identity_number/{identity_number}/?format=json")
    Call<Guest> getGuestByIdCard(@Path("identity_number") String identityNumber);

    @GET("api/guest/{guest_id}/?format=json")
    Call<Guest> getGuestById(@Path("guest_id") int guestId);
}
