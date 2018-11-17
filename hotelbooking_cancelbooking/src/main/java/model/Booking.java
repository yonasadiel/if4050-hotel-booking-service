package model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Booking {
    public int id;
    @SerializedName("payment_name")
    public String paymentName;
    @SerializedName("payment_type")
    public String paymentType;
    public long price;
    @SerializedName("room_type")
    public String typeRoom;
    @SerializedName("check_in")
    public Date checkIn;
    @SerializedName("check_out")
    public Date checkOut;
    @SerializedName("guest_id")
    public int guestId;

    public Booking(String paymentName, String paymentType, long price, String typeRoom, Date checkIn, Date checkOut) {
        this.paymentName = paymentName;
        this.paymentType = paymentType;
        this.price = price;
        this.typeRoom = typeRoom;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
}