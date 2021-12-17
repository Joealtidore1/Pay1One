package com.pay1onet.fmca.JSONSchema;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JSONResponse {
    @SerializedName("response")
    @Expose
    ResponseSchema responseSchema;

    @SerializedName("revenueheads")
    @Expose
    List<RevenueHeads> revenueHeads;

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("defaults")
    @Expose
    AppDefaultsSchema appDefaultsSchema;

    @SerializedName("departments")
    @Expose
    List<DepartmentSchema> departmentSchemas;

    @SerializedName("categories")
    @Expose
    List<CategorySchema> categorySchemas;

    @SerializedName("wallet")
    @Expose
    WalletSchema walletSchema;

    @SerializedName("message")
    @Expose
    String message;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @SerializedName("token")
    @Expose
    String token;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public WalletSchema getWalletSchema() {
        return walletSchema;
    }

    public void setWalletSchema(WalletSchema walletSchema) {
        this.walletSchema = walletSchema;
    }

    public ResponseSchema getResponseSchema() {
        return responseSchema;
    }

    public void setResponseSchema(ResponseSchema responseSchema) {
        this.responseSchema = responseSchema;
    }

    public List<RevenueHeads> getRevenueHeads() {
        return revenueHeads;
    }

    public void setRevenueHeads(List<RevenueHeads> revenueHeads) {
        this.revenueHeads = revenueHeads;
    }

    public String isStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AppDefaultsSchema getAppDefaultsSchema() {
        return appDefaultsSchema;
    }

    public void setAppDefaultsSchema(AppDefaultsSchema appDefaultsSchema) {
        this.appDefaultsSchema = appDefaultsSchema;
    }


    public List<DepartmentSchema> getDepartmentSchemas() {
        return departmentSchemas;
    }

    public void setDepartmentSchemas(List<DepartmentSchema> departmentSchemas) {
        this.departmentSchemas = departmentSchemas;
    }

    public List<CategorySchema> getCategorySchemas() {
        return categorySchemas;
    }

    public void setCategorySchemas(List<CategorySchema> categorySchemas) {
        this.categorySchemas = categorySchemas;
    }
}
