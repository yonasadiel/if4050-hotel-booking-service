package booking.model;

import com.google.gson.annotations.SerializedName;

public class Guest {
    public int id;
    public String email;
    @SerializedName("identity_number")
    public String identityNumber;
    @SerializedName("mobile_phone")
    public String mobilePhone;
    public String name;

    public Guest(String email, String identityNumber, String mobilePhone, String name) {
        this.email = email;
        this.identityNumber = identityNumber;
        this.mobilePhone = mobilePhone;
        this.name = name;
    }
}
