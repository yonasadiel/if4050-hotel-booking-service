package booking.service;

import booking.model.Guest;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GuestService {
    public String BASE_URL = "";

    @POST("api/guest/?format=json")
    Guest createGuest(@Body Guest request);

    @GET("api/guest/identity_number/{identity_number}/?format=json")
    Guest getGuestByIdCard(@Path("identity_number") String identityNumber);
}
