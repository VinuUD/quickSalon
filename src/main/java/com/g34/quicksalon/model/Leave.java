package com.g34.quicksalon.model;

import java.sql.Time;
import java.util.Date;

public class Leave {

    private int leaveID;
    private int leaverID;

    private String firstName;
    private String lastName;

    private char isApproved;
    private String fromDate;
    private String toDate;
    private String fromTime;
    private String toTime;
    private String leaveType;

    public Leave() {
    }

    public Leave(int leaveID, int leaverID, char isApproved, String fromDate, String toDate, String fromTime, String toTime, String leaveType) {
        this.leaveID = leaveID;
        this.leaverID = leaverID;
        this.isApproved = isApproved;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.leaveType = leaveType;
    }

    public Leave(int leaverID, String fromDate, String toDate, String fromTime, String toTime, String leaveType) {
        this.leaverID = leaverID;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.leaveType = leaveType;
    }

//    public Leave(int employeeID, String firstName, String lastName, String fromDate, String toDate,String leaveType,char isApproved) {
//        this.leaverID = employeeID;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.fromDate = fromDate;
//        this.toDate = toDate;
//        this.leaveType = leaveType;
//        this.isApproved=isApproved;
//    }

    public Leave(int employeeID, String firstName, String lastName, String fromDate, String toDate, String leaveType, char isApproved) {
        this.leaverID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.leaveType = leaveType;
        this.isApproved=isApproved;
    }


    public int getLeaveID() {
        return leaveID;
    }

    public void setLeaveID(int leaveID) {
        this.leaveID = leaveID;
    }

    public int getLeaverID() {
        return leaverID;
    }

    public void setLeaverID(int leaverID) {
        this.leaverID = leaverID;
    }

    public char getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(char isApproved) {
        this.isApproved = isApproved;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }
}
