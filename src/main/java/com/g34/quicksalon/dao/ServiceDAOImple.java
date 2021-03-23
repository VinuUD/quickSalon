package com.g34.quicksalon.dao;

import com.g34.quicksalon.database.DBConnection;
import com.g34.quicksalon.model.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServiceDAOImple implements ServiceDAO {

    private ArrayList<Service> services=new ArrayList<>();

    public ArrayList<Service> getAllServices() {
        try {
            ResultSet resultSet = DBConnection.getConnection().createStatement().executeQuery("SELECT * FROM j4f9qe_service;");
            while (resultSet.next()) {
                services.add(new Service(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6)));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return services;
    }


    public boolean  addServicetoAppointments(int qId,int serviceId) throws SQLException, ClassNotFoundException {

        Connection connection =DBConnection.getConnection();
        PreparedStatement stmt= connection.prepareStatement("INSERT INTO j4f9qe_appointmentservice VALUES (?,?)");
        stmt.setInt(1,qId);
        stmt.setInt(2,serviceId);

        int success=stmt.executeUpdate();
        if(success>0){
            return true;
        }else{
            return false;
        }
    }

    //    This will return service name+ serviceID + timeTaken of all services
    @Override
    public ArrayList<Service> getAllServiceNames() {
        ArrayList<Service> services=new ArrayList<>();
        try {
            ResultSet resultSet = DBConnection.getConnection().createStatement().executeQuery("SELECT serviceID,serviceName,timeTaken FROM j4f9qe_service;");
            while (resultSet.next()) {
                services.add(new Service(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3)));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return services;
    }

    public String getServiceNameByqID(int qID){
        String serviceName="";
        try {
            Connection connection =DBConnection.getConnection();
            PreparedStatement stmt= connection.prepareStatement("SELECT s.serviceName FROM j4f9qe_service s INNER JOIN j4f9qe_appointmentservice apts ON apts.serviceID=s.serviceID WHERE apts.qID=?;");
            stmt.setInt(1,qID);
            ResultSet resultSet=stmt.executeQuery();

            if(resultSet.next()){
                serviceName=resultSet.getString(1);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return serviceName;
    }

    public ArrayList<Service> getSpListByService() {
        ArrayList<Service> spList=new ArrayList<>();



        return spList;
    }

}
