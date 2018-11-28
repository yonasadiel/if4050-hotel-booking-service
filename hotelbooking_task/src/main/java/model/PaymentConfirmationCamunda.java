package model;

import com.google.gson.annotations.SerializedName;

public class PaymentConfirmationCamunda extends CamundaModel {
    @SerializedName("booking_id")
    CamundaStringObject bookingId;

    public PaymentConfirmationCamunda(int bookingId) {
        this.bookingId = new CamundaStringObject("" + bookingId);
    }
}
