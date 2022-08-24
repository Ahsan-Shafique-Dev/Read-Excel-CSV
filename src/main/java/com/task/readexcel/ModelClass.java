package com.task.readexcel;

public class ModelClass {
    int srNo;
    String name;
    String department;

    public ModelClass() {
    }

    public ModelClass(int id, String name, String lasrName) {
        this.srNo = id;
        this.name = name;
        this.department = lasrName;
    }

    public int getSrNo() {
        return srNo;
    }

    public void setSrNo(int srNo) {
        this.srNo = srNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "ModelClass{" +
                "SrNo = " + srNo +
                ", name = '" + name + '\'' +
                ", Department = '" + department + '\'' +
                '}';
    }
}

