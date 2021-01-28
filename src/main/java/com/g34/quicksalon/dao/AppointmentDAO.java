package com.g34.quicksalon.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.g34.quicksalon.model.Appointment;


public interface AppointmentDAO {

    public ArrayList<Appointment> getAllAppointments();

    public ArrayList<Integer> getAllAppointmentsBySP(int spId);

    public int placeAppointment(Appointment appointment) throws SQLException, ClassNotFoundException;

    public ArrayList<Appointment> getAllAppointmentDetailsByUserId(int userID);

}
