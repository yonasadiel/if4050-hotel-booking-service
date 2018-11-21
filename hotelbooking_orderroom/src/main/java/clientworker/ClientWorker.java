package clientworker;

import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.impl.ExternalTaskClientBuilderImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import booking.BookingImpl;
import model.Booking;
import model.Guest;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.IntegerValue;

public class ClientWorker {

    public void runClient() {
        ExternalTaskClient client = new ExternalTaskClientBuilderImpl().baseUrl("http://localhost:8080/engine-rest").build();

        client.subscribe("get-guest-id")
            .lockDuration(1000)
            .handler((externalTask, externalTaskService) -> {
                String identityNumber = (String) externalTask.getVariable("identityNumber");

                BookingImpl bookingImpl = new BookingImpl();

                IntegerValue guestId = Variables.integerValue(bookingImpl.getGuestId(identityNumber));

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

                BookingImpl bookingImpl = new BookingImpl();
                Guest newGuest = bookingImpl.createGuest(guest);

                IntegerValue guestId = Variables.integerValue(newGuest.id);

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
                long price = externalTask.getVariable("price");
                int typeRoom = externalTask.getVariable("typeRoom");
                Date checkIn = (Date) externalTask.getVariable("checkIn");
                Date checkOut = (Date) externalTask.getVariable("checkOut");
                int guestId = externalTask.getVariable("guestId");

                Booking booking = new Booking(paymentName, paymentType, price, typeRoom, checkIn, checkOut);
                booking.guestId = guestId;

                BookingImpl bookingImpl = new BookingImpl();
                bookingImpl.createBookingData(booking);

                externalTaskService.complete(externalTask);
            })
            .open();
    }
}

