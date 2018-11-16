package booking;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class Payment {

    @WebMethod
    public int confirmPayment(int bookingId, long price, String payerName, int paymentType) {
        return bookingId;
    }

    @WebMethod
    public void changeBookingStatus(int bookingId, long price) {

    }
}
