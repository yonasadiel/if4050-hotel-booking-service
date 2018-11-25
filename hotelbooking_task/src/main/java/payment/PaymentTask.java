package payment;

import model.Booking;
import model.BookingDetail;
import model.Guest;
import model.PaymentConfirmationRequest;
import service.BookingService;
import service.GuestService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class PaymentTask {

    private GuestService guestService;
    private BookingService bookingService;

    @WebMethod
    public BookingDetail confirmPayment(@WebParam(name = "paymentConfirmationRequest") PaymentConfirmationRequest request) {
        // STUB will always be run as confirmed
        confirmBookingStatus(request.bookingId);
        Booking booking = bookingService.getBookingById(request.bookingId);
        Guest guest = guestService.getGuestById(booking.guestId);

        confirmBookingStatus(booking.id);
        sendReceipt(booking.id, guest.id);

        return new BookingDetail(booking, guest);
    }

    @WebMethod
    public void confirmBookingStatus(int bookingId) {
        // STUB
    }

    @WebMethod
    public void sendReceipt(int bookingId, int guestId) {
        // STUB
    }
}
