package com.pay1onet.fmca.models;

public class PaymentModel {
    int id;
    double amount;
    String payerName;
    String payerPhone;
    String payerEmail;
    String paymentDate;
    String paymentTime;
    String agent;
    String revenueHead;
    String synced;
    String ref;

    public PaymentModel(int id, double amount, String payerName, String payerPhone, String payerEmail, String paymentDate, String paymentTime, String agent, String revenueHead, String synced, String ref, String previousBalance, String currentBalance, String rrr, String location, String mdaId, String revId, String agentId, String quantity, String transFee, String total, String method, String revCode, String lastSerial, String dept, String department, String cate, String category, String discount, String mainAmt, String subs, String emr, String shift, String billno) {
        this.id = id;
        this.amount = amount;
        this.payerName = payerName;
        this.payerPhone = payerPhone;
        this.payerEmail = payerEmail;
        this.paymentDate = paymentDate;
        this.paymentTime = paymentTime;
        this.agent = agent;
        this.revenueHead = revenueHead;
        this.synced = synced;
        this.ref = ref;
        this.previousBalance = previousBalance;
        this.currentBalance = currentBalance;
        this.rrr = rrr;
        this.location = location;
        this.mdaId = mdaId;
        this.revId = revId;
        this.agentId = agentId;
        this.quantity = quantity;
        this.transFee = transFee;
        this.total = total;
        this.method = method;
        this.revCode = revCode;
        this.lastSerial = lastSerial;
        this.dept = dept;
        this.department = department;
        this.cate = cate;
        this.category = category;
        this.discount = discount;
        this.mainAmt = mainAmt;
        this.subs = subs;
        this.shift = shift;
        this.priceType = priceType;
        this.billno = billno;
    }

    public PaymentModel(int id,
                        double amount,
                        String payerName,
                        String payerPhone,
                        String payerEmail,
                        String paymentDate,
                        String paymentTime,
                        String agent,
                        String revenueHead,
                        String synced,
                        String ref,
                        String previousBalance,
                        String currentBalance,
                        String rrr,
                        String location,
                        String mdaId,
                        String revId,
                        String agentId,
                        String quantity,
                        String transFee,
                        String total,
                        String method,
                        String revCode,
                        String lastSerial,
                        String dept,
                        String department,
                        String cate,
                        String category,
                        String discount,
                        String mainAmt,
                        String subs,
                        String desc,
                        String emr,
                        String shift,
                        String priceType,
                        String billno,
                        String payPoint,
                        Integer payPointId,
                        String airport) {
        this.id = id;
        this.amount = amount;
        this.payerName = payerName;
        this.payerPhone = payerPhone;
        this.payerEmail = payerEmail;
        this.paymentDate = paymentDate;
        this.paymentTime = paymentTime;
        this.agent = agent;
        this.revenueHead = revenueHead;
        this.synced = synced;
        this.ref = ref;
        this.previousBalance = previousBalance;
        this.currentBalance = currentBalance;
        this.rrr = rrr;
        this.location = location;
        this.mdaId = mdaId;
        this.revId = revId;
        this.agentId = agentId;
        this.quantity = quantity;
        this.transFee = transFee;
        this.total = total;
        this.method = method;
        this.revCode = revCode;
        this.lastSerial = lastSerial;
        this.dept = dept;
        this.department = department;
        this.cate = cate;
        this.category = category;
        this.discount = discount;
        this.mainAmt = mainAmt;
        this.subs = subs;
        this.desc = desc;
        this.emr = emr;
        this.shift = shift;
        this.priceType = priceType;
        this.billno = billno;
        this.payPoint = payPoint;
        this.payPointId = payPointId;
        this.airport = airport;
    }

