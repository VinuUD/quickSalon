package com.g34.quicksalon.entity;

public class ManagerDetails {
    private String nic;
    private String firstName;
    private String lastName;
    private int salary;
    private String enrollDate;
    private String resignDate;
    private int isUpperStaffFlag;
    private int onLeaveFlag;
    private int removedFlag;
    private int cNum;

    

	public ManagerDetails(String nic, String firstName, String lastName, int salary, String enrollDate, String resignDate, int isUpperStaffFlag, int onLeaveFlag, int removedFlag, int cNum) {
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
    }

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


    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
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
    
    public int getcNum() {
		return cNum;
	}

	public void setcNum(int cNum) {
		this.cNum = cNum;
	}



}
