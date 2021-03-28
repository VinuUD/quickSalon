package com.g34.quicksalon.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.g34.quicksalon.model.Appointment;
import com.g34.quicksalon.model.AppointmentServiceVIEW;
import com.g34.quicksalon.model.ServiceProvider;


public interface AppointmentDAO {

    public ArrayList<Appointment> getAllAppointments();

    public ArrayList<Integer> getAllAppointmentsBySP(int spId);

    public int placeAppointment(Appointment appointment) throws SQLException, ClassNotFoundException;

    public ArrayList<Appointment> getAllAppointmentDetailsByUserId(int userID);

    public ArrayList<Appointment> getAllAppointmentDetailsByEmpId(int empID);

    //Return List of all appointments for given service ID
    public ArrayList<AppointmentServiceVIEW> getAllAppointmentDetailsByServiceID(int serviceID) throws SQLException, ClassNotFoundException;

    public ServiceProvider getLeastAppCountSp(String[] arr) throws SQLException, ClassNotFoundException;

    public int placeNewAppointment(int serviceID, int spID,int custID, String date, String startTIme, String endTime) throws SQLException, ClassNotFoundException;


}
