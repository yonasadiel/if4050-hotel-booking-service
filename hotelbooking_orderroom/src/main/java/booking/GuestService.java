package booking;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.POST;

interface GuestService {
    String BASE_URL = "http://aeb3010f.ngrok.io/";

    @POST("api/guest/?format=json")
    Call<Void> insertUser(@Body GuestRequest request);
}
