package com.pay1onet.fmca.JSONSchema;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaymentRetrieval {

    @SerializedName("payment")
    @Expose
    List<PaymentDetail>  paymentDetails;

    public List<PaymentDetail> getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(List<PaymentDetail> paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public class PaymentDetail{
        @SerializedName("amount")
        @Expose
        double amount;
        @SerializedName("payername")
        @Expose
        String payerName;
        @SerializedName("payerphone")
        @Expose
        String payerPhone;
        @SerializedName("payerEmail")
        @Expose
        String payerEmail;
        @SerializedName("paymentdate")
        @Expose
        String paymentDate;
        @SerializedName("paymenttime")
        @Expose
        String paymentTime;
        @SerializedName("agent")
        @Expose
        String agent;
        @SerializedName("revenuehead")
        @Expose
        String revenueHead;
        @SerializedName("synced")
        @Expose
        String synced;
        @SerializedName("ref")
        @Expose
        String ref;
        @SerializedName("pbalance")
        @Expose
        String previousBalance;
        @SerializedName("cbalance")
        @Expose
        String currentBalance;
        @SerializedName("uniqueID")
        @Expose
        String rrr;
        @SerializedName("location")
        @Expose
        String location;
        @SerializedName("mdaID")
        @Expose
        String mdaId;
        @SerializedName("revID")
        @Expose
        String revId;
        @SerializedName("agentID")
        @Expose
        String agentId;
        @SerializedName("quantity")
        @Expose
        String quantity;
        String transFee;
        @SerializedName("total")
        @Expose
        String total;
        @SerializedName("method")
        @Expose
        String method;
        @SerializedName("revcode")
        @Expose
        String revCode;
        @SerializedName("lastserial")
        @Expose
        String lastSerial;
        @SerializedName("dept")
        @Expose
        String dept;
        @SerializedName("department")
        @Expose
        String department;
        @SerializedName("cate")
        @Expose
        String cate;
        @SerializedName("category")
        @Expose
        String category;
        @SerializedName("discount")
        @Expose
        String discount;
        @SerializedName("mainamt")
        @Expose
        String mainAmt;
        String subs;
        @SerializedName("desc")
        @Expose
        String desc;
        @SerializedName("emr")
        @Expose
        private String emr;
        @SerializedName("shift")
        @Expose
        String shift;
        @SerializedName("pricetype")
        @Expose
        String priceType;
        @SerializedName("billno")
        @Expose
        String billno;


        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
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

    }

}
