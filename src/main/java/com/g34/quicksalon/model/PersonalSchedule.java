package com.g34.quicksalon.model;

import java.sql.Time;
import java.util.Date;

public class PersonalSchedule {

    private String customerName;
    private String serviceName;
    private String startTime;
    private String endTime;
    private String date;

    private int qID;
    private int customerID;
    private int cancelledFlag;
    private String firstName;
    private String lastName;
    private String telephone;
    private int employeeID;
    private int userID;

    public PersonalSchedule() {

    }

    public PersonalSchedule(String customerName, String serviceName, String startTime,String endTime,String date) {
        this.customerName = customerName;
        this.serviceName = serviceName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
    }

    public PersonalSchedule(String serviceName, String startTime, String endTime, String date, int qID, int customerID, int cancelledFlag, String firstName, String lastName, String telephone, int employeeID, int userID) {
        this.serviceName = serviceName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.qID = qID;
        this.customerID = customerID;
        this.cancelledFlag = cancelledFlag;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.employeeID = employeeID;
        this.userID = userID;
    }

    //constructor for personal schedule UI
//    firstName,lastName,telephone,serviceName,startTime,endTime
    public PersonalSchedule(String firstName,String lastName,String telephone,String serviceName, String startTime, String endTime) {
        this.serviceName = serviceName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
    }

    //constructor for complete job UI //qID,firstName,lastName,serviceName,startTime,endTime
    public PersonalSchedule(int qID,String firstName,String lastName,String serviceName, String startTime, String endTime,String telephone) {
        this.qID=qID;
        this.serviceName = serviceName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;

    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getqID() {
        return qID;
    }

    public void setqID(int qID) {
        this.qID = qID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getCancelledFlag() {
        return cancelledFlag;
    }

    public void setCancelledFlag(int cancelledFlag) {
        this.cancelledFlag = cancelledFlag;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
