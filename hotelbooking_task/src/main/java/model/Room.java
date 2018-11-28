package model;

import com.google.gson.annotations.SerializedName;

public class Room {
    @SerializedName("room_number")
    public int roomNumber;
    @SerializedName("type_room")
    public RoomType roomType;

    public Room(int roomNumber, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
    }
}
