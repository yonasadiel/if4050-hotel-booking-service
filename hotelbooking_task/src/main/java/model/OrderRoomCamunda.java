package model;

import com.google.gson.annotations.SerializedName;

public class OrderRoomCamunda extends CamundaModel {
    @SerializedName("payment_name")
    CamundaStringObject paymentName;
    @SerializedName("payment_type")
    CamundaStringObject paymentType;
    @SerializedName("room_type")
    CamundaStringObject roomType;
    @SerializedName("check_in")
    CamundaStringObject checkIn;
    @SerializedName("check_out")
    CamundaStringObject checkOut;
    @SerializedName("identity_number")
    CamundaStringObject identityNumber;
    @SerializedName("mobile_phone")
    CamundaStringObject mobilePhone;
    @SerializedName("name")
    CamundaStringObject name;
    @SerializedName("email")
    CamundaStringObject email;

    public OrderRoomCamunda(Booking booking, Guest guest) {
        this.paymentName = new CamundaStringObject(booking.paymentName);
        this.paymentType = new CamundaStringObject(booking.paymentType);
        this.roomType = new CamundaStringObject("" + booking.typeRoom);
        this.checkIn = new CamundaStringObject(booking.checkIn);
        this.checkOut = new CamundaStringObject(booking.checkOut);
        this.identityNumber = new CamundaStringObject(guest.identityNumber);
        this.mobilePhone = new CamundaStringObject(guest.mobilePhone);
        this.name = new CamundaStringObject(guest.name);
        this.email = new CamundaStringObject(guest.email);
    }
}
