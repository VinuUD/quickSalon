package com.g34.quicksalon.entity;

public class UserInfo {
    private String firstName;
    private String lastName;
    private String userName;
    private Long contactNumber;
    private String nic;
    private String email;
    private String address;
    private String password;

    public UserInfo(String firstName, String lastName, String userName, Long contactNumber, String nic, String email, String address, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.contactNumber = contactNumber;
        this.nic = nic;
        this.email = email;
        this.address = address;
        this.password = password;
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

    public Long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(Long contactNumber) {
        this.contactNumber = contactNumber;
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
