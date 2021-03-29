package com.g34.quicksalon.model;

public class Ratings {
    private int empID;
    private  int ratings;

    public Ratings(int empID, int ratings) {
        this.empID = empID;
        this.ratings = ratings;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public int getRatings() {
        return ratings;
    }

    public void setRatings(int ratings) {
        this.ratings = ratings;
    }
}
