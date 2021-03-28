package com.g34.quicksalon.model;

public class ManagerDetailsForView {
    private int empId;
    private String firstName;
    private String lastName;
    private String contactNum;
    private String nic;
    private String salary;

    private String address;





    public ManagerDetailsForView(int empId, String firstName, String lastName, String contactNum, String nic, String salary,  String address) {
        this.empId = empId;
        this.nic = nic;
        this.contactNum = contactNum;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;

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

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }






    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }






}
