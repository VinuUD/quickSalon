package com.g34.quicksalon.model;

public class Customer {
    private int customerID;
    private String firstName;
    private String lastName;
    private String telephone;
    private String registeredDate;
    private int accountType;

    public Customer() {
    }

    public Customer(int customerID, String firstName, String lastName, String telephone, String registeredDate, int accountType) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.registeredDate = registeredDate;
        this.accountType = accountType;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
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

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }
}
