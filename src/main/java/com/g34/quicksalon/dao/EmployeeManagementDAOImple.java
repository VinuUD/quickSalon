package com.g34.quicksalon.dao;

import com.g34.quicksalon.database.DBConnection;
import com.g34.quicksalon.model.Leave;

import java.sql.*;

public class EmployeeManagementDAOImple implements EmployeeManagementDAO{

//    @Override
//    public boolean applyLeave(Leave leave) throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getConnection();
//        PreparedStatement stmt= connection.prepareStatement("INSERT INTO j4f9qe_leave (leaverID,fromDate,fromTime,toDate,toTime,leaveType) VALUES (?,?,?,?,?,?)");
//        stmt.setInt(1,leave.getLeaverID());
//        stmt.setString(2,leave.getFromDate());
//        stmt.setString(3, leave.getFromTime());
//        stmt.setString(4,leave.getToDate());
//        stmt.setString(5, leave.getToTime());
//        stmt.setString(6,leave.getLeaveType());
//
//        int success=stmt.executeUpdate();
//
//        if(success<= 0){
//            return false;
//        }
//        return true;
//    }

}
