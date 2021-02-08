package com.g34.quicksalon.model;

public class UserLoginModel {

    private int id;
    private String email;
    private String username;
    private String password;
    private int userType;

    public UserLoginModel() {
    }

    public UserLoginModel(int id, String email, String username, String password, int userType) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

}
