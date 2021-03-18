package com.g34.quicksalon.dao;

import com.g34.quicksalon.model.Leave;

import java.sql.SQLException;

public interface EmployeeManagementDAO {

    public boolean addLeave(Leave leave) throws SQLException, ClassNotFoundException;

}
