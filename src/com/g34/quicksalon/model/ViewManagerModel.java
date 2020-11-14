package com.g34.quicksalon.model;

import com.g34.quicksalon.database.DBConnection;
import com.g34.quicksalon.entity.ManagerDetails;
import com.g34.quicksalon.entity.ManagerDetailsForView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViewManagerModel {


    private ArrayList<ManagerDetailsForView> AllManagersDetails = new ArrayList<>();

    public ArrayList<ManagerDetailsForView> getManagersDetails() throws Exception
    {
        String query = "select j4f9qe_employee.employeeID, j4f9qe_employee.firstName, j4f9qe_employee.lastName, j4f9qe_employeecontacts.contactNum, j4f9qe_employee.nicNo, j4f9qe_employee.salary from j4f9qe_employee INNER JOIN j4f9qe_employeecontacts ON j4f9qe_employee.employeeID = j4f9qe_employeecontacts.empID WHERE j4f9qe_employee.isUpperStaffFlag = 1";
        ResultSet resultSet = DBConnection.getConnection().createStatement().executeQuery(query);
        while(resultSet.next())
        {
            AllManagersDetails.add(new ManagerDetailsForView(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4),resultSet.getString(5),resultSet.getInt(6)));

        }
        return AllManagersDetails;
    }



}
