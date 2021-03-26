package com.g34.quicksalon.model;

import java.sql.Time;
import java.util.Date;

public class Appointment {

    private int qId;
    private int customerId;
    private String date;
    private String startTime;
    private String endTime;
    private int cancelledFlag;

    private Date day;


    public Appointment() {
    }

    public Appointment(int qId, int customerId, String date, String startTime,String endTime, int cancelledFlag) {
        this.qId = qId;
        this.customerId = customerId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cancelledFlag = cancelledFlag;
    }

    public Appointment(int qId, int customerId, Date date, String startTime,String endTime, int cancelledFlag) {
        this.qId = qId;
        this.customerId = customerId;
        this.day = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cancelledFlag = cancelledFlag;
    }



    //    For load calendar data
    public Appointment(int qId, int customerId, Date date, String startTime,String endTime) {
        this.qId = qId;
        this.customerId = customerId;
        this.day = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Appointment(int qId,String date, String startTime,String endTime) {
        this.qId = qId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public int getqId() {
        return qId;
    }

    public void setqId(int qId) {
        this.qId = qId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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
}
