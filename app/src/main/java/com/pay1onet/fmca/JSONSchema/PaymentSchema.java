package com.pay1onet.fmca.JSONSchema;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentSchema {
    @SerializedName("status")
    @Expose
    boolean status;

    @SerializedName("ref")
    @Expose
    String ref;

    @SerializedName("message")
    @Expose
    String message;


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

}
