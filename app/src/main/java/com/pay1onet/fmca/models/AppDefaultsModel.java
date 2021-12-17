package com.pay1onet.fmca.models;

public class AppDefaultsModel {
    int id;
    int mdaId;
    String email;
    String phone;
    String chargeType;
    String charge;

    public AppDefaultsModel(int id, int mdaId, String email, String phone, String chargeType, String charge) {
        this.id = id;
        this.mdaId = mdaId;
        this.email = email;
        this.phone = phone;
        this.chargeType = chargeType;
        this.charge = charge;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMdaId() {
        return mdaId;
    }

    public void setMdaId(int mdaId) {
        this.mdaId = mdaId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
