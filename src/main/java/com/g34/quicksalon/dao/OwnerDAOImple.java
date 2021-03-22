package com.g34.quicksalon.dao;

import com.g34.quicksalon.database.DBConnection;
import com.g34.quicksalon.model.AppointmentDetailsForLeave;
import com.g34.quicksalon.model.Leave;
import com.g34.quicksalon.model.ManagerDetailsForView;

import java.sql.*;
import java.util.ArrayList;

public class OwnerDAOImple implements OwnerDAO {

    private ArrayList<Leave> leaverDetails = new ArrayList<>();

    public OwnerDAOImple() throws SQLException, ClassNotFoundException {
    }

    public ArrayList<Leave> getLeaversDetails() throws SQLException, ClassNotFoundException {

        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery("SELECT j4f9qe_employee.employeeID , j4f9qe_employee.firstName, j4f9qe_employee.lastName, j4f9qe_leave.fromDate, j4f9qe_leave.toDate, j4f9qe_leave.leaveType FROM j4f9qe_leave INNER JOIN j4f9qe_employee ON j4f9qe_leave.leaverID = j4f9qe_employee.employeeID WHERE j4f9qe_leave.isApproved = \"n\"");

        while(rs.next())
        {
           leaverDetails.add(new Leave(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),'n'));

        }

       return leaverDetails;
    }

    ArrayList<AppointmentDetailsForLeave> appointmentDetails = new ArrayList<>();
    public ArrayList<AppointmentDetailsForLeave> appointmentDetails() throws SQLException, ClassNotFoundException {

        Connection con1 = DBConnection.getConnection();
        Statement st1 = con1.createStatement();

        ResultSet rs1 = st1.executeQuery("SELECT j4f9qe_appointments.date, j4f9qe_appointments.startTime, j4f9qe_appointmentsassigned.employeeID, j4f9qe_appointmentservice.serviceID FROM j4f9qe_appointments INNER JOIN j4f9qe_appointmentsassigned ON j4f9qe_appointments.qID = j4f9qe_appointmentsassigned.qID INNER JOIN j4f9qe_appointmentservice ON j4f9qe_appointmentservice.qID = j4f9qe_appointments.qID");

        while(rs1.next())
        {
            appointmentDetails.add( new AppointmentDetailsForLeave(rs1.getString(1), rs1.getString(2), rs1.getInt(4),rs1.getInt(3)));

        }
        return appointmentDetails;
    }

    public int addLeave(int id) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();

        int x = st.executeUpdate("UPDATE j4f9qe_leave SET j4f9qe_leave.isApproved = \"y\" WHERE j4f9qe_leave.leaverID ="+ id);

        return x;
    }
    Connection con = DBConnection.getConnection();
    public boolean cancelLeave(int id) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getConnection();
        String query = "DELETE FROM j4f9qe_leave WHERE j4f9qe_leave.leaverID = ?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, id);

        boolean x = pst.execute();

        return x;
    }

    public int removeManager(int id, String password) throws SQLException {
        Statement st = con.createStatement();
        System.out.println(password);

        if (password.equals("admin"))
        {
            System.out.println("mama shehan");

            String query = "DELETE FROM j4f9qe_employee WHERE j4f9qe_employee.employeeID ="+id;
            int x = st.executeUpdate(query);
            return x;
        }
        else
        {
            return 4;
        }




    }

    public int updateManager(int id, String cNum, int salary, String email, String address) throws SQLException {
        String query = "UPDATE j4f9qe_employee SET j4f9qe_employee.salary = ? , j4f9qe_employee.email = ?, j4f9qe_employee.address = ?, j4f9qe_employee.contactNum = ?  WHERE j4f9qe_employee.employeeID = ?";

        PreparedStatement pst = con.prepareStatement(query);


        pst.setInt(1,salary);
        pst.setString(2,email);
        pst.setString(3,address);
        pst.setString(4,cNum);
        pst.setInt(5,id);



            try {
                int x = pst.executeUpdate();
                System.out.println(x);
                return x;
            }
            catch (Exception e)
            {
                System.out.println("mn inne catch block ekee, hu huuuu");
                return 1;
            }



    }

    @Override
    public boolean applyLeave(Leave leave) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt= connection.prepareStatement("INSERT INTO j4f9qe_leave (leaverID,fromDate,fromTime,toDate,toTime,leaveType) VALUES (?,?,?,?,?,?)");
        stmt.setInt(1,leave.getLeaverID());
        stmt.setString(2,leave.getFromDate());
        stmt.setString(3, leave.getFromTime());
        stmt.setString(4,leave.getToDate());
        stmt.setString(5, leave.getToTime());
        stmt.setString(6,leave.getLeaveType());

        int success=stmt.executeUpdate();

        if(success<= 0){
            return false;
        }
        return true;
    }

//    Set removed flag=1 in emp table of SP
    @Override
    public boolean setRemovedFlagOfSp(int empid) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt= connection.prepareStatement("UPDATE j4f9qe_employee SET removedFlag=1 WHERE employeeID=(?);");
        stmt.setInt(1,empid);

        int success=stmt.executeUpdate();
        if(success<= 0){
            return false;
        }
        return true;
    }


}
