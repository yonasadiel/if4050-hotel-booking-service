package model;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BookingCamunda extends CamundaModel {
    @SerializedName("payment_type")
    public CamundaStringObject paymentType;
    @SerializedName("payment_name")
    public CamundaStringObject paymentName;
    @SerializedName("room_type")
    public CamundaStringObject roomType;
    @SerializedName("check_in")
    public CamundaStringObject checkIn;
    @SerializedName("check_out")
    public CamundaStringObject checkOut;
    @SerializedName("identity_number")
    public CamundaStringObject identityNumber;
    @SerializedName("name")
    public CamundaStringObject name;
    @SerializedName("email")
    public CamundaStringObject email;
    @SerializedName("mobile_phone")
    public CamundaStringObject mobilePhone;

    public BookingCamunda(
            String paymentType,
            String paymentName,
            int roomType,
            String checkIn,
            String checkOut,
            String identityNumber,
            String name,
            String email,
            String mobilePhone) {
        this.paymentType = new CamundaStringObject(paymentType);
        this.paymentName = new CamundaStringObject(paymentName);

        this.roomType = new CamundaStringObject(String.valueOf(roomType));
        this.checkIn = new CamundaStringObject(checkIn);
        this.checkOut = new CamundaStringObject(checkOut);
        this.identityNumber = new CamundaStringObject(identityNumber);
        this.name = new CamundaStringObject(name);
        this.email = new CamundaStringObject(email);
        this.mobilePhone = new CamundaStringObject(mobilePhone);
    }

    class CamundaStringObject {
        @SerializedName("value")
        public String value;
        @SerializedName("type")
        public String valType;

        public CamundaStringObject(String value) {
            this.value = value;
            this.valType = "string";
        }
    }
}
