package com.pay1onet.fmca.JSONSchema;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BillerSchema {

    @SerializedName("data")
    @Expose
    List<GeneratedBill> generatedBill;

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("message")
    @Expose
    String message;

    public List<GeneratedBill> getGeneratedBill() {
        return generatedBill;
    }

    public void setGeneratedBill(List<GeneratedBill> generatedBill) {
        this.generatedBill = generatedBill;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


   public static class GeneratedBill {
        @SerializedName("billID")
        @Expose
        int billId;

        @SerializedName("billno")
        @Expose
        String billno;

        @SerializedName("ref")
        @Expose
        String ref;

       @SerializedName("amount")
       @Expose
       String amount;

        @SerializedName("payername")
        @Expose
        String payerName;

        @SerializedName("payeremail")
        @Expose
        String payerEmail;

        @SerializedName("payerphone")
        @Expose
        String PayerPhone;

        @SerializedName("dategenerated")
        @Expose
        String dateGenerated;

        @SerializedName("datesynced")
        @Expose
        String dateSynced;

        @SerializedName("timegenerated")
        @Expose
        String timeGenerated;

        @SerializedName("timesynced")
        @Expose
        String timeSynced;

        @SerializedName("mdaID")
        @Expose
        int mdaID;

        @SerializedName("generatedBill")
        @Expose
        String biller;

        @SerializedName("billerID")
        @Expose
        int billerID;

        @SerializedName("status")
        @Expose
        String status;

        @SerializedName("revenuehead")
        @Expose
        String revenuehead;

        @SerializedName("revenueId")
        @Expose
        int revenueId;

        @SerializedName("quantity")
        @Expose
        String quantity;


        @SerializedName("bill")
        @Expose
        String bill;

        @SerializedName("age")
        @Expose
        String age;

        @SerializedName("gender")
        @Expose
        String gender;

        @SerializedName("patient")
        @Expose
        Object patient;


       public int getBillId() {
           return billId;
       }

       public void setBillId(int billId) {
           this.billId = billId;
       }

       public String getBillno() {
           return billno;
       }

       public void setBillno(String billno) {
           this.billno = billno;
       }

       public String getRef() {
           return ref;
       }

       public void setRef(String ref) {
           this.ref = ref;
       }

       public String getAmount() {
           return amount;
       }

       public void setAmount(String amount) {
           this.amount = amount;
       }

       public String getPayerName() {
           return payerName;
       }

       public void setPayerName(String payerName) {
           this.payerName = payerName;
       }

       public String getPayerEmail() {
           return payerEmail;
       }

       public String getQuantity() {
           return quantity;
       }

       public void setQuantity(String quantity) {
           this.quantity = quantity;
       }

       public void setPayerEmail(String payerEmail) {
           this.payerEmail = payerEmail;
       }

       public String getPayerPhone() {
           return PayerPhone;
       }

       public void setPayerPhone(String payerPhone) {
           PayerPhone = payerPhone;
       }

       public String getDateGenerated() {
           return dateGenerated;
       }

       public void setDateGenerated(String dateGenerated) {
           this.dateGenerated = dateGenerated;
       }

       public String getDateSynced() {
           return dateSynced;
       }

       public void setDateSynced(String dateSynced) {
           this.dateSynced = dateSynced;
       }

       public String getTimeGenerated() {
           return timeGenerated;
       }

       public void setTimeGenerated(String timeGenerated) {
           this.timeGenerated = timeGenerated;
       }

       public String getTimeSynced() {
           return timeSynced;
       }

       public void setTimeSynced(String timeSynced) {
           this.timeSynced = timeSynced;
       }

       public int getMdaID() {
           return mdaID;
       }

       public void setMdaID(int mdaID) {
           this.mdaID = mdaID;
       }

       public String getBiller() {
           return biller;
       }

       public void setBiller(String biller) {
           this.biller = biller;
       }

       public int getBillerID() {
           return billerID;
       }

       public void setBillerID(int billerID) {
           this.billerID = billerID;
       }

       public String getStatus() {
           return status;
       }

       public void setStatus(String status) {
           this.status = status;
       }

       public String getRevenuehead() {
           return revenuehead;
       }

       public int getRevenueId() {
           return revenueId;
       }

       public void setRevenueId(int revenueId) {
           this.revenueId = revenueId;
       }

       public void setRevenuehead(String revenuehead) {
           this.revenuehead = revenuehead;
       }

       public String getBill() {
           return bill;
       }

       public void setBill(String bill) {
           this.bill = bill;
       }

       public String getAge() {
           return age;
       }

       public void setAge(String age) {
           this.age = age;
       }

       public String getGender() {
           return gender;
       }

       public void setGender(String gender) {
           this.gender = gender;
       }

       public Object getPatient() {
           return patient;
       }

       public void setPatient(Object patient) {
           this.patient = patient;
       }

       @Override
       public String toString() {
           return "GeneratedBill{" +
                   "billId=" + billId +
                   ", billno='" + billno + '\'' +
                   ", ref='" + ref + '\'' +
                   ", amount='" + amount + '\'' +
                   ", payerName='" + payerName + '\'' +
                   ", payerEmail='" + payerEmail + '\'' +
                   ", PayerPhone='" + PayerPhone + '\'' +
                   ", dateGenerated='" + dateGenerated + '\'' +
                   ", dateSynced='" + dateSynced + '\'' +
                   ", timeGenerated='" + timeGenerated + '\'' +
                   ", timeSynced='" + timeSynced + '\'' +
                   ", mdaID=" + mdaID +
                   ", biller='" + biller + '\'' +
                   ", billerID=" + billerID +
                   ", status='" + status + '\'' +
                   ", revenuehead='" + revenuehead + '\'' +
                   ", revenueId=" + revenueId +
                   ", quantity='" + quantity + '\'' +
                   ", bill='" + bill + '\'' +
                   ", age='" + age + '\'' +
                   ", gender='" + gender + '\'' +
                   ", patient=" + patient +
                   '}';
       }
   }
}


