package com.g34.quicksalon.dao;

import com.g34.quicksalon.model.AppointmentDetailsForLeave;
import com.g34.quicksalon.model.Leave;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OwnerDAO {
    public ArrayList<Leave> getLeaversDetails() throws SQLException, ClassNotFoundException;

    public ArrayList<AppointmentDetailsForLeave> appointmentDetails() throws SQLException, ClassNotFoundException;

    public int addLeave(int id) throws SQLException, ClassNotFoundException;

    public boolean cancelLeave(int id) throws SQLException, ClassNotFoundException;


    public int removeManager(int id, String password) throws SQLException;

    public int updateManager(int id, String cNum, int salary, String address) throws SQLException;

    public boolean applyLeave(Leave leave) throws SQLException, ClassNotFoundException;

    public boolean setRemovedFlagOfSp(int empid) throws SQLException, ClassNotFoundException;

    public int removeServiceProvider(int id, String password) throws SQLException;

    public int addClosingDates(String fromDate, String toDate) throws SQLException, ClassNotFoundException;

    public String getOwnerEmail() throws SQLException, ClassNotFoundException;


}
