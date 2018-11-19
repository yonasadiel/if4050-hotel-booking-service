package client_worker;

import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.ExternalTaskClientBuilder;
import org.camunda.bpm.client.impl.ExternalTaskClientBuilderImpl;
import org.camunda.bpm.client.impl.ExternalTaskClientImpl;
import org.camunda.bpm.client.task.ExternalTaskHandler;

import java.util.HashMap;
import java.util.Map;

import booking.BookingImpl;
import model.Booking;

public class ClientWorker {

    public void runClient() {
        ExternalTaskClient client = new ExternalTaskClientBuilderImpl().baseUrl("http://localhost:8080/engine-rest").build();

        client.subscribe("get-guest-id")
            .lockDuration(1000)
            .handler((externalTask, externalTaskService) -> {
                String identityNumber = (String) externalTask.getVariable("identityNumber");

                BookingImpl bookingImpl = BookingImpl();
                int guestId = bookingImpl.getGuestId(identityNumber);

                Map<String, Object> vars = new HashMap<>();
                vars.put("guestId", guestId);

                externalTaskService.complete(externalTask, vars);
            })
            .open();
        client.subscribe("create-guest")
            .lockDuration(1000)
            .handler((externalTask, externalTaskService) -> {
                String email = (String) externalTask.getVariable("email");
                String identityNumber = (String) externalTask.getVariable("identityNumber");
                String mobilePhone = (String) externalTask.getVariable("mobilePhone");
                String name = (String) externalTask.getVariable("name");

                Guest guest = new Guest(email, identityNumber, mobilePhone, name);

                BookingImpl bookingImpl = BookingImpl();
                Guest newGuest = bookingImpl.createGuest(guest);

                int guestId = newGuest.id;

                Map<String, Object> vars = new HashMap<>();
                vars.put("guestId", guestId);

                externalTaskService.complete(externalTask, vars);
            })
            .open();
        client.subscribe("create-booking-data")
            .lockDuration(1000)
            .handler((externalTask, externalTaskService) -> {
                String paymentName = (String) externalTask.getVariable("paymentName");
                String paymentType = (String) externalTask.getVariable("paymentType");
                long price = (long) externalTask.getVariable("price");
                int typeRoom = (int) externalTask.getVariable("typeRoom");
                Date checkIn = (Date) externalTask.getVariable("checkIn");
                Date checkOut = (Date) externalTask.getVariable("checkOut");
                int guestId = (int) externalTask.getVariable("guestId");

                Booking booking = new Booking(paymentName, paymentType, price, typeRoom, checkIn, checkOut);
                booking.guestId = guestId;

                BookingImpl bookingImpl = BookingImpl();
                bookingImpl.createBookingData(booking);

                externalTaskService.complete(externalTask);
            })
            .open();
    }
}

