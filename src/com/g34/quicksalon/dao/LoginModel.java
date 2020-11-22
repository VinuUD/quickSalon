package com.g34.quicksalon.dao;

import com.g34.quicksalon.database.DBConnection;
import com.g34.quicksalon.model.LoginInfo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class LoginModel {

    //check login and if true, return userType & userID
    public HashMap<String,Integer> getUserType(LoginInfo loginInfo) throws SQLException, ClassNotFoundException {

        HashMap<String, Integer> userDetails=new HashMap<>();

       // ArrayList<UserType> userDetails = new ArrayList<>();
        PreparedStatement stmt= DBConnection.getConnection().prepareStatement("SELECT userID,userType FROM j4f9qe_user WHERE userName=? AND password=? ;");
        stmt.setString(1,loginInfo.getUsername());
        stmt.setString(2,loginInfo.getPassword());

       ResultSet resultSet=stmt.executeQuery();
       
        if(resultSet.next()){
           userDetails.put("userID",resultSet.getInt(1));
           userDetails.put("userType",resultSet.getInt(2));
           return userDetails;
        }else{
            return null;
        }
        
    }
    
}
