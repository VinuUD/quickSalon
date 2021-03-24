package com.g34.quicksalon.model;

public class Service {

    private int serviceID;
    private String serviceName; 
    private String serviceDescription;
    private String timeTaken;
    private double price;
    private int holdFlag;

    public Service() {
    }

    public Service(int serviceID,String serviceName, String timeTaken) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.timeTaken = timeTaken;
    }


    public Service(int serviceID, String serviceName, String serviceDescription, String timeTaken, double price, int holdFlag) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.serviceDescription = serviceDescription;
        this.timeTaken = timeTaken;
        this.price = price;
        this.holdFlag = holdFlag;
    }

    public Service(String serviceName, String serviceDescription, String timeTaken, double price, int holdFlag) {
        this.serviceName = serviceName;
        this.serviceDescription = serviceDescription;
        this.timeTaken = timeTaken;
        this.price = price;
        this.holdFlag = holdFlag;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public String getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(String timeTaken) {
        this.timeTaken = timeTaken;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getHoldFlag() {
        return holdFlag;
    }

    public void setHoldFlag(int holdFlag) {
        this.holdFlag = holdFlag;
    }
}