    String previousBalance;
    String currentBalance;
    String rrr;
    String location;
    String mdaId;
    String revId;
    String agentId;
    String quantity;
    String transFee;
    String total;
    String method;
    String revCode;
    String lastSerial;
    String dept;
    String department;
    String cate;
    String category;
    String discount;
    String mainAmt;
    String subs;
    String desc;
    private String emr;
    String shift;
    String priceType;
    String billno;
    String payPoint;
    Integer payPointId;
    String airport;


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public PaymentModel(int id,
                        double amount,
                        String payerName,
                        String payerPhone,
                        String payerEmail,
                        String paymentDate,
                        String paymentTime,
                        String agent,
                        String revenueHead,
                        String synced,
                        String ref,
                        String previousBalance,
                        String currentBalance,
                        String rrr,
                        String location,
                        String mdaId,
                        String revId,
                        String agentId,
                        String quantity,
                        String transFee,
                        String total,
                        String method,
                        String revCode,
                        String lastSerial,
                        String dept,
                        String department,
                        String cate,
                        String category,
                        String discount,
                        String mainAmt,
                        String subs,
                        String emr,
                        String shift,
                        String priceType,
                        String payPoint,
                        Integer payPointId,
                        String airport) {
        this.id = id;
        this.amount = amount;
        this.payerName = payerName;
        this.payerPhone = payerPhone;
        this.payerEmail = payerEmail;
        this.paymentDate = paymentDate;
        this.paymentTime = paymentTime;
        this.agent = agent;
        this.revenueHead = revenueHead;
        this.synced = synced;
        this.ref = ref;
        this.previousBalance = previousBalance;
        this.currentBalance = currentBalance;
        this.rrr = rrr;
        this.location = location;
        this.mdaId = mdaId;
        this.revId = revId;
        this.agentId = agentId;
        this.quantity = quantity;
        this.transFee = transFee;
        this.total = total;
        this.method = method;
        this.revCode = revCode;
        this.lastSerial = lastSerial;
        this.dept = dept;
        this.department = department;
        this.cate = cate;
        this.category = category;
        this.discount = discount;
        this.mainAmt = mainAmt;
        this.subs = subs;
        this.emr = emr;
        this.shift = shift;
        this.priceType = priceType;
        this.payPoint = payPoint;
        this.payPointId = payPointId;
        this.airport = airport;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getPayerPhone() {
        return payerPhone;
    }

    public void setPayerPhone(String payerPhone) {
        this.payerPhone = payerPhone;
    }

    public String getPayerEmail() {
        return payerEmail;
    }

    public void setPayerEmail(String payerEmail) {
        this.payerEmail = payerEmail;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getRevenueHead() {
        return revenueHead;
    }

    public void setRevenueHead(String revenueHead) {
        this.revenueHead = revenueHead;
    }

    public String getSynced() {
        return synced;
    }

    public void setSynced(String synced) {
        this.synced = synced;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getPreviousBalance() {
        return previousBalance;
    }

    public void setPreviousBalance(String previousBalance) {
        this.previousBalance = previousBalance;
    }

    public String getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(String currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getRrr() {
        return rrr;
    }

    public void setRrr(String rrr) {
        this.rrr = rrr;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMdaId() {
        return mdaId;
    }

    public void setMdaId(String mdaId) {
        this.mdaId = mdaId;
    }

    public String getRevId() {
        return revId;
    }

    public void setRevId(String revId) {
        this.revId = revId;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTransFee() {
        return transFee;
    }

    public void setTransFee(String transFee) {
        this.transFee = transFee;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRevCode() {
        return revCode;
    }

    public void setRevCode(String revCode) {
        this.revCode = revCode;
    }

    public String getLastSerial() {
        return lastSerial;
    }

    public void setLastSerial(String lastSerial) {
        this.lastSerial = lastSerial;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getMainAmt() {
        return mainAmt;
    }

    public void setMainAmt(String mainAmt) {
        this.mainAmt = mainAmt;
    }

    public String getSubs() {
        return subs;
    }

    public void setSubs(String subs) {
        this.subs = subs;
    }

    public String getEmr() {
        return emr;
    }

    public void setEmr(String emr) {
        this.emr = emr;
    }


    public void setBillno(String billno) {
        this.billno = billno;
    }

    public String getBillNo() {
        return billno;
    }

    public String getBillno() {

        return billno;
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

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    @Override
    public String toString() {
        return "PaymentModel{" +
                "id=" + id +
                ", amount=" + amount +
                ", payerName='" + payerName + '\'' +
                ", payerPhone='" + payerPhone + '\'' +
                ", payerEmail='" + payerEmail + '\'' +
                ", paymentDate='" + paymentDate + '\'' +
                ", paymentTime='" + paymentTime + '\'' +
                ", agent='" + agent + '\'' +
                ", revenueHead='" + revenueHead + '\'' +
                ", synced='" + synced + '\'' +
                ", ref='" + ref + '\'' +
                ", previousBalance='" + previousBalance + '\'' +
                ", currentBalance='" + currentBalance + '\'' +
                ", rrr='" + rrr + '\'' +
                ", location='" + location + '\'' +
                ", mdaId='" + mdaId + '\'' +
                ", revId='" + revId + '\'' +
                ", agentId='" + agentId + '\'' +
                ", quantity='" + quantity + '\'' +
                ", transFee='" + transFee + '\'' +
                ", total='" + total + '\'' +
                ", method='" + method + '\'' +
                ", revCode='" + revCode + '\'' +
                ", lastSerial='" + lastSerial + '\'' +
                ", dept='" + dept + '\'' +
                ", department='" + department + '\'' +
                ", cate='" + cate + '\'' +
                ", category='" + category + '\'' +
                ", discount='" + discount + '\'' +
                ", mainAmt='" + mainAmt + '\'' +
                ", subs='" + subs + '\'' +
                ", desc='" + desc + '\'' +
                ", emr='" + emr + '\'' +
                ", shift='" + shift + '\'' +
                ", priceType='" + priceType + '\'' +
                ", billno='" + billno + '\'' +
                '}';
    }
}
