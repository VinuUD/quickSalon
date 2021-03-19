package com.g34.quicksalon.model;

import java.util.Date;

public class ServiceProvider {
    private int employeeId;
    private String firstName;
    private String lastName;
    private String userName;
    private String nicNo;
    private String email;
    private float salary;
    private String password;

    private Date enrollDate;
    private Date resignDate;
    private int isUpperStaffFlag;
    private int onLeaveFlag;
    private int removedFlag;
    private int userID;

    public ServiceProvider() {
    }

    public ServiceProvider(int employeeId, String firstName, String lastName) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public ServiceProvider(int employeeId, String firstName, String lastName, String userName, String nicNo, String email, float salary, String password, Date enrollDate, Date resignDate, int isUpperStaffFlag, int onLeaveFlag, int removedFlag,int userID) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.nicNo = nicNo;
        this.email = email;
        this.salary = salary;
        this.password = password;
        this.enrollDate = enrollDate;
        this.resignDate = resignDate;
        this.isUpperStaffFlag = isUpperStaffFlag;
        this.onLeaveFlag = onLeaveFlag;
        this.removedFlag = removedFlag;
        this.userID = userID;
    }

    public ServiceProvider(int employeeId, String nicNo, String firstName, String lastName, float salary, Date enrollDate, Date resignDate, int isUpperStaffFlag, int onLeaveFlag, int removedFlag) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nicNo = nicNo;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNicNo() {
        return nicNo;
    }

    public void setNicNo(String nicNo) {
        this.nicNo = nicNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
