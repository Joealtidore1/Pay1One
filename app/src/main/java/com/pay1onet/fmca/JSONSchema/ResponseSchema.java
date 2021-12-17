package com.pay1onet.fmca.JSONSchema;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseSchema {
    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("phone")
    @Expose
    String phone;

    @SerializedName("email")
    @Expose
    String email;

    @SerializedName("address")
    @Expose
    String address;

    @SerializedName("organisation")
    @Expose
    String organisation;

    @SerializedName("userID")
    @Expose
    String userId;

    @SerializedName("username")
    @Expose
    String username;

    @SerializedName("isLoggedin")
    @Expose
    boolean isLoggedin;

    @SerializedName("lastLogin")
    @Expose
    String lastLogin;

    /*@SerializedName("loginTime")
    @Expose
    int loginTime;
*/
    @SerializedName("mdaID")
    @Expose
    String mdaId;

    @SerializedName("balance")
    @Expose
    Float balance;

    @SerializedName("defphone")
    @Expose
    String defphone;

    @SerializedName("defemail")
    @Expose
    String defemail;

    @SerializedName("quantity")
    @Expose
    String quantity;

    @SerializedName("mdacode")
    @Expose
    String mdacode;

    @SerializedName("serialno")
    @Expose
    String serialNo;

    @SerializedName("apikey")
    @Expose
    String apikey;

    @SerializedName("airport")
    @Expose
    String airport;

    @SerializedName("paypoint")
    @Expose
    String paypoint;

    @SerializedName("paypointID")
    @Expose
    Integer paypointId;

    @SerializedName("device")
    @Expose
    String device;

    @SerializedName("location")
    @Expose
    String location;

    public boolean isLoggedin() {
        return isLoggedin;
    }

    public void setLoggedin(boolean loggedin) {
        isLoggedin = loggedin;
    }

    /*public Integer getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Integer loginTime) {
        this.loginTime = loginTime;
    }*/

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public String getDefphone() {
        return defphone;
    }

    public void setDefphone(String defphone) {
        this.defphone = defphone;
    }

    public String getDefemail() {
        return defemail;
    }

    public void setDefemail(String defemail) {
        this.defemail = defemail;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getPaypoint() {
        return paypoint;
    }

    public void setPaypoint(String paypoint) {
        this.paypoint = paypoint;
    }

    public Integer getPaypointId() {
        return paypointId;
    }

    public void setPaypointId(Integer paypointId) {
        this.paypointId = paypointId;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getMdaId() {
        return mdaId;
    }

    public void setMdaId(String mdaId) {
        this.mdaId = mdaId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getMdacode() {
        return mdacode;
    }

    public void setMdacode(String mdacode) {
        this.mdacode = mdacode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
