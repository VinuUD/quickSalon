package com.g34.quicksalon.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.g34.quicksalon.model.Service;

public interface ServiceDAO {
    
    public ArrayList<Service> getAllServices();

    public String getServiceNameByqID(int qID);

    public boolean  addServicetoAppointments(int qId,int serviceId) throws SQLException, ClassNotFoundException;


}
