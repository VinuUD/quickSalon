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
    public boolean registerCustomer(CustomerDetails customerDetails){
        //get DBConnection
        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            connection.setAutoCommit(false);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        int userID=0;
        Boolean success=false;

        int customerID= 0;
        try {
            //1) add Customer to customer table
            customerID = addCustomer(customerDetails,connection);
            System.out.println(customerID);
            //2)add user to j4f9qe_user table
            userID=registerUser(customerDetails,connection);
            //3)Update `j4f9qe_registeredcustomers` table
           success=addRegisteredCustomer(customerDetails,customerID,userID,connection);

            //if success
            connection.commit();
        } catch (SQLException | ClassNotFoundException throwables) {
            //exception happen then, rollback
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
        return success;
    }

    //add cutomer to j4f9qe_customer` table- returns customerID
    public int addCustomer(CustomerDetails customerDetails,Connection connection) throws SQLException, ClassNotFoundException {

        //for registeredCustomer usertype=1
        int accountType=1;

        PreparedStatement stmt= connection.prepareStatement("INSERT INTO j4f9qe_customer (firstname,lastname,telephone,accountType,registeredDate) VALUES (?,?,?,?,?)");
        stmt.setString(1,customerDetails.getFirstName());
        stmt.setString(2,customerDetails.getLastName());
        stmt.setString(3,customerDetails.getContactNo());
        stmt.setInt(4,accountType);
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
    public int registerUser(CustomerDetails customerDetails,Connection connection) throws SQLException, ClassNotFoundException {

        int userType=4;
        PreparedStatement stmt= connection.prepareStatement("INSERT INTO j4f9qe_user(email,userName,password,userType) VALUES (?,?,?,?)");
        stmt.setString(1,customerDetails.getEmail());
        stmt.setString(2,customerDetails.getUserName());
        stmt.setString(3,customerDetails.getPassword());
        stmt.setInt(4,userType);

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
    public boolean addRegisteredCustomer(CustomerDetails customerDetails, int customerID, int userID,Connection connection) throws SQLException, ClassNotFoundException {

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

    public String getCustomerNameByID(int customerID) throws SQLException, ClassNotFoundException{

        String customerName="";
        try {
            Connection connection =DBConnection.getConnection();
            PreparedStatement stmt= connection.prepareStatement("SELECT CONCAT(firstName,' ',lastName) AS fullName FROM j4f9qe_customer WHERE customerID=?;");
            stmt.setInt(1,customerID);
            ResultSet resultSet=stmt.executeQuery();

            if(resultSet.next()){
                customerName=resultSet.getString(1);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return customerName;
    }

}




