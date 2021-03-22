package com.g34.quicksalon.model;

public class AppointmentDetailsForLeave {
    private String date;
    private String time;
    private int serviceId;
    private int employeeId;

    public AppointmentDetailsForLeave(String date, String time, int serviceId, int employeeId) {
        this.date = date;
        this.time = time;
        this.serviceId = serviceId;
        this.employeeId = employeeId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
