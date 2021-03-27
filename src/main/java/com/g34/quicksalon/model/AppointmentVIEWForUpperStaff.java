package com.g34.quicksalon.model;

public class AppointmentVIEWForUpperStaff {

    private int customerId;
    private int qId;
   private String custFirstName;
   private String  custLastName;
   private String telephone;
   private String serviceName;
    private String date;
    private String startTime;
    private String endTime;
    private int cancelledFlag;
   private  int complete;
   private  int  employeeID;
   private String empFirstName;
   private String empLastName;
   private int userID;

    public AppointmentVIEWForUpperStaff(int qId, String custFirstName, String custLastName, String telephone, String serviceName, String date, String startTime, String endTime, int cancelledFlag, int complete, int employeeID, String empFirstName, String empLastName, int userID) {
        this.qId = qId;
        this.custFirstName = custFirstName;
        this.custLastName = custLastName;
        this.telephone = telephone;
        this.serviceName = serviceName;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cancelledFlag = cancelledFlag;
        this.complete = complete;
        this.employeeID = employeeID;
        this.empFirstName = empFirstName;
        this.empLastName = empLastName;
        this.userID = userID;
    }

    //constructor for Dequeue View
    public AppointmentVIEWForUpperStaff(int qId, String custFirstName, String custLastName, String telephone, String serviceName, String startTime, String endTime, String empFirstName, String empLastName) {
        this.qId = qId;
        this.custFirstName = custFirstName;
        this.custLastName = custLastName;
        this.telephone = telephone;
        this.serviceName = serviceName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.empFirstName = empFirstName;
        this.empLastName = empLastName;
    }



    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getqId() {
        return qId;
    }

    public void setqId(int qId) {
        this.qId = qId;
    }

    public String getCustFirstName() {
        return custFirstName;
    }

    public void setCustFirstName(String custFirstName) {
        this.custFirstName = custFirstName;
    }

    public String getCustLastName() {
        return custLastName;
    }

    public void setCustLastName(String custLastName) {
        this.custLastName = custLastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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

    public int getComplete() {
        return complete;
    }

    public void setComplete(int complete) {
        this.complete = complete;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmpFirstName() {
        return empFirstName;
    }

    public void setEmpFirstName(String empFirstName) {
        this.empFirstName = empFirstName;
    }

    public String getEmpLastName() {
        return empLastName;
    }

    public void setEmpLastName(String empLastName) {
        this.empLastName = empLastName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
