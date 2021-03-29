package com.g34.quicksalon.model;

public class ManagerDetails {
    private String nic;
    private String firstName;
    private String lastName;
    private String salary;
    private String enrollDate;
    private String resignDate;
    private int isUpperStaffFlag;
    private int onLeaveFlag;
    private int removedFlag;
    private String cNum;
    private String email;
    private String address;

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
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

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(String enrollDate) {
        this.enrollDate = enrollDate;
    }

    public String getResignDate() {
        return resignDate;
    }

    public void setResignDate(String resignDate) {
        this.resignDate = resignDate;
    }

    public int getIsUpperStaffFlag() {
        return isUpperStaffFlag;
    }

    public void setIsUpperStaffFlag(int isUpperStaffFlag) {
        this.isUpperStaffFlag = isUpperStaffFlag;
    }

    public int getOnLeaveFlag() {
        return onLeaveFlag;
    }

    public void setOnLeaveFlag(int onLeaveFlag) {
        this.onLeaveFlag = onLeaveFlag;
    }

    public int getRemovedFlag() {
        return removedFlag;
    }

    public void setRemovedFlag(int removedFlag) {
        this.removedFlag = removedFlag;
    }

    public String getcNum() {
        return cNum;
    }

    public void setcNum(String cNum) {
        this.cNum = cNum;
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



    

	public ManagerDetails(String nic, String firstName, String lastName, String salary, String enrollDate, String resignDate, int isUpperStaffFlag, int onLeaveFlag, int removedFlag, String cNum, String email, String address) {
        this.nic = nic;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.enrollDate = enrollDate;
        this.resignDate = resignDate;
        this.isUpperStaffFlag = isUpperStaffFlag;
        this.onLeaveFlag = onLeaveFlag;
        this.removedFlag = removedFlag;
        this.cNum = cNum;
        this.email = email;
        this.address = address;
    }


    public ManagerDetails(String nic, String firstName, String lastName, String salary, String enrollDate, String resignDate, int isUpperStaffFlag, int onLeaveFlag, int removedFlag, String cNum, String address) {
        this.nic = nic;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.enrollDate = enrollDate;
        this.resignDate = resignDate;
        this.isUpperStaffFlag = isUpperStaffFlag;
        this.onLeaveFlag = onLeaveFlag;
        this.removedFlag = removedFlag;
        this.cNum = cNum;
        this.address = address;
    }
}
