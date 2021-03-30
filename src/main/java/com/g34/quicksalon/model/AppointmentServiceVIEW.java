package com.g34.quicksalon.model;

import java.util.Date;

public class AppointmentServiceVIEW {

    private int qID;
    private String date;
    private String startTime;
    private String endTime;
    private int cancelledFlag;
    private int serviceID;
    private int employeeID;
    private Date day;


    public AppointmentServiceVIEW(int qID, String date, String startTime, String endTime, int cancelledFlag, int serviceID, int employeeID) {
        this.qID = qID;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cancelledFlag = cancelledFlag;
        this.serviceID = serviceID;
        this.employeeID = employeeID;
    }

    public AppointmentServiceVIEW() {
    }
    public AppointmentServiceVIEW(int qID, Date date, String startTime) {
        this.qID = qID;
        this.day = date;
        this.startTime = startTime;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public int getqID() {
        return qID;
    }

    public void setqID(int qID) {
        this.qID = qID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public int getCancelledFlag() {
        return cancelledFlag;
    }

    public void setCancelledFlag(int cancelledFlag) {
        this.cancelledFlag = cancelledFlag;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }
}
