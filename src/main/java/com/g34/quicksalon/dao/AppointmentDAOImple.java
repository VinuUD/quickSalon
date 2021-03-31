package com.g34.quicksalon.dao;

import com.g34.quicksalon.database.DBConnection;
import com.g34.quicksalon.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class AppointmentDAOImple implements AppointmentDAO {


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



    //    get all upcoming appointment by Date -- return (QId,Name,telephone,startTime,endTime,spName)
    @Override
    public ArrayList<AppointmentVIEWForUpperStaff> getAppointmentDeatilsByDate(String date) throws SQLException, ClassNotFoundException {

        ArrayList<AppointmentVIEWForUpperStaff> appointments=new ArrayList<>();
        try {
            PreparedStatement stmt=DBConnection.getConnection().prepareStatement("SELECT qID,firstName,LastName,telephone,serviceName,startTime,endTime,empFirstName,empLastName FROM j4f9qe_AppointmentViewForUS WHERE cancelledFlag=0 AND complete=0 AND date=(?);");
            stmt.setString(1,date);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){
                AppointmentVIEWForUpperStaff appointment=new AppointmentVIEWForUpperStaff(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9));
                appointments.add(appointment);
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }


        return appointments;

    }

    @Override
    public boolean cancelAppointment(int qID) throws SQLException, ClassNotFoundException {

        Connection connection =DBConnection.getConnection();
        PreparedStatement stmt= connection.prepareStatement("UPDATE j4f9qe_appointments SET cancelledFlag=1 WHERE qID=(?);");
        stmt.setInt(1,qID);
        int success=stmt.executeUpdate();

        if(success<=0){
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<PersonalSchedule> getTodayAppointmentsBySPID(int userID) throws SQLException, ClassNotFoundException {

        ArrayList<PersonalSchedule> appointments=new ArrayList<>();
        try {
            PreparedStatement stmt=DBConnection.getConnection().prepareStatement("SELECT qID,firstName,lastName,serviceName,startTime,endTime FROM j4f9qe_personalschedule WHERE date=CURDATE() AND cancelledFlag=0 AND complete=0 AND (startTime> CURRENT_TIME() OR CURRENT_TIME() BETWEEN startTime AND endTime) AND userID=(?);");
            stmt.setInt(1,userID);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){
                PersonalSchedule appointment=new PersonalSchedule(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),null);
                appointments.add(appointment);
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

        for(int k=0; k<len; k++)
        {
            System.out.print(arr[k]);
        }



        for(int i=0; i<len; i++)
        {

            String query = "SELECT * FROM j4f9qe_employeeratings WHERE employeeID = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1,Integer.parseInt(arr[i]));
            ResultSet rs =  pst.executeQuery();

            while(rs.next())
            {
                appCountList.add(rs.getInt(4));

            }
        }

        int max = Collections.max(appCountList);
        int maxIndex = 0;

        for(int j=0; j<appCountList.size();j++)
        {
            if(max == appCountList.get(j))
            {
                maxIndex = j;
                break;
            }
        }

        int spID = Integer.parseInt(arr[maxIndex]);

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


    public int placeNewAppointment(int serviceID, int spID,int custID, String date, String startTIme, String endTime) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getConnection();
        String query = "INSERT INTO j4f9qe_appointments (j4f9qe_appointments.customerID, j4f9qe_appointments.date, j4f9qe_appointments.startTime, j4f9qe_appointments.endTime) VALUES (?,?,?,?)";
        PreparedStatement pst = con.prepareStatement(query);

        pst.setInt(1,custID);
        pst.setString(2,date);
        pst.setString(3,startTIme);
        pst.setString(4,endTime);

        int x = pst.executeUpdate();

        int lastID = 0;
        Statement st = con.createStatement();
        String query2 = "SELECT LAST_INSERT_ID()";
        ResultSet rs =  st.executeQuery(query2);
        if(rs.next())
        {
            lastID = rs.getInt(1);
            String query3 = "INSERT INTO j4f9qe_appointmentsassigned VALUES (?,?)";
            String query4 = "INSERT INTO j4f9qe_appointmentservice VALUES (?,?)";
            PreparedStatement pst2 = con.prepareStatement(query3);
            PreparedStatement pst3 = con.prepareStatement(query4);
            pst2.setInt(1,lastID);
            pst2.setInt(2,spID);
            pst2.executeUpdate();

            pst3.setInt(1,lastID);
            pst3.setInt(2,serviceID);
            int y = pst3.executeUpdate();

            return y;

            
        }
        return 0;
    }


    @Override
    public ArrayList<AppointmentServiceVIEW> getAllSPAppointmentByServiceID(int serviceID,int empID) throws SQLException, ClassNotFoundException {
        ArrayList<AppointmentServiceVIEW> appointments=new ArrayList<AppointmentServiceVIEW>();
        try {

            PreparedStatement stmt=DBConnection.getConnection().prepareStatement("SELECT qID,date,startTime FROM j4f9qe_AppointemtServiceView WHERE cancelledFlag=0 AND serviceID=? AND employeeID =?;");
            stmt.setInt(1,serviceID);
            stmt.setInt(2,empID);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                // qID,date,startTime,endTime,cancelledFlag,serviceID,employeeID
                appointments.add(new AppointmentServiceVIEW(resultSet.getInt(1),resultSet.getDate(2),resultSet.getString(3)));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return appointments;
    }

    @Override
    public boolean dequeueAppointment(int qID) throws SQLException, ClassNotFoundException {

        Connection connection =DBConnection.getConnection();
        PreparedStatement stmt= connection.prepareStatement("UPDATE j4f9qe_appointments SET complete=1 WHERE qID=(?);");
        stmt.setInt(1,qID);
        int success=stmt.executeUpdate();

        if(success<=0){
            return false;
        }
        return true;

    }


}
