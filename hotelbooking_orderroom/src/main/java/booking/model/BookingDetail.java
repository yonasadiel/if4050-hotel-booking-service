package booking.model;

import booking.model.Booking;
import booking.model.Guest;

public class BookingDetail {
    public Booking booking;
    public Guest guest;

    public BookingDetail(Booking booking, Guest guest) {
        this.booking = booking;
        this.guest = guest;
    }
}
