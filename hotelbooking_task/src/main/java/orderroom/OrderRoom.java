package orderroom;

import com.google.gson.Gson;
import model.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import service.BookingService;
import service.CamundaService;
import service.GuestService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.io.IOException;

@WebService
public class OrderRoom {

    private CamundaService camundaService;

    @WebMethod
    public BookingDetail orderRoom(@WebParam(name = "booking") Booking booking, @WebParam(name = "guest") Guest guest) throws IOException {
        initService();
        try {
            camundaService.orderRoom(new Container<>(new OrderRoomCamunda(booking, guest))).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new BookingDetail(booking, guest);
    }

    private void initService() {
        if (camundaService == null) {
            camundaService = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .baseUrl(CamundaService.BASE_URL)
                    .build()
                    .create(CamundaService.class);
        }
    }
}
