package com.g34.quicksalon.model;

import com.g34.quicksalon.database.DBConnection;
import com.g34.quicksalon.entity.ServiceProvider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ServiceProviderModel {

    private ArrayList<ServiceProvider> serviceProviders;

    public ArrayList<ServiceProvider>getAllServiceProviders() {

        try {
            ResultSet resultSet = DBConnection.getConnection().createStatement().executeQuery("SELECT * FROM j4f9qe_employee WHERE isUpperStaffFlag=0;");
            while (resultSet.next()){
                serviceProviders.add(new ServiceProvider(resultSet.getInt("employeeID"),resultSet.getString("nicNo"),resultSet.getString("firstName"),resultSet.getString("lastName"),resultSet.getInt("salary"),resultSet.getString("enrollDate"),resultSet.getString("resignDate"),resultSet.getInt("isUpperStaffFlag"),resultSet.getInt("onLeaveFlag"),resultSet.getInt("removedFlag")));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return serviceProviders;
    }

//    This will return the id->full name pair of all sps
    public HashMap<String,String> getSPList() {
        HashMap<String, String> spList = new HashMap<String, String>();
        try {
            ResultSet resultSet = DBConnection.getConnection().createStatement().executeQuery("SELECT employeeID,firstName,lastName FROM j4f9qe_employee WHERE isUpperStaffFlag=0;");
            while (resultSet.next()) {
                spList.put(""+resultSet.getInt("employeeID"),resultSet.getString("firstName")+" "+resultSet.getString("lastName"));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return spList;

    }






}
