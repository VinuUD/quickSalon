package com.g34.quicksalon.model;

public class Complaint {

    private int complaintID ;
    private int customerID ;
    private String date ;
    private String time ;
    private String details ;

    public Complaint(int complaintID , int customerID , String date , String time , String details ){

        this.complaintID = complaintID;
        this.customerID = customerID;
        this.date = date;
        this.time = time;
        this.details = details;
    }

    public int getComplaintID() { return complaintID; }

    public void setComplaintID(int complaintID) { this.complaintID = complaintID; }

    public int getCustomerID() { return customerID; }

    public void setCustomerID(int customerID) { this.customerID = customerID; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }

    public void setTime(String time) { this.time = time; }

    public String getDetails() { return details; }

    public void setDetails(String details) { this.details = details; }
}
