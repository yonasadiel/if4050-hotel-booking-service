package model;

import com.google.gson.annotations.SerializedName;

public class Booking {
    public int id;
    @SerializedName("payment_name")
    public String paymentName;
    @SerializedName("payment_type")
    public String paymentType;
    @SerializedName("payment_id")
    public String paymentId;
    @SerializedName("room_type")
    public int typeRoom;
    @SerializedName("check_in")
    public String checkIn;
    @SerializedName("check_out")
    public String checkOut;
    @SerializedName("guest_id")
    public int guestId;
    public String status;

    public Booking(String paymentName, String paymentType, int typeRoom, String checkIn, String checkOut) {
        this.paymentName = paymentName;
        this.paymentType = paymentType;
        this.typeRoom = typeRoom;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
}