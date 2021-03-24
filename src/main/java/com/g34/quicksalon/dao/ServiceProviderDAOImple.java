package com.g34.quicksalon.dao;

import com.g34.quicksalon.database.DBConnection;
import com.g34.quicksalon.model.Appointment;
import com.g34.quicksalon.model.CustomerDetails;
import com.g34.quicksalon.model.ServiceProvider;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class ServiceProviderDAOImple implements  ServiceProviderDAO{

    //get sps by serviceIDs
    public ArrayList<ServiceProvider> getServiceProvidersByID(int sid) {
        ArrayList<ServiceProvider> serviceProviders=new ArrayList<>();
        try {

            PreparedStatement stmt=DBConnection.getConnection().prepareStatement("SELECT * FROM j4f9qe_employee WHERE employeeID IN(SELECT serviceProviderID FROM j4f9qe_servicesprovided WHERE serviceID=?);");
            stmt.setInt(1,sid);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){

                ServiceProvider serviceProvider=new ServiceProvider(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getFloat(5),resultSet.getDate(6),resultSet.getDate(7),resultSet.getInt(8),resultSet.getInt(9),resultSet.getInt(10));
                serviceProviders.add(serviceProvider);

            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return serviceProviders;
    }

    //    This will return the id,firstName,lastName of sps whose provide given serviceId
    public ArrayList<ServiceProvider> getSPListByservice(int serviceId) throws SQLException, ClassNotFoundException {

        ArrayList<ServiceProvider> serviceProviders = new ArrayList<>();
            PreparedStatement stmt=DBConnection.getConnection().prepareStatement("SELECT employeeID,firstName,lastName FROM j4f9qe_employee WHERE employeeID IN(SELECT serviceProviderID FROM j4f9qe_servicesprovided WHERE serviceID=?);");
            stmt.setInt(1,serviceId);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                serviceProviders.add(new ServiceProvider(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3)));
            }

        return serviceProviders;
    }

    public boolean assignedService(int qId,int employeeId) throws SQLException, ClassNotFoundException {

        Connection connection =DBConnection.getConnection();
        PreparedStatement stmt= connection.prepareStatement("INSERT INTO j4f9qe_appointmentsassigned VALUES (?,?)");
        stmt.setInt(1,qId);
        stmt.setInt(2,employeeId);

        int success=stmt.executeUpdate();
        if(success>0){
            return true;
        }else{
            return false;
        }
    }

    public ArrayList<ServiceProvider> getServiceProvidersByName(String name) {

        ArrayList<ServiceProvider> serviceProviders=new ArrayList<>();

        try {
            PreparedStatement stmt=DBConnection.getConnection().prepareStatement("SELECT employeeID,firstName,lastName FROM j4f9qe_employee WHERE firstName LIKE '"+name+"%';");

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){
                ServiceProvider serviceProvider=new ServiceProvider(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
                serviceProviders.add(serviceProvider);
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return serviceProviders;
    }

    @Override
    public int getLastEmployeeID() throws SQLException, ClassNotFoundException {

        PreparedStatement stmt=DBConnection.getConnection().prepareStatement("SELECT employeeID FROM `j4f9qe_employee` ORDER BY employeeid DESC LIMIT 1;");
        ResultSet resultSet = stmt.executeQuery();

        int empID=0;
        if(resultSet.next()){
            empID=resultSet.getInt(1);
        }
        return empID+1;
    }

    @Override
    public boolean registerServiceProvider(ServiceProvider serviceProvider) throws SQLException, ClassNotFoundException {

        Connection connection =DBConnection.getConnection();
        connection.setAutoCommit(false);

        //Add SP to the user table
        int userID=registerUser(serviceProvider,connection);
        serviceProvider.setUserID(userID);

        //Add to the employee table
        boolean success=addNewEmployee(serviceProvider,connection);

        if(success){
            connection.commit();
            return true;
        }else{
            connection.rollback();
            return false;
        }
    }

    @Override
    public boolean assignServiceToSP(int serviceID, int spID) throws SQLException, ClassNotFoundException {
        Connection connection =DBConnection.getConnection();
        PreparedStatement stmt= connection.prepareStatement("INSERT INTO j4f9qe_servicesprovided VALUES (?,?);");
        stmt.setInt(1,serviceID);
        stmt.setInt(2,spID);

        int success=stmt.executeUpdate();
        if(success>0){
            return true;
        }else{
            return false;
        }
    }

    //     get all upcoming appointment by SP -- (QId,date,startTime,endTime)
    @Override
    public ArrayList<Appointment> getAppointmentDeatilsBySP(int empID) throws SQLException, ClassNotFoundException {

        ArrayList<Appointment> upAppointments=new ArrayList<>();

        try {
            PreparedStatement stmt=DBConnection.getConnection().prepareStatement("SELECT qID,date,startTime,endTime FROM j4f9qe_spappointmentsdetails WHERE date>=NOW() AND cancelledFlag!=1 AND employeeID=(?);");
            stmt.setInt(1,empID);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){
                Appointment appointment=new Appointment(resultSet.getInt(1),resultSet.getDate(2),resultSet.getTime(3),resultSet.getTime(4));
                upAppointments.add(appointment);
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return upAppointments;
    }


    public int registerUser(ServiceProvider serviceProvider,Connection connection) throws SQLException, ClassNotFoundException {
//        3-for SPs
        int userType=3;
        PreparedStatement stmt= connection.prepareStatement("INSERT INTO j4f9qe_user (email,userName,password,userType) VALUES (?,?,?,?)");

        stmt.setString(1,serviceProvider.getEmail());
        stmt.setString(2,serviceProvider.getUserName());
        stmt.setString(3,serviceProvider.getPassword());
        stmt.setInt(4,userType);

        int success=stmt.executeUpdate();

        if(success<=0){
            return 0;
        }
        ResultSet rst=connection.createStatement().executeQuery("SELECT LAST_INSERT_ID()");
        int userID=0;
        if(rst.next()){
            userID=rst.getInt(1);
        }
        return userID;
    }

    public boolean addNewEmployee(ServiceProvider serviceProvider,Connection connection) throws SQLException {
        PreparedStatement stmt= connection.prepareStatement("INSERT INTO j4f9qe_employee (nicNo,firstName,lastName,salary,enrollDate,resignDate,isUpperStaffFlag,onLeaveFlag,removedFlag,userID) VALUES (?,?,?,?,CURDATE(),?,?,?,?,?)");
        stmt.setString(1,serviceProvider.getNicNo());
        stmt.setString(2,serviceProvider.getFirstName());
        stmt.setString(3,serviceProvider.getLastName());
        stmt.setDouble(4,serviceProvider.getSalary());
        stmt.setString(5,null);
        stmt.setInt(6,0);
        stmt.setInt(7,0);
        stmt.setInt(8,0);
        stmt.setInt(9,serviceProvider.getUserID());

//        stmt.setString(4,serviceProvider.getNicNo());
//        stmt.setInt(2,employeeId);

        int success=stmt.executeUpdate();
        if(success>0){
            return true;
        }else{
            return false;
        }
    }

}

