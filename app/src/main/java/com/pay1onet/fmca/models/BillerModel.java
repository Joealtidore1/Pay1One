package com.pay1onet.fmca.models;

public class BillerModel {
    String billerId, name, phone, email;

    public BillerModel(String billerId, String name, String phone, String email) {
        this.billerId = billerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getBillerId() {
        return billerId;
    }

    public void setBillerId(String billerId) {
        this.billerId = billerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
