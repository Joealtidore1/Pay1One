package com.pay1onet.fmca.JSONSchema;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SyncDepartments {
    @SerializedName("departments")
    @Expose
    List<DepartmentSchema> departments;

    @SerializedName("revenueheads")
    @Expose
    List<RevenueHeads> revenueHeads;

    @SerializedName("categories")
    @Expose
    List<CategorySchema> category;

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("message")
    @Expose
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DepartmentSchema> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentSchema> departments) {
        this.departments = departments;
    }

    public List<RevenueHeads> getRevenueHeads() {
        return revenueHeads;
    }

    public void setRevenueHeads(List<RevenueHeads> revenueHeads) {
        this.revenueHeads = revenueHeads;
    }

    public List<CategorySchema> getCategory() {
        return category;
    }

    public void setCategory(List<CategorySchema> category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
