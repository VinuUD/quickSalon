package com.g34.quicksalon.model;
public class CustomerDetails {

    private String firstName;
    private String lastName;
    private String userName;
    private String contactNo;
    private String nic;
    private String email;
    private String address;
    private String password;
    private int userID;
    private int customerID;
    private String registeredDate;

    public CustomerDetails() {
    }

    public CustomerDetails(String firstName, String lastName, String userName, String contactNo, String nic, String email, String address, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.contactNo = contactNo;
        this.nic = nic;
        this.email = email;
        this.address = address;
        this.password = password;
    }

    public CustomerDetails( int customerID, String firstName, String lastName, String contactNo, String registeredDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNo = contactNo;
        this.customerID = customerID;
        this.registeredDate = registeredDate;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
