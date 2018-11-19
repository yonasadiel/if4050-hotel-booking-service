package cancelbooking;

import model.Booking;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Date;

@WebService
public class CancelBookingImpl {

    @WebMethod
    public void cancelBooking(@WebParam(name = "booking") Booking booking) {
        Date now = new Date();
        if (now.after(booking.checkIn)) {
            sendFailStatus(booking);
        } else {
            setBookingStatusAsCancelled(booking.id);
            refundPayment(booking.id);
            sendSuccessStatus(booking);
        }
    }

    @WebMethod
    public void sendFailStatus(@WebParam(name = "booking") Booking booking) {
        // STUB
    }

    @WebMethod
    public void setBookingStatusAsCancelled(@WebParam(name = "bookingId") int bookingId) {
        // STUB
    }

    @WebMethod
    public void refundPayment(@WebParam(name = "bookingId") int bookingId) {
        // STUB
    }

    @WebMethod
    public void sendSuccessStatus(@WebParam(name = "booking") Booking booking) {
        // STUB
    }
}
