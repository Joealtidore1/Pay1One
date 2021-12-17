package com.pay1onet.fmca.JSONSchema;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppDefaultsSchema {
    @SerializedName("mdaID")
    @Expose
    int mdaId;

    @SerializedName("PayerPhone")
    @Expose
    String phone;

    @SerializedName("payerEmail")
    @Expose
    String email;

    @SerializedName("chargetype")
    @Expose
    String chargeType;

    @SerializedName("charge")
    @Expose
    String charge;

    public int getMdaId() {
        return mdaId;
    }

    public void setMdaId(int mdaId) {
        this.mdaId = mdaId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }
}
