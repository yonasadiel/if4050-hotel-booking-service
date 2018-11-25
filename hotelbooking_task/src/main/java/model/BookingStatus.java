package model;

public class BookingStatus {

    public static final String PAID = "PAID";
    public static final String PENDING = "PENDING";
    public static final String CANCELLED = "CANCELLED";

    public String status;

    public BookingStatus(String status) {
        this.status = status;
    }
}
