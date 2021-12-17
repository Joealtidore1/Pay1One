package com.pay1onet.fmca.models;

public class DepartmentsModel {
    int id;
    int deptId;
    String deptName;
    String deptAbbr;

    public DepartmentsModel(int id, int deptId, String deptName, String deptAbbr) {
        this.id = id;
        this.deptId = deptId;
        this.deptName = deptName;
        this.deptAbbr = deptAbbr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptAbbr() {
        return deptAbbr;
    }

    public void setDeptAbbr(String deptAbbr) {
        this.deptAbbr = deptAbbr;
    }

    @Override
    public String toString() {
        return "DepartmentsModel{" +
                "deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                ", deptAbbr='" + deptAbbr + '\'' +
                '}';
    }
}
