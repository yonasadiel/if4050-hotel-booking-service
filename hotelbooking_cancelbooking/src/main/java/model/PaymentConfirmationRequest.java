package model;

public class PaymentConfirmationRequest {
    public int bookingId;
    public String paymentName;
    public String paymentType;
    public long price;

    public PaymentConfirmationRequest(int bookingId, String paymentName, String paymentType, long price) {
        this.bookingId = bookingId;
        this.paymentName = paymentName;
        this.paymentType = paymentType;
        this.price = price;
    }
}
