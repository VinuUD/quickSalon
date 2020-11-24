package com.g34.quicksalon.dao;

import com.g34.quicksalon.model.CustomerDetails;

import java.sql.SQLException;

public interface CustomerDAO {
    
    public int addWalkInCustomers(String fName,String lName,String telephone) throws SQLException, ClassNotFoundException;

    public boolean registerCustomer(CustomerDetails customerDetails) throws SQLException, ClassNotFoundException;

    public String getCustomerNameByID(int customerID) throws SQLException, ClassNotFoundException;


}
