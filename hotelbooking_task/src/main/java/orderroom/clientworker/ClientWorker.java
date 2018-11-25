package orderroom.clientworker;

import orderroom.OrderRoomTask;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.impl.ExternalTaskClientBuilderImpl;

import java.util.HashMap;
import java.util.Map;

import model.Booking;
import model.Guest;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.StringValue;

public class ClientWorker {

    public static void main(String[] args) {
        ExternalTaskClient client = new ExternalTaskClientBuilderImpl().baseUrl("http://localhost:8080/engine-rest").build();

        client.subscribe("get-guest-id")
                .lockDuration(10000)
                .handler((externalTask, externalTaskService) -> {
                    String identityNumber = (String) externalTask.getVariable("identity_number");

                    OrderRoomTask orderRoomTask = new OrderRoomTask();

                    StringValue guestId = Variables.stringValue(String.valueOf(orderRoomTask.getGuestId(identityNumber)));

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

                    OrderRoomTask orderRoomTask = new OrderRoomTask();
                    Guest newGuest = orderRoomTask.createGuest(guest);

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

                        Booking booking = new Booking(paymentName, paymentType, 0, typeRoom, checkIn, checkOut);
                        booking.guestId = guestId;

                        OrderRoomTask orderRoomTask = new OrderRoomTask();
                        orderRoomTask.createBookingData(booking);

                        externalTaskService.complete(externalTask);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })
                .open();
    }
}
