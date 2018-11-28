package service;

import model.Room;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface RoomService {
    public String BASE_URL = "";

    @GET("api/room/type/{room_type}")
    Call<List<Room>> getRoomByType(@Path("room_type") int roomType);
}
