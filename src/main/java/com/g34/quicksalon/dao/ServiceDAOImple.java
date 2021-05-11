package com.g34.quicksalon.dao;

import com.g34.quicksalon.database.DBConnection;
import com.g34.quicksalon.model.Ratings;
import com.g34.quicksalon.model.Service;
import com.g34.quicksalon.model.ServiceProvider;

import java.sql.*;
import java.util.ArrayList;

public class ServiceDAOImple implements ServiceDAO {

    private ArrayList<Service> services=new ArrayList<>();

    public ArrayList<Service> getAllServices() {
        try {
            ResultSet resultSet = DBConnection.getConnection().createStatement().executeQuery("SELECT * FROM j4f9qe_service;");
            while (resultSet.next()) {
                services.add(new Service(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getInt(6)));
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

    public int registerService(Service service) throws SQLException, ClassNotFoundException {

        Connection connection =DBConnection.getConnection();
        PreparedStatement stmt= connection.prepareStatement("INSERT INTO j4f9qe_service (serviceName,serviceDescription,timeTaken,price) VALUES (?,?,?,?)");

        stmt.setString(1,service.getServiceName());
        stmt.setString(2,service.getServiceDescription());
        stmt.setString(3,service.getTimeTaken());
        stmt.setDouble(4,service.getPrice());

        int success=stmt.executeUpdate();
        if(success<=0){
            return 0;
        }
        ResultSet rst=connection.createStatement().executeQuery("SELECT LAST_INSERT_ID()");
        int serviceID=0;
        if(rst.next()){
            serviceID=rst.getInt(1);
        }
        return serviceID;
    }

    public ArrayList<Service> getServiceByID(int id) throws SQLException, ClassNotFoundException {
        ArrayList<Service> serviceDetails=new ArrayList<>();

        Connection connection =DBConnection.getConnection();
        PreparedStatement stmt= connection.prepareStatement("SELECT  * FROM j4f9qe_service WHERE j4f9qe_service.serviceID = ?");
        stmt.setInt(1,id);
        ResultSet rs =  stmt.executeQuery();
        while(rs.next())
        {
            serviceDetails.add(new Service(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6)));
        }

        return serviceDetails;
    }

    public int updateServiceTable(Service service) throws SQLException, ClassNotFoundException {
        Connection connection =DBConnection.getConnection();
        PreparedStatement stmt= connection.prepareStatement("UPDATE j4f9qe_service SET j4f9qe_service.serviceName=?, j4f9qe_service.serviceDescription=?, j4f9qe_service.timeTaken=?, j4f9qe_service.price =?, j4f9qe_service.holdFlag=? WHERE j4f9qe_service.serviceID =?");
        stmt.setString(1,service.getServiceName());
        stmt.setString(2,service.getServiceDescription());
        stmt.setString(3, service.getTimeTaken());
        stmt.setDouble(4, service.getPrice());
        stmt.setInt(5, service.getHoldFlag());
        stmt.setInt(6,service.getServiceID());

        int x = stmt.executeUpdate();
        return x;
    }

//    Get all deatails of service with give serviceID
    @Override
    public ArrayList<Service> getServiceDetailsByID(int serviceID) throws SQLException, ClassNotFoundException {

        ArrayList<Service> service=new ArrayList<>();
        try {
            Connection connection =DBConnection.getConnection();
            PreparedStatement stmt= connection.prepareStatement("SELECT * FROM j4f9qe_service WHERE serviceID=?;");
            stmt.setInt(1,serviceID);

            ResultSet resultSet=stmt.executeQuery();

            while (resultSet.next()) {
                service.add(new Service(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getDouble(5),resultSet.getInt(6)));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return service;

    }

    public int removeService(int serviceID) throws SQLException, ClassNotFoundException {
        Connection connection =DBConnection.getConnection();
        PreparedStatement stmt= connection.prepareStatement("DELETE FROM j4f9qe_service WHERE serviceID=?;");
        stmt.setInt(1,serviceID);
        int x = stmt.executeUpdate();
        return x;
    }

    public int updateRatings(int empID, int rateVal) throws SQLException, ClassNotFoundException {
        Connection connection =DBConnection.getConnection();
        PreparedStatement pst= connection.prepareStatement("UPDATE j4f9qe_ratings SET j4f9qe_ratings.rating = ? WHERE j4f9qe_ratings.empID = ?");
        pst.setInt(1,rateVal);
        pst.setInt(2,empID);

        int x = pst.executeUpdate();
        System.out.println(x);
        return x;
    }

    public ArrayList<Ratings> getRatings() throws SQLException, ClassNotFoundException {
        ArrayList<Ratings> ratings = new ArrayList<>();
        Connection connection =DBConnection.getConnection();
        PreparedStatement pst2 = connection.prepareStatement("SELECT * FROM j4f9qe_ratings");
        ResultSet rs = pst2.executeQuery();
        while (rs.next())
        {
            ratings.add(new Ratings(rs.getInt(1),rs.getInt(2)));

        }
        return ratings;

    }

    @Override
    public ArrayList<Service> getServiceByuserID(int userID) throws SQLException, ClassNotFoundException {

        ArrayList<Service> service=new ArrayList<>();
        try {
            Connection connection =DBConnection.getConnection();
            PreparedStatement stmt= connection.prepareStatement("SELECT s.serviceID,s.serviceName,s.timeTaken FROM j4f9qe_employee e INNER JOIN j4f9qe_servicesprovided sp ON e.employeeID = sp.serviceProviderID INNER JOIN j4f9qe_service s ON sp.serviceID = s.serviceID WHERE e.userID=(?);");
            stmt.setInt(1,userID);

            ResultSet resultSet=stmt.executeQuery();

            while (resultSet.next()) {
                service.add(new Service(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3)));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return service;

    }


}
