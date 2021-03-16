package com.g34.quicksalon.model;

public class OurStaff {

    private String firstName;
    private String lastName;
    private int upperStaffFlag;

    public OurStaff(String firstName, String lastName, int upperStaffFlag) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.upperStaffFlag = upperStaffFlag;
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

    public int getUpperStaffFlag() {
        return upperStaffFlag;
    }

    public void setUpperStaffFlag(int upperStaffFlag) {
        this.upperStaffFlag = upperStaffFlag;
    }
}
