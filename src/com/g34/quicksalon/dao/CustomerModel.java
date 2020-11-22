package com.g34.quicksalon.dao;

import com.g34.quicksalon.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerModel {

    public int addWalkingCustomers(String fName,String lName,String telephone) throws SQLException, ClassNotFoundException {

        Connection connection =DBConnection.getConnection();
        PreparedStatement stmt= connection.prepareStatement("INSERT INTO j4f9qe_customer (firstname,lastname,telephone,accountType,registeredDate) VALUES (?,?,?,?,?)");
        stmt.setString(1,fName);
        stmt.setString(2,lName);
        stmt.setString(3,telephone);
        stmt.setInt(4,0);
        stmt.setString(5,null);

        int success=stmt.executeUpdate();

        if(success<=0){
            return 0;
        }
        ResultSet rst=connection.createStatement().executeQuery("SELECT LAST_INSERT_ID()");
        int custId=0;
        if(rst.next()){
            custId=rst.getInt(1);
        }

        return custId;

    }


}
