package booking;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class BookingRequest {
    @SerializedName("guestId")
    private int guestId;

    @SerializedName("check_in")
    private Date checkIn;

    @SerializedName("check_out")
    private Date checkOut;

    @SerializedName("payment_name")
    private String paymentName;

    @SerializedName("payment_type")
    private String paymentType;

    @SerializedName("room_type")
    private int roomType;

    public BookingRequest(int guestId, Date checkIn, Date checkOut, String paymentName, String paymentType, int roomType) {
        this.guestId = guestId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.paymentName = paymentName;
        this.paymentType = paymentType;
        this.roomType = roomType;
    }

    public int getGuestId() {
        return guestId;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public int getRoomType() {
        return roomType;
    }
}
