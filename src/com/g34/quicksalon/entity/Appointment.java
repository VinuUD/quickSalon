package com.g34.quicksalon.entity;

import java.sql.Time;
import java.util.Date;

public class Appointment {

    private int qId;
    private int customerId;
    private Date date;
    private Time startTime;
    private Time endTime;
    private int cancelledFlag;

    public Appointment() {
    }

    public Appointment(int qId, int customerId, Date date, Time startTime, Time endTime, int cancelledFlag) {
        this.qId = qId;
        this.customerId = customerId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cancelledFlag = cancelledFlag;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endtTime) {
        this.endTime = endtTime;
    }

    public int getCancelledFlag() {
        return cancelledFlag;
    }

    public void setCancelledFlag(int cancelledFlag) {
        this.cancelledFlag = cancelledFlag;
    }
}
