package com.g34.quicksalon.dao;

import com.g34.quicksalon.database.DBConnection;
import com.g34.quicksalon.model.Appointment;
import com.g34.quicksalon.model.AppointmentServiceVIEW;
import com.g34.quicksalon.model.ServiceProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class ApppointmentDAOImple implements AppointmentDAO {


    public ArrayList<Appointment> getAllAppointments() {
        ArrayList<Appointment> appointments=new ArrayList<>();
        try {
            ResultSet resultSet = DBConnection.getConnection().createStatement().executeQuery("SELECT * FROM j4f9qe_appointments WHERE cancelledFlag=0;");

            while (resultSet.next()) {
                //data=(Time)resultSet.getString(4);
                appointments.add(new Appointment(resultSet.getInt(1), resultSet.getInt(2), resultSet.getDate(3), resultSet.getString(4),resultSet.getString(5)));
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
        stmt.setString(2,appointment.getDate());
        stmt.setString(3, appointment.getStartTime());
        stmt.setString(4, appointment.getEndTime());
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
                appointments.add(new Appointment(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getInt(6)));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return appointments;
    }

       //This will returns all the appointment details of specific employeeID
       public ArrayList<Appointment> getAllAppointmentDetailsByEmpId(int empID) {
        ArrayList<Appointment> appointments=new ArrayList<Appointment>();
        try {
            PreparedStatement stmt=DBConnection.getConnection().prepareStatement("SELECT apt.qID,apt.customerID,apt.date,apt.startTime,apt.endTime,apt.cancelledFlag FROM j4f9qe_appointments apt INNER JOIN j4f9qe_appointmentsassigned ass ON ass.qID=apt.qID WHERE ass.employeeID=(?) AND apt.cancelledFlag=0;");
            stmt.setInt(1,empID);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                appointments.add(new Appointment(resultSet.getInt(1),resultSet.getInt(2),resultSet.getDate(3),resultSet.getString(4),resultSet.getString(5),resultSet.getInt(6)));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return appointments;
    }

    @Override
    public ArrayList<AppointmentServiceVIEW> getAllAppointmentDetailsByServiceID(int serviceID) throws SQLException, ClassNotFoundException {
        ArrayList<AppointmentServiceVIEW> appointments=new ArrayList<AppointmentServiceVIEW>();
        try {
            PreparedStatement stmt=DBConnection.getConnection().prepareStatement("SELECT qID,date,startTime,endTime,cancelledFlag,serviceID,employeeID FROM j4f9qe_AppointemtServiceView WHERE cancelledFlag=0 AND serviceID=?;");
            stmt.setInt(1,serviceID);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                                                                                     // qID,date,startTime,endTime,cancelledFlag,serviceID,employeeID
                appointments.add(new AppointmentServiceVIEW(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5),resultSet.getInt(6),resultSet.getInt(7)));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return appointments;
    }

    public ServiceProvider getLeastAppCountSp(String[] arr) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getConnection();
        ArrayList<Integer> appCountList = new ArrayList<Integer>();
        ServiceProvider sp = new ServiceProvider();

        int len = arr.length;
        for(int i=0; i<len; i++)
        {

            String query = "SELECT * FROM `appointmentCount` WHERE employeeID = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1,Integer.parseInt(arr[i]));
            ResultSet rs =  pst.executeQuery();

            while(rs.next())
            {
                appCountList.add(rs.getInt(1));

            }
        }

        int min = Collections.min(appCountList);
        int minIndex = 0;

        for(int j=0; j<appCountList.size();j++)
        {
            if(min == appCountList.get(j))
            {
                minIndex = j;
                break;
            }
        }

        int spID = Integer.parseInt(arr[minIndex]);

        String query1 = "SELECT j4f9qe_employee.employeeID, j4f9qe_employee.firstName, j4f9qe_employee.lastName FROM j4f9qe_employee WHERE j4f9qe_employee.employeeID = ?";
        PreparedStatement pst2 = con.prepareStatement(query1);
        pst2.setInt(1,spID);
        ResultSet rs2 = pst2.executeQuery();

        if(rs2.next())
        {
            sp.setEmployeeId(rs2.getInt(1));
            sp.setFirstName(rs2.getString(2));
            sp.setLastName(rs2.getString(3));
        }


        return sp;

    }

}
