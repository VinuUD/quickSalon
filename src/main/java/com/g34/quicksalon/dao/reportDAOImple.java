package com.g34.quicksalon.dao;

import com.g34.quicksalon.database.DBConnection;
import com.g34.quicksalon.model.Complaint;
import com.g34.quicksalon.model.Leave;
import com.g34.quicksalon.model.Leave2;
import com.g34.quicksalon.model.Service;

import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class reportDAOImple implements reportDAO {


    ArrayList<Leave2> leaveArray = new ArrayList<>();
    ArrayList<Complaint> complaintArray = new ArrayList<>();


    public ArrayList<Complaint> getComplaintAll( String from_date , String to_date) throws SQLException, ClassNotFoundException {

        ResultSet rs =   DBConnection.getConnection().createStatement().executeQuery("select * from j4f9qe_complaint where date >= '"+ from_date + "' &&  date <= '"+ to_date +"'");
        while(rs.next()){
            complaintArray.add( new Complaint(rs.getInt(1) , rs.getInt(2) ,rs.getDate(3).toString() ,rs.getTime(4).toString(),rs.getString(5)));
        }
        return complaintArray ;
    }
    public ArrayList<Complaint> getComplaintServiceAndFrom(String from_date ) throws SQLException, ClassNotFoundException {
        ResultSet rs =   DBConnection.getConnection().createStatement().executeQuery("select * from j4f9qe_complaint where date >= '"+ from_date + "'");
        while(rs.next()){
            complaintArray.add( new Complaint(rs.getInt(1) , rs.getInt(2) ,rs.getDate(3).toString() ,rs.getTime(4).toString(),rs.getString(5)));
        }
        return complaintArray ;
    }
    public ArrayList<Complaint> getComplaintServiceAndTo(String to_date) throws SQLException, ClassNotFoundException {
        ResultSet rs =   DBConnection.getConnection().createStatement().executeQuery("select * from j4f9qe_complaint where  date <= '"+ to_date +"'");
        while(rs.next()){
            complaintArray.add( new Complaint(rs.getInt(1) , rs.getInt(2) ,rs.getDate(3).toString() ,rs.getTime(4).toString(),rs.getString(5)));
        }
        return complaintArray ;
    }
    public ArrayList<Complaint> getComplaintService() throws SQLException, ClassNotFoundException {
        ResultSet rs =   DBConnection.getConnection().createStatement().executeQuery("select * from j4f9qe_complaint ");
        while(rs.next()){
            complaintArray.add( new Complaint(rs.getInt(1) , rs.getInt(2) ,rs.getDate(3).toString() ,rs.getTime(4).toString(),rs.getString(5)));
        }
        return complaintArray ;
    }

    public ArrayList<Leave2> getLeaveAll( String from_date , String to_date) throws SQLException, ClassNotFoundException {

        ResultSet rst =   DBConnection.getConnection().createStatement().executeQuery("select * from j4f9qe_leave where fromDate >'"+ from_date + "' &&  toDate <= '"+ to_date +"'");
        while(rst.next()){
            leaveArray.add( new Leave2(rst.getInt(1) , rst.getInt(2) , rst.getString(3) ,rst.getDate(4).toString() ,rst.getTime(5).toString(), rst.getDate(6).toString() , rst.getTime(7).toString() , rst.getString(8) ));
        }
        return leaveArray ;
    }
    public ArrayList<Leave2> getLeaveServiceAndFrom( String from_date ) throws SQLException, ClassNotFoundException {
        ResultSet rst =   DBConnection.getConnection().createStatement().executeQuery("select * from j4f9qe_leave where fromDate >= '"+ from_date + "'");
        while(rst.next()){
            leaveArray.add( new Leave2(rst.getInt(1) , rst.getInt(2) , rst.getString(3) ,rst.getDate(4).toString() ,rst.getTime(5).toString(), rst.getDate(6).toString() , rst.getTime(7).toString() , rst.getString(8) ));
        }
        return leaveArray ;
    }
    public ArrayList<Leave2> getLeaveServiceAndTo(String to_date) throws SQLException, ClassNotFoundException {
        ResultSet rst =   DBConnection.getConnection().createStatement().executeQuery("select * from j4f9qe_leave where  toDate <= '"+ to_date +"'");
        while(rst.next()){
            leaveArray.add( new Leave2(rst.getInt(1) , rst.getInt(2) , rst.getString(3) ,rst.getDate(4).toString() ,rst.getTime(5).toString(), rst.getDate(6).toString() , rst.getTime(7).toString() , rst.getString(8) ));
        }
        return leaveArray ;
    }
    public ArrayList<Leave2> getLeaveService( ) throws SQLException, ClassNotFoundException {
        ResultSet rst =   DBConnection.getConnection().createStatement().executeQuery("select * from j4f9qe_leave ");
        while(rst.next()){
            leaveArray.add( new Leave2(rst.getInt(1) , rst.getInt(2) , rst.getString(3) ,rst.getDate(4).toString() ,rst.getTime(5).toString(), rst.getDate(6).toString() , rst.getTime(7).toString() , rst.getString(8) ));
        }
        return leaveArray ;
    }



}
