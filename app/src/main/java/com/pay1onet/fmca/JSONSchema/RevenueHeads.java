package com.pay1onet.fmca.JSONSchema;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RevenueHeads {
    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("service_type_id")
    @Expose
    String serviceTypeId;

    @SerializedName("code")
    @Expose
    String code;

    @SerializedName("name")
    @Expose
    String revenueHead;

    @SerializedName("targetValidFrom")
    @Expose
    String targetValidFrom;

    @SerializedName("mda_id")
    @Expose
    Integer mdaId;

    @SerializedName("account_owner")
    @Expose
    String accountOwner;

    @SerializedName("account_name")
    @Expose
    String accountName;

    @SerializedName("account_number")
    @Expose
    String accountNumber;

    @SerializedName("bank_name")
    @Expose
    String bankName;

    @SerializedName("acount_type")
    @Expose
    String accountType;

    @SerializedName("bank_code")
    @Expose
    String bankCode;

    @SerializedName("ready")
    @Expose
    Object ready;

    @SerializedName("amount")
    @Expose
    String amount;

    @SerializedName("type")
    @Expose
    String type;

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

    @SerializedName("subs")
    @Expose
    String subs;

    @SerializedName("rrn")
    @Expose
    String rrn;

    @SerializedName("acct")
    @Expose
    String acct;

    @SerializedName("remsevtype")
    @Expose
    String remSevType;

    @SerializedName("reportsevtype")
    @Expose
    String reportSevType;

    @SerializedName("sc")
    @Expose
    String sc;

    @SerializedName("mod")
    @Expose
    String mod;





    /*@SerializedName("emr")
    @Expose
    String emr;

    @SerializedName("pricetype")
    @Expose
    String pricetype;

    @SerializedName("airport")
    @Expose
    String airport;*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(String serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRevenueHead() {
        return revenueHead;
    }

    public void setRevenueHead(String revenueHead) {
        this.revenueHead = revenueHead;
    }

    public String getTargetValidFrom() {
        return targetValidFrom;
    }

    public void setTargetValidFrom(String targetValidFrom) {
        this.targetValidFrom = targetValidFrom;
    }

    public Integer getMdaId() {
        return mdaId;
    }

    public void setMdaId(Integer mdaId) {
        this.mdaId = mdaId;
    }

    public String getAccountOwner() {
        return accountOwner;
    }

    public void setAccountOwner(String accountOwner) {
        this.accountOwner = accountOwner;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public Object getReady() {
        return ready;
    }

    public void setReady(Object ready) {
        this.ready = ready;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getSubs() {
        return subs;
    }

    public void setSubs(String subs) {
        this.subs = subs;
    }

    public String getRrn() {
        return rrn;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
    }

    public String getAcct() {
        return acct;
    }

    public void setAcct(String acct) {
        this.acct = acct;
    }

    public String getRemSevType() {
        return remSevType;
    }

    public void setRemSevType(String remSevType) {
        this.remSevType = remSevType;
    }

    public String getReportSevType() {
        return reportSevType;
    }

    public void setReportSevType(String reportSevType) {
        this.reportSevType = reportSevType;
    }

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

    /* public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }*/

    /*@SerializedName("dept")
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

    @SerializedName("subs")
    @Expose
    String subs;

    @SerializedName("emr")
    @Expose
    String emr;

    @SerializedName("pricetype")
    @Expose
    String pricetype;


    public String getPricetype() {
        return pricetype;
    }

    public void setPricetype(String pricetype) {
        this.pricetype = pricetype;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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

    public String getSubs() {
        return subs;
    }

    public void setSubs(String subs) {
        this.subs = subs;
    }

    public String getRevenueHead() {
        return revenueHead;
    }

    public void setRevenueHead(String revenueHead) {
        this.revenueHead = revenueHead;
    }

    public String getEmr() {
        return emr;
    }

    public void setEmr(String emr){
        this.emr = emr;
    }*/
}
