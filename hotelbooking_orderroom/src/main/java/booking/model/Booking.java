package booking.model;

import java.util.Date;

public class Booking {
    public int id;
    public String paymentName;
    public String paymentType;
    public long price;
    public String typeRoom;
    public Date checkin;
    public Date checkout;

    public Booking(int id, String paymentName, String paymentType, long price, String typeRoom, Date checkin, Date checkout) {
        this.id = id;
        this.paymentName = paymentName;
        this.paymentType = paymentType;
        this.price = price;
        this.typeRoom = typeRoom;
        this.checkin = checkin;
        this.checkout = checkout;
    }
}