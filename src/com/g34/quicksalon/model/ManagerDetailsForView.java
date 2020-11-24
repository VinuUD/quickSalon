package com.g34.quicksalon.model;

public class ManagerDetailsForView {
    private int empId;
    private String firstName;
    private String lastName;
    private int contactNum;
    private String nic;
    private int salary;
    private String email;
    private String address;





    public ManagerDetailsForView(int empId, String firstName, String lastName, int contactNum, String nic, int salary, String email, String address) {
        this.empId = empId;
        this.nic = nic;
        this.contactNum = contactNum;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.email = email;
        this.address = address;
    }


    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
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

    public int getContactNum() {
        return contactNum;
    }

    public void setContactNum(int contactNum) {
        this.contactNum = contactNum;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
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






}
