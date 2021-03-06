package clientworker;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cancelbooking.CancelBookingTask;
import orderroom.OrderRoomTask;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.impl.ExternalTaskClientBuilderImpl;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.StringValue;

import model.Booking;
import model.Guest;

import payment.Payment;
import payment.PaymentTask;

public class ClientWorker {

    public static void main(String[] args) {
        ExternalTaskClient client = new ExternalTaskClientBuilderImpl().baseUrl("http://localhost:8006/engine-rest").build();

        client.subscribe("get-guest-id")
                .lockDuration(10000)
                .handler((externalTask, externalTaskService) -> {
                    String identityNumber = (String) externalTask.getVariable("identity_number");

                    OrderRoomTask orderRoom = new OrderRoomTask();

                    StringValue guestId = Variables.stringValue(String.valueOf(orderRoom.getGuestId(identityNumber)));

                    Map<String, Object> vars = new HashMap<>();
                    vars.put("guest_id", guestId);

                    externalTaskService.complete(externalTask, vars);
                })
                .open();

        client.subscribe("create-guest")
                .lockDuration(10000)
                .handler((externalTask, externalTaskService) -> {
                    String email = (String) externalTask.getVariable("email");
                    String identityNumber = (String) externalTask.getVariable("identity_number");
                    String mobilePhone = (String) externalTask.getVariable("mobile_phone");
                    String name = (String) externalTask.getVariable("name");

                    Guest guest = new Guest(email, identityNumber, mobilePhone, name);

                    OrderRoomTask orderRoom = new OrderRoomTask();
                    Guest newGuest = null;
                    try {
                        newGuest = orderRoom.createGuest(guest);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    StringValue guestId = Variables.stringValue(String.valueOf(newGuest.id));

                    Map<String, Object> vars = new HashMap<>();
                    vars.put("guest_id", guestId);

                    externalTaskService.complete(externalTask, vars);
                })
                .open();

        client.subscribe("create-booking-data")
                .lockDuration(10000)
                .handler((externalTask, externalTaskService) -> {
                    try {
                        String paymentName = (String) externalTask.getVariable("payment_name");
                        String paymentType = (String) externalTask.getVariable("payment_type");
                        int typeRoom = Integer.parseInt(externalTask.getVariable("room_type"));
                        String checkIn = externalTask.getVariable("check_in");
                        String checkOut = externalTask.getVariable("check_out");
                        int guestId = Integer.parseInt(externalTask.getVariable("guest_id"));

                        Booking booking = new Booking(paymentName, paymentType, typeRoom, checkIn, checkOut);
                        booking.guestId = guestId;

                        OrderRoomTask orderRoom = new OrderRoomTask();
                        orderRoom.createBookingData(booking);

                        externalTaskService.complete(externalTask);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })
                .open();

        client.subscribe("retrieve-booking-data")
                .lockDuration(10000)
                .handler((externalTask, externalTaskService) -> {
                    String bookingId = (String) externalTask.getVariable("booking_id");

                    CancelBookingTask cancelBookingImpl = new CancelBookingTask();

                    Booking booking = null;
                    try {
                        booking = cancelBookingImpl.retrieveBookingData(Integer.parseInt(bookingId));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Map<String, Object> vars = new HashMap<>();
                    vars.put("booking_check_in", booking.checkIn);

                    externalTaskService.complete(externalTask, vars);
                })
                .open();

        client.subscribe("send-payment-information")
                .lockDuration(10000)
                .handler(((externalTask, externalTaskService) -> {
                    String paymentName = externalTask.getVariable("payment-name");
                    int roomType = Integer.parseInt(externalTask.getVariable("room-type"));

                    OrderRoomTask task = new OrderRoomTask();
                    String paymentId = null;
                    try {
                        paymentId = task.sendPaymentInformation(paymentName, roomType);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Map<String, Object> vars = new HashMap<>();
                    if (paymentId != null) {
                        vars.put("payment-id", paymentId);
                    }

                    externalTaskService.complete(externalTask, vars);
                }));

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

                    CancelBookingTask cancelBookingImpl = new CancelBookingTask();
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

                    CancelBookingTask cancelBookingImpl = new CancelBookingTask();
                    try {
                        cancelBookingImpl.setBookingStatusAsCancelled(Integer.parseInt(bookingId));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    externalTaskService.complete(externalTask);
                })
                .open();

        client.subscribe("refund-payment")
                .lockDuration(10000)
                .handler((externalTask, externalTaskService) -> {
                    String refundAccount = (String) externalTask.getVariable("refund_account");

                    CancelBookingTask cancelBookingImpl = new CancelBookingTask();
                    cancelBookingImpl.refundPayment(refundAccount);

                    externalTaskService.complete(externalTask);
                })
                .open();

        client.subscribe("retrieve-booking-status")
                .lockDuration(10000)
                .handler((externalTask, externalTaskService) -> {
                    String bookingId = (String) externalTask.getVariable("booking_id");

                    PaymentTask paymentTask= new PaymentTask();

                    boolean hasPaid = false;
                    try {
                        hasPaid = paymentTask.retrieveBookingStatus(Integer.parseInt(bookingId));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Map<String, Object> vars = new HashMap<>();
                    vars.put("has_paid", hasPaid);

                    externalTaskService.complete(externalTask, vars);
                })
                .open();

        client.subscribe("change-booking-status")
                .lockDuration(10000)
                .handler((externalTask, externalTaskService) -> {
                    String bookingId= (String) externalTask.getVariable("booking_id");

                    PaymentTask paymentTask = new PaymentTask();
                    try {
                        paymentTask.confirmBookingStatus(Integer.parseInt(bookingId));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    externalTaskService.complete(externalTask);
                })
                .open();
    }
}

