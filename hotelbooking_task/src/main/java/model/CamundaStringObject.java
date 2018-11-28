package model;

import com.google.gson.annotations.SerializedName;

class CamundaStringObject {
    @SerializedName("value")
    public String value;
    @SerializedName("type")
    public String valType;

    public CamundaStringObject(String value) {
        this.value = value;
        this.valType = "string";
    }
}