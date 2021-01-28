package com.g34.quicksalon.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.g34.quicksalon.model.ServiceProvider;

public interface ServiceProviderDAO {
    
    public ArrayList<ServiceProvider> getServiceProvidersByID(int sid) ;

    public HashMap<String,String> getSPList(int serviceId) ;

    public boolean assignedService(int qId,int employeeId) throws SQLException, ClassNotFoundException ;
  
}
