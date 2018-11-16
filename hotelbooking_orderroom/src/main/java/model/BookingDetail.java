package model;

public class BookingDetail {
    public Booking booking;
    public Guest guest;

    public BookingDetail(Booking booking, Guest guest) {
        this.booking = booking;
        this.guest = guest;
    }
}
