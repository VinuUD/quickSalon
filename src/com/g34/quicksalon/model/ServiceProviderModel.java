package com.g34.quicksalon.model;

import com.g34.quicksalon.database.DBConnection;
import com.g34.quicksalon.entity.ServiceProvider;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ServiceProviderModel {

    private ArrayList<ServiceProvider> serviceProviders=new ArrayList<>();

    public ArrayList<ServiceProvider>getAllServiceProviders() {

        try {
            ResultSet resultSet = DBConnection.getConnection().createStatement().executeQuery("SELECT * FROM j4f9qe_employee WHERE isUpperStaffFlag=0;");
            while (resultSet.next()){

                ServiceProvider serviceProvider=new ServiceProvider(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5),resultSet.getString(6),resultSet.getString(7),resultSet.getInt(8),resultSet.getInt(9),resultSet.getInt(10));
                //ServiceProvider serviceProvider=new ServiceProvider(1,"HEllo","Hayyo","Monamagilkda",5,"Try","resultSet.getString(7)",8,9,10);
                serviceProviders.add(serviceProvider);

//                serviceProviders.add(new ServiceProvider(resultSet.getInt("employeeID"),resultSet.getString("nicNo"),resultSet.getString("firstName"),resultSet.getString("lastName"),resultSet.getInt("salary"),resultSet.getString("enrollDate"),resultSet.getString("resignDate"),resultSet.getInt("isUpperStaffFlag"),resultSet.getInt("onLeaveFlag"),resultSet.getInt("removedFlag")));
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



}
