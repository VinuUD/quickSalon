package com.g34.quicksalon.dao;

import com.g34.quicksalon.database.DBConnection;
import com.g34.quicksalon.model.Appointment;

import java.sql.*;
import java.util.ArrayList;

public class ApppointmentDAOImple implements AppointmentDAO {


    public ArrayList<Appointment> getAllAppointments() {
        ArrayList<Appointment> appointments=new ArrayList<>();
        try {
            ResultSet resultSet = DBConnection.getConnection().createStatement().executeQuery("SELECT * FROM j4f9qe_appointments;");

            while (resultSet.next()) {
                //data=(Time)resultSet.getString(4);

                appointments.add(new Appointment(resultSet.getInt(1), resultSet.getInt(2), resultSet.getDate(3), Time.valueOf(resultSet.getString(4)), Time.valueOf(resultSet.getString(5)),  resultSet.getInt(6)));
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return appointments;
    }

    //This will returns all the QIDs
    public ArrayList<Integer> getAllAppointmentsBySP(int spId) {
          ArrayList<Integer> qIds=new ArrayList<Integer>();
        try {
            PreparedStatement stmt=DBConnection.getConnection().prepareStatement("SELECT qID FROM j4f9qe_appointmentsassigned WHERE employeeID=?;");

            // "SELECT qID FROM j4f9qe_appointmentsassigned WHERE employeeID LIKE "*";"
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

    //This will returns all the appointment details of specific userID
    public ArrayList<Appointment> getAllAppointmentDetailsByUserId(int userID) {

        ArrayList<Appointment> appointments=new ArrayList<Appointment>();

        try {
            PreparedStatement stmt=DBConnection.getConnection().prepareStatement("SELECT apt.qID,apt.customerID,apt.date,apt.startTime,apt.endTime,apt.cancelledFlag FROM j4f9qe_appointments apt INNER JOIN j4f9qe_appointmentsassigned ass ON ass.qID=apt.qID INNER JOIN j4f9qe_employee emp ON emp.employeeID=ass.employeeID WHERE userID=?;");

            stmt.setInt(1,userID);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                appointments.add(new Appointment(resultSet.getInt(1),resultSet.getInt(2),resultSet.getDate(3),Time.valueOf(resultSet.getString(4)),Time.valueOf(resultSet.getString(4)),resultSet.getInt(6)));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return appointments;
    }




}
