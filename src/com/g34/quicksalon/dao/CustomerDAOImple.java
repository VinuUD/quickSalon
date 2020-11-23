package com.g34.quicksalon.dao;

import com.g34.quicksalon.database.DBConnection;
import com.g34.quicksalon.model.CustomerDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CustomerDAOImple implements CustomerDAO {
    
    public int addWalkInCustomers(String fName,String lName,String telephone) throws SQLException, ClassNotFoundException {

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

    //
    public boolean registerCustomer(CustomerDetails customerDetails) throws SQLException, ClassNotFoundException{

        //1) add Customer to customer table
        int customerID=addCustomer(customerDetails);
        int userID=0;
        Boolean success=false;
        if(customerID==0){
            //rollback
        }else{
            //2)add user to j4f9qe_user table
            userID=registerUser(customerDetails,customerID);
            if(userID==0){
                //rollback
            }else{
                //3)Update `j4f9qe_registeredcustomers` table
                success=addRegisterdCustomer(customerDetails,customerID,userID);
                if(!success){
                    //Rollback
                }
            }

        }

        return success;
    }

    //add cutomer to j4f9qe_customer` table- returns customerID
    public int addCustomer(CustomerDetails customerDetails) throws SQLException, ClassNotFoundException {

        //for registeredCustomer usertype=1
        int userType=1;
        Connection connection =DBConnection.getConnection();
        PreparedStatement stmt= connection.prepareStatement("INSERT INTO j4f9qe_customer (firstname,lastname,telephone,accountType,registeredDate) VALUES (?,?,?,?,?)");
        stmt.setString(1,customerDetails.getFirstName());
        stmt.setString(2,customerDetails.getLastName());
        stmt.setString(3,customerDetails.getContactNo());
        stmt.setInt(4,userType);
        stmt.setString(5, String.valueOf(LocalDate.now()));

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

    //add user to j4f9qe_user table-returs  userID
    public int registerUser(CustomerDetails customerDetails, int customerID) throws SQLException, ClassNotFoundException {

        int userType=4;
        Connection connection =DBConnection.getConnection();
        PreparedStatement stmt= connection.prepareStatement("INSERT INTO j4f9qe_user (userName,password,userType) VALUES (?,?,?)");
        stmt.setString(1,customerDetails.getUserName());
        stmt.setString(2,customerDetails.getPassword());
        stmt.setInt(3,userType);

        int success=stmt.executeUpdate();

        if(success<=0){
            return 0;
        }
        ResultSet rst=connection.createStatement().executeQuery("SELECT LAST_INSERT_ID()");
        int userID=0;
        if(rst.next()){
            userID=rst.getInt(1);
        }
        return userID;
    }

    //Update `j4f9qe_registeredcustomers` table
    public boolean addRegisterdCustomer(CustomerDetails customerDetails, int customerID, int userID) throws SQLException, ClassNotFoundException {

        Connection connection =DBConnection.getConnection();
        PreparedStatement stmt= connection.prepareStatement("INSERT INTO j4f9qe_registeredcustomers VALUES (?,?,?,?,?)");
        stmt.setInt(1,userID);
        stmt.setInt(2,customerID);
        stmt.setString(3,customerDetails.getEmail());
        stmt.setString(4,customerDetails.getAddress());
        stmt.setString(5, customerDetails.getNic());

        int success=stmt.executeUpdate();

        if(success<=0){
            return false;
        }
        return true;
    }

}


