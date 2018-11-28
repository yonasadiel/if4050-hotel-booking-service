package cancelbooking;

import com.google.gson.Gson;
import model.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import service.BookingService;
import service.CamundaService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.io.IOException;
import java.util.Date;

@WebService
public class CancelBooking {
    private CamundaService camundaService;

    @WebMethod
    public void cancelBooking(@WebParam(name = "booking") Booking booking, @WebParam(name = "refundAccount") String refundAccount) {
        initService();
        try {
            camundaService.cancelBooking(
                    new Container<>(
                            new CancelBookingCamunda(
                                    booking.id,
                                    refundAccount
                            )
                    )
            ).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
