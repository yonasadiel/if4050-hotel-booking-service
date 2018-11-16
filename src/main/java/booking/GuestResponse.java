package booking;

import com.google.gson.annotations.SerializedName;

public class GuestResponse {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("identity_number")
    private String identityNumber;

    @SerializedName("mobile_phone")
    private String mobilePhone;

    public GuestResponse(int id, String name, String email, String identityNumber, String mobilePhone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.identityNumber = identityNumber;
        this.mobilePhone = mobilePhone;
    }

    public int getId() {
        return id;
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
