package com.g34.quicksalon.dao;

import java.sql.SQLException;

public interface CustomerDAO {
    
    public int addWalkInCustomers(String fName,String lName,String telephone) throws SQLException, ClassNotFoundException;

}
