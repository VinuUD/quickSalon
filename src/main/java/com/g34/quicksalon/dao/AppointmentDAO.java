package com.g34.quicksalon.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.g34.quicksalon.model.Appointment;
import com.g34.quicksalon.model.AppointmentServiceVIEW;


public interface AppointmentDAO {

    public ArrayList<Appointment> getAllAppointments();

    public ArrayList<Integer> getAllAppointmentsBySP(int spId);

    public int placeAppointment(Appointment appointment) throws SQLException, ClassNotFoundException;

    public ArrayList<Appointment> getAllAppointmentDetailsByUserId(int userID);

    public ArrayList<Appointment> getAllAppointmentDetailsByEmpId(int empID);

    //Return List of all appointments for given service ID
    public ArrayList<AppointmentServiceVIEW> getAllAppointmentDetailsByServiceID(int serviceID) throws SQLException, ClassNotFoundException;


}
