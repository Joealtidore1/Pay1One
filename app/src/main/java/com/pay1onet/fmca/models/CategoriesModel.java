package com.pay1onet.fmca.models;

public class CategoriesModel {
    int id, categoryId,dept;
    String  category, department;

    public CategoriesModel(int id, int categoryId, String category, int dept, String department) {
        this.id = id;
        this.categoryId = categoryId;
        this.category = category;
        this.dept = dept;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
