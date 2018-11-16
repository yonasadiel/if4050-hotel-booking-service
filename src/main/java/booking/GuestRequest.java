package booking;

import com.google.gson.annotations.SerializedName;

public class GuestRequest {

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("identity_number")
    private String identityNumber;

    @SerializedName("mobile_phone")
    private String mobilePhone;

    public GuestRequest(String name, String identityNumber, String email, String mobilePhone) {
        this.name = name;
        this.email = email;
        this.identityNumber = identityNumber;
        this.mobilePhone = mobilePhone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }
}
