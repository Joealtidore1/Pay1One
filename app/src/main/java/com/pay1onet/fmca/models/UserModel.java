package com.pay1onet.fmca.models;

public class UserModel {
    private int id;
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private String organization;
    private String userId;
    private String username;
    private boolean isLoggedIn;
    private String lastLogin;
    private Integer loginTime;
    private String mdaId;
    private Float balance;
    private String location;
    private String defPhone;
    private String defEmail;
    private String quantity;
    private String mdaCode;
    private String serialNo;
    private String apiKey;
    private String airport;
    private String payPoint;
    private Integer payPointId;
    private String device;

    public UserModel(int id,
                     String name,
                     String phoneNumber,
                     String email,
                     String address,
                     String organization,
                     String userId,
                     String username,
                     boolean isLoggedIn,
                     String lastLogin,
                     Integer loginTime,
                     String mdaId,
                     Float balance,
                     String defPhone,
                     String defEmail,
                     String quantity,
                     String mdaCode,
                     String serialNo,
                     String apiKey,
                     String airport,
                     String payPoint,
                     Integer payPointId,
                     String device,
                     String location) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.organization = organization;
        this.userId = userId;
        this.username = username;
        this.isLoggedIn = isLoggedIn;
        this.lastLogin = lastLogin;
        this.loginTime = loginTime;
        this.mdaId = mdaId;
        this.balance = balance;
        this.defPhone = defPhone;
        this.defEmail = defEmail;
        this.quantity = quantity;
        this.mdaCode = mdaCode;
        this.serialNo = serialNo;
        this.apiKey = apiKey;
        this.airport = airport;
        this.payPoint = payPoint;
        this.payPointId = payPointId;
        this.device = device;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
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

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Integer getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Integer loginTime) {
        this.loginTime = loginTime;
    }

    public String getMdaId() {
        return mdaId;
    }

    public void setMdaId(String mdaId) {
        this.mdaId = mdaId;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public String getDefPhone() {
        return defPhone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDefPhone(String defPhone) {
        this.defPhone = defPhone;
    }

    public String getDefEmail() {
        return defEmail;
    }

    public void setDefEmail(String defEmail) {
        this.defEmail = defEmail;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMdaCode() {
        return mdaCode;
    }

    public void setMdaCode(String mdaCode) {
        this.mdaCode = mdaCode;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getPayPoint() {
        return payPoint;
    }

    public void setPayPoint(String payPoint) {
        this.payPoint = payPoint;
    }

    public Integer getPayPointId() {
        return payPointId;
    }

    public void setPayPointId(Integer payPointId) {
        this.payPointId = payPointId;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    /*public UserModel(int id, String userId, String name, String email, String address, String organization, String username, String mdaCode, String lastLogin, String location, String phoneNumber) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.organization = organization;
        this.username = username;
        this.mdaCode = mdaCode;
        this.lastLogin = lastLogin;
        this.location = location;
        this.phoneNumber = phoneNumber;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMdaCode() {
        return mdaCode;
    }

    public void setMdaCode(String mdaCode) {
        this.mdaCode = mdaCode;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }*/
}
