package model;

import com.google.gson.annotations.SerializedName;

public class Container<T extends CamundaModel> {
    @SerializedName("variables")
    public T variables;

    public Container(T variables) {
        this.variables = variables;
    }
}
