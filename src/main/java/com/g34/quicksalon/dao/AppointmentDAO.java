package com.g34.quicksalon.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.g34.quicksalon.model.Appointment;
import com.g34.quicksalon.model.AppointmentServiceVIEW;
import com.g34.quicksalon.model.AppointmentVIEWForUpperStaff;
import com.g34.quicksalon.model.ServiceProvider;



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
    

//    Cancel appointment by cancelledFlag=1
    public boolean cancelAppointment(int qID) throws SQLException, ClassNotFoundException;
}
