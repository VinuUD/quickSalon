package com.g34.quicksalon.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.g34.quicksalon.model.Appointment;
import com.g34.quicksalon.model.ServiceProvider;

public interface ServiceProviderDAO {
    
    public ArrayList<ServiceProvider> getServiceProvidersByID(int sid) ;

    public ArrayList<ServiceProvider> getSPListByservice(int serviceId) throws SQLException, ClassNotFoundException;

    public boolean assignedService(int qId,int employeeId) throws SQLException, ClassNotFoundException ;


//    Serach service rovider by his name or Chars of name----return spid+name
    public ArrayList<ServiceProvider> getServiceProvidersByName(String name);

//    Get new employee ID for new SPS
    public  int getLastEmployeeID() throws SQLException, ClassNotFoundException;

//    Register SP
    public boolean registerServiceProvider(ServiceProvider serviceProvider) throws SQLException, ClassNotFoundException;

    //    Assign service to sp
    public  boolean assignServiceToSP(int serviceID,int spID) throws SQLException, ClassNotFoundException;

    //    get all upcoming appointment by SP -- (QId,date,startTime,endTime)
    public ArrayList<Appointment> getAppointmentDeatilsBySP(int empID) throws SQLException, ClassNotFoundException;

}
