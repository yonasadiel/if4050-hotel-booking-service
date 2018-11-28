package model;

import com.google.gson.annotations.SerializedName;

public class CancelBookingCamunda extends CamundaModel {
    @SerializedName("booking_id")
    public CamundaStringObject bookingId;
    @SerializedName("refund_account")
    public CamundaStringObject refundAccount;

    public CancelBookingCamunda(
            int bookingId,
            String refundAccount) {
        this.bookingId = new CamundaStringObject(String.valueOf(bookingId));
        this.refundAccount= new CamundaStringObject(refundAccount);
    }
}
