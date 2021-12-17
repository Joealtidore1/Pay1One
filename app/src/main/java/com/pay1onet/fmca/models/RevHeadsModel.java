package com.pay1onet.fmca.models;

public class RevHeadsModel {
    int id , revenueId;
    String serviceTypeId;
    String revenueCode;
    String revenueHead;
    String targetValidFrom;
    Integer mdaId;
    String accountOwner;
    String accountName;
    String accountNumber;
    String bankName;
    String accountType;
    String bankCode;
    Object ready;
    String amount;
    String type;
    String airport;
    String dept;
    String department;
    String cate;
    String category;
    String subs;
    String rrn;
    String acct;
    String remSevType;
    String reportSevType;
    String sc;
    String mod;

    public RevHeadsModel(int id,
                         int revenueId,
                         String serviceTypeId,
                         String revenueCode,
                         String revenueHead,
                         String targetValidFrom,
                         Integer mdaId,
                         String accountOwner,
                         String accountName,
                         String accountNumber,
                         String bankName,
                         String accountType,
                         String bankCode,
                         Object ready,
                         String amount,
                         String type,
                         String dept,
                         String department,
                         String cate,
                         String category,
                         String subs,
                         String rrn,
                         String acct,
                         String remSevType,
                         String reportSevType,
                         String sc,
                         String mod) {
        this.id = id;
        this.revenueId = revenueId;
        this.serviceTypeId = serviceTypeId;
        this.revenueCode = revenueCode;
        this.revenueHead = revenueHead;
        this.targetValidFrom = targetValidFrom;
        this.mdaId = mdaId;
        this.accountOwner = accountOwner;
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.bankName = bankName;
        this.accountType = accountType;
        this.bankCode = bankCode;
        this.ready = ready;
        this.amount = amount;
        this.type = type;
        this.dept = dept;
        this.department = department;
        this.cate = cate;
        this.category = category;
        this.subs = subs;
        this.rrn = rrn;
        this.acct = acct;
        this.remSevType = remSevType;
        this.reportSevType = reportSevType;
        this.sc = sc;
        this.mod = mod;
    }

    public RevHeadsModel(String revenueHead, String revenueCode) {
        this.revenueHead = revenueHead;
        this.revenueCode = revenueCode;
    }

    public RevHeadsModel(int id,
                         int revenueId,
                         String serviceTypeId,
                         String revenueCode,
                         String revenueHead,
                         String targetValidFrom,
                         Integer mdaId,
                         String accountOwner,
                         String accountName,
                         String accountNumber,
                         String bankName,
                         String accountType,
                         String bankCode,
                         Object ready,
                         String amount,
                         String type,
                         String airport) {
        this.id = id;
        this.revenueId = revenueId;
        this.serviceTypeId = serviceTypeId;
        this.revenueCode = revenueCode;
        this.revenueHead = revenueHead;
        this.targetValidFrom = targetValidFrom;
        this.mdaId = mdaId;
        this.accountOwner = accountOwner;
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.bankName = bankName;
        this.accountType = accountType;
        this.bankCode = bankCode;
        this.ready = ready;
        this.amount = amount;
        this.type = type;
        this.airport = airport;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRevenueId() {
        return revenueId;
    }

    public void setRevenueId(int revenueId) {
        this.revenueId = revenueId;
    }

    public String getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(String serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getRevenueCode() {
        return revenueCode;
    }

    public void setRevenueCode(String revenueCode) {
        this.revenueCode = revenueCode;
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

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    /*public RevHeadsModel(int id, String revenueHead, String revenueCode, int revenueId, String amount, String dept, String department, String cate, String category, String subs, String emr, String priceType) {
        this.id = id;
        this.revenueHead = revenueHead;
        this.revenueCode = revenueCode;
        this.revenueId = revenueId;
        this.amount = amount;
        this.dept = dept;
        this.department = department;
        this.cate = cate;
        this.category = category;
        this.subs = subs;
        this.emr = emr;
        this.priceType = priceType;
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

    public String getRevenueHead() {
        return revenueHead;
    }

    public void setRevenueHead(String revenueHead) {
        this.revenueHead = revenueHead;
    }

    public String getRevenueCode() {
        return revenueCode;
    }

    public void setRevenueCode(String revenueCode) {
        this.revenueCode = revenueCode;
    }

    public int getRevenueId() {
        return revenueId;
    }

    public void setRevenueId(int revenueId) {
        this.revenueId = revenueId;
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

    public String getEmr() {
        return emr;
    }

    public void setEmr(String emr) {
        this.emr = emr;
    }*/
}
