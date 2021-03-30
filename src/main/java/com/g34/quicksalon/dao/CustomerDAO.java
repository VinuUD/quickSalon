package com.g34.quicksalon.dao;

import com.g34.quicksalon.model.Customer;
import com.g34.quicksalon.model.CustomerDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO {
    
    public int addWalkInCustomers(String fName,String lName,String telephone) throws SQLException, ClassNotFoundException;

    public boolean registerCustomer(CustomerDetails customerDetails) throws SQLException, ClassNotFoundException;

    public String getCustomerNameByID(int customerID) throws SQLException, ClassNotFoundException;

//    key = customer ID, customer name, contact number
    public ArrayList<Customer> getCustomersByKey(String key) throws SQLException, ClassNotFoundException;



}
