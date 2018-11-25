package model;

import com.google.gson.annotations.SerializedName;

public class Booking {
    public int id;
    @SerializedName("payment_name")
    public String paymentName;
    @SerializedName("payment_type")
    public String paymentType;
    public long price;
    @SerializedName("room_type")
    public int typeRoom;
    @SerializedName("check_in")
    public String checkIn;
    @SerializedName("check_out")
    public String checkOut;
    @SerializedName("guest_id")
    public int guestId;

    public Booking(String paymentName, String paymentType, long price, int typeRoom, String checkIn, String checkOut) {
        this.paymentName = paymentName;
        this.paymentType = paymentType;
        this.price = price;
        this.typeRoom = typeRoom;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
}