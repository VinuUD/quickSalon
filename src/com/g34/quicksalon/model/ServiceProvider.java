package com.g34.quicksalon.entity;

import java.util.Date;

public class ServiceProvider {
    private int employeeId;
    private String nicNo;
    private String firstName;
    private String lastName;
    private float salary;
    private Date enrollDate;
    private Date resignDate;
    private int isUpperStaffFlag;
    private int onLeaveFlag;
    private int removedFlag;

    public ServiceProvider() {
    }

    public ServiceProvider(int employeeId, String firstName, String lastName) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public ServiceProvider(int employeeId, String nicNo, String firstName, String lastName, float salary, Date enrollDate, Date resignDate, int isUpperStaffFlag, int onLeaveFlag, int removedFlag) {
        this.employeeId = employeeId;
        this.nicNo = nicNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.enrollDate = enrollDate;
        this.resignDate = resignDate;
        this.isUpperStaffFlag = isUpperStaffFlag;
        this.onLeaveFlag = onLeaveFlag;
        this.removedFlag = removedFlag;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getNicNo() {
        return nicNo;
    }

    public void setNicNo(String nicNo) {
        this.nicNo = nicNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    public Date getResignDate() {
        return resignDate;
    }

    public void setResignDate(Date resignDate) {
        this.resignDate = resignDate;
    }

    public int getIsUpperStaffFlag() {
        return isUpperStaffFlag;
    }

    public void setIsUpperStaffFlag(int isUpperStaffFlag) {
        this.isUpperStaffFlag = isUpperStaffFlag;
    }

    public int getOnLeaveFlag() {
        return onLeaveFlag;
    }

    public void setOnLeaveFlag(int onLeaveFlag) {
        this.onLeaveFlag = onLeaveFlag;
    }

    public int getRemovedFlag() {
        return removedFlag;
    }

    public void setRemovedFlag(int removedFlag) {
        this.removedFlag = removedFlag;
    }
}
