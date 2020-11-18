package com.g34.quicksalon.model;
import com.g34.quicksalon.entity.ManagerDetailsForView;
import java.sql.*;
import java.util.ArrayList;

public class ViewManagerModel {

    private ArrayList<ManagerDetailsForView> AllManagersDetails = new ArrayList<>();

    public ArrayList<ManagerDetailsForView> getManagersDetails() throws Exception
    {
        Class.forName("com.mysql.jdbc.Driver");

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quick_salon","root","");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select j4f9qe_employee.employeeID, j4f9qe_employee.firstName, j4f9qe_employee.lastName, j4f9qe_employeecontacts.contactNum, j4f9qe_employee.nicNo, j4f9qe_employee.salary from j4f9qe_employee INNER JOIN j4f9qe_employeecontacts ON j4f9qe_employee.employeeID = j4f9qe_employeecontacts.empID WHERE j4f9qe_employee.isUpperStaffFlag = 1");
        while(rs.next())
        {
            AllManagersDetails.add(new ManagerDetailsForView(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),rs.getString(5), rs.getInt(6)));

        }
        return AllManagersDetails;
    }

}
