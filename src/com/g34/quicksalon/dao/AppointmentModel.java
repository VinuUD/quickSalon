package com.g34.quicksalon.model;

import com.g34.quicksalon.database.DBConnection;
import com.g34.quicksalon.entity.Appointment;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class AppointmentModel {

    private ArrayList<Appointment> appointments=new ArrayList<>();

    public ArrayList<Appointment> getAllAppointments() {
        try {
            ResultSet resultSet = DBConnection.getConnection().createStatement().executeQuery("SELECT * FROM j4f9qe_appointments;");
            while (resultSet.next()) {
                appointments.add(new Appointment(resultSet.getInt(1), resultSet.getInt(2), resultSet.getDate(3), resultSet.getTime(4), resultSet.getTime(5),  resultSet.getInt(6)));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return appointments;
    }

    public ArrayList<Integer> getAllAppointmentsBySP(int spId) {
          ArrayList<Integer> qIds=new ArrayList<Integer>();
        try {
            PreparedStatement stmt=DBConnection.getConnection().prepareStatement("SELECT qID FROM j4f9qe_appointmentsassigned WHERE employeeID=?;");
            stmt.setInt(1,spId);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {

               qIds.add(resultSet.getInt(1));

            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return qIds;
    }

    public int placeAppointment(Appointment appointment) throws SQLException, ClassNotFoundException {

        Connection connection =DBConnection.getConnection();
        PreparedStatement stmt= connection.prepareStatement("INSERT INTO j4f9qe_appointments (customerID,date,startTime,endTime,cancelledFlag) VALUES (?,?,?,?,?)");
        stmt.setInt(1,appointment.getCustomerId());
        stmt.setDate(2,(Date)appointment.getDate());
        stmt.setTime(3, appointment.getStartTime());
        stmt.setTime(4, appointment.getEndTime());
        stmt.setInt(5,0);

        int success=stmt.executeUpdate();

        if(success<=0){
            return 0;
        }
        ResultSet rst=connection.createStatement().executeQuery("SELECT LAST_INSERT_ID()");
        int qId=0;
        if(rst.next()){
            qId=rst.getInt(1);
        }

        return qId;
    }

}
