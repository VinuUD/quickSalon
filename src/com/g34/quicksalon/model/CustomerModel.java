package com.g34.quicksalon.model;

import com.g34.quicksalon.database.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerModel {

    public boolean addWalkingCustomers(String fName,String lName,String telephone,int qid) throws SQLException, ClassNotFoundException {

//        PreparedStatement stmt= DBConnection.getConnection().prepareStatement("INSERT INTO j4f9qe_customer (fname,lname,accountType,registeredDate) VALUES (?,?,?,?)");
//        stmt.setString(1,customerName);
//        stmt.setString(2,telephone);
//        stmt.setInt(2,qid);
//
//        int success=stmt.executeUpdate();
//        if(success<=0){
//            return false;
//        }else{
//            return true;
//        }
        return false;
    }
}
