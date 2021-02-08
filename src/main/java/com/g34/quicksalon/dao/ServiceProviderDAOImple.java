package com.g34.quicksalon.dao;

import com.g34.quicksalon.database.DBConnection;
import com.g34.quicksalon.model.ServiceProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ServiceProviderDAOImple implements  ServiceProviderDAO{

    private ArrayList<ServiceProvider> serviceProviders=new ArrayList<>();

    //get sps by serviceIDs
    public ArrayList<ServiceProvider> getServiceProvidersByID(int sid) {

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

//    This will return the id->full name pair of sps whose provide given serviceId
    public HashMap<String,String> getSPList(int serviceId) {
        HashMap<String, String> spList = new HashMap<String, String>();
        try {
            PreparedStatement stmt=DBConnection.getConnection().prepareStatement("SELECT employeeID, CONCAT(firstName,' ',lastName) AS fullName FROM j4f9qe_employee WHERE employeeID IN(SELECT serviceProviderID FROM j4f9qe_servicesprovided WHERE serviceID=?);");

            stmt.setInt(1,serviceId);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                spList.put(""+resultSet.getInt("employeeID"),resultSet.getString("fullName"));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return spList;
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



}
