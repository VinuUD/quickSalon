package com.g34.quicksalon.dao;

import com.g34.quicksalon.database.DBConnection;
import com.g34.quicksalon.model.Complaint;
import com.g34.quicksalon.model.Leave;
import com.g34.quicksalon.model.Leave2;
import com.g34.quicksalon.model.Service;

import java.sql.SQLException;
import java.util.ArrayList;

public interface reportDAO {

    public ArrayList<Complaint> getComplaintAll( String from_date , String to_date) throws SQLException, ClassNotFoundException;
    public ArrayList<Complaint> getComplaintServiceAndFrom( String from_date ) throws SQLException, ClassNotFoundException;
    public ArrayList<Complaint> getComplaintServiceAndTo( String to_date) throws SQLException, ClassNotFoundException;
    public ArrayList<Complaint> getComplaintService( ) throws SQLException, ClassNotFoundException;

    public ArrayList<Leave2> getLeaveAll( String from_date , String to_date) throws SQLException, ClassNotFoundException;
    public ArrayList<Leave2> getLeaveServiceAndFrom( String from_date ) throws SQLException, ClassNotFoundException;
    public ArrayList<Leave2> getLeaveServiceAndTo(  String to_date) throws SQLException, ClassNotFoundException;
    public ArrayList<Leave2> getLeaveService( ) throws SQLException, ClassNotFoundException;


    }
