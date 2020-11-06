package com.g34.quicksalon.model;

public class AppointmentModel {
    private int qId;
    private int customerId;
    private  String date;
    private String startTime;
    private String endtTime;

    public AppointmentModel() {
    }

    public AppointmentModel(int qId, int customerId, String date, String startTime, String endtTime) {
        this.qId = qId;
        this.customerId = customerId;
        this.date = date;
        this.startTime = startTime;
        this.endtTime = endtTime;
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

    public String getEndtTime() {
        return endtTime;
    }

    public void setEndtTime(String endtTime) {
        this.endtTime = endtTime;
    }
}
