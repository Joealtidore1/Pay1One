package com.pay1onet.fmca.JSONSchema;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategorySchema {
    @SerializedName("categoryID")
    @Expose
    int categoryId;

    @SerializedName("category")
    @Expose
    String category;

    @SerializedName("mda")
    @Expose
    int mda;

    @SerializedName("dept")
    @Expose
    int dept;

    @SerializedName("department")
    @Expose
    String department;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getMda() {
        return mda;
    }

    public void setMda(int mda) {
        this.mda = mda;
    }

    public int getDept() {
        return dept;
    }

    public void setDept(int dept) {
        this.dept = dept;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}

