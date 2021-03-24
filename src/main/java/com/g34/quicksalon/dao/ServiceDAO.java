package com.g34.quicksalon.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.g34.quicksalon.model.Service;

public interface ServiceDAO {
    
    public ArrayList<Service> getAllServices();

    public String getServiceNameByqID(int qID);

    public boolean  addServicetoAppointments(int qId,int serviceId) throws SQLException, ClassNotFoundException;

    public ArrayList<Service> getAllServiceNames() throws SQLException, ClassNotFoundException;

    public int registerService(Service service) throws SQLException, ClassNotFoundException;

    public ArrayList<Service> getServiceByID(int id) throws SQLException, ClassNotFoundException;

    public int updateServiceTable(Service service) throws SQLException, ClassNotFoundException;
  //Get all service Details of a service By serviceID
  public ArrayList<Service> getServiceDetailsByID(int serviceID) throws SQLException, ClassNotFoundException;

}
