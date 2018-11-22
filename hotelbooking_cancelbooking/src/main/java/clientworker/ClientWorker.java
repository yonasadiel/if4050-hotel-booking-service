package clientworker;

import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.impl.ExternalTaskClientBuilderImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cancelbooking.CancelBookingImpl;
import model.Booking;

public class ClientWorker {

    public static void main(String[] args) {
        ExternalTaskClient client = new ExternalTaskClientBuilderImpl().baseUrl("http://localhost:8080/engine-rest").build();

        client.subscribe("retrieve-booking-data")
                .lockDuration(10000)
                .handler((externalTask, externalTaskService) -> {
                    String bookingId = (String) externalTask.getVariable("booking_id");

                    CancelBookingImpl cancelBookingImpl = new CancelBookingImpl();

                    Booking booking = cancelBookingImpl.retrieveBookingData(Integer.parseInt(bookingId));
                    String checkInDate = new SimpleDateFormat("yyyy-MM-dd").format(booking.checkIn);

                    Map<String, Object> vars = new HashMap<>();
                    vars.put("booking_check_in", checkInDate);

                    externalTaskService.complete(externalTask, vars);
                })
                .open();

        client.subscribe("check-checkin-time")
                .lockDuration(10000)
                .handler((externalTask, externalTaskService) -> {
                    String bookingCheckIn = (String) externalTask.getVariable("booking_check_in");

                    Date checkInDate;
                    try {
                        checkInDate = new SimpleDateFormat("yyyy-MM-dd").parse(bookingCheckIn);
                    } catch (Exception e) {
                        e.printStackTrace();
                        checkInDate = new Date();
                    }

                    CancelBookingImpl cancelBookingImpl = new CancelBookingImpl();
                    boolean dateSuccess = cancelBookingImpl.checkCheckinTime(checkInDate);

                    Map<String, Object> vars = new HashMap<>();
                    vars.put("date_success", dateSuccess);

                    externalTaskService.complete(externalTask, vars);
                })
                .open();

        client.subscribe("set-booking-status-as-cancelled")
                .lockDuration(10000)
                .handler((externalTask, externalTaskService) -> {
                    String bookingId = (String) externalTask.getVariable("booking_id");

                    CancelBookingImpl cancelBookingImpl = new CancelBookingImpl();
                    cancelBookingImpl.setBookingStatusAsCancelled(Integer.parseInt(bookingId));

                    externalTaskService.complete(externalTask);
                })
                .open();

        client.subscribe("refund-payment")
                .lockDuration(10000)
                .handler((externalTask, externalTaskService) -> {
                    String refundAccount = (String) externalTask.getVariable("refund_account");

                    CancelBookingImpl cancelBookingImpl = new CancelBookingImpl();
                    cancelBookingImpl.refundPayment(refundAccount);

                    externalTaskService.complete(externalTask);
                })
                .open();
    }
}

