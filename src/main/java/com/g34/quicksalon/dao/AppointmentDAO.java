package com.g34.quicksalon.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.g34.quicksalon.model.*;


public interface AppointmentDAO {

    public ArrayList<Appointment> getAllAppointments();


    public ArrayList<Integer> getAllAppointmentsBySP(int spId);

    public int placeAppointment(Appointment appointment) throws SQLException, ClassNotFoundException;

    public ArrayList<Appointment> getAllAppointmentDetailsByUserId(int userID);

    public ArrayList<Appointment> getAllAppointmentDetailsByEmpId(int empID);

    //Return List of all appointments for given service ID
    public ArrayList<AppointmentServiceVIEW> getAllAppointmentDetailsByServiceID(int serviceID) throws SQLException, ClassNotFoundException;

    //    get all upcoming appointment by Date -- return (QId,Name,telephone,startTime,endTime,spName)
    public ArrayList<AppointmentVIEWForUpperStaff> getAppointmentDeatilsByDate(String date) throws SQLException, ClassNotFoundException;

    public ServiceProvider getLeastAppCountSp(String[] arr) throws SQLException, ClassNotFoundException;

    public int placeNewAppointment(int serviceID, int spID,int custID, String date, String startTIme, String endTime) throws SQLException, ClassNotFoundException;


//    Cancel appointment by cancelledFlag=1
    public boolean cancelAppointment(int qID) throws SQLException, ClassNotFoundException;

    //Get all appointments of today by SPID
    public ArrayList<PersonalSchedule> getTodayAppointmentsBySPID(int empID) throws SQLException, ClassNotFoundException;

    //Get all Appointment of a SP by service ID
    public ArrayList<AppointmentServiceVIEW> getAllSPAppointmentByServiceID(int serviceID,int userID) throws SQLException, ClassNotFoundException;


    //    Cancel appointment by complete=1
    public boolean dequeueAppointment(int qID) throws SQLException, ClassNotFoundException;
}
