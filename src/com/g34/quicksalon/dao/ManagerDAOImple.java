package com.g34.quicksalon.dao;

import com.g34.quicksalon.database.DBConnection;
import com.g34.quicksalon.model.ManagerDetails;
import com.g34.quicksalon.model.ManagerDetailsForView;

import java.sql.*;
import java.util.ArrayList;

public class ManagerDAOImple implements ManagerDAO {

	
	public void addManager(ManagerDetails manager) throws Exception
	{



		Connection	con = DBConnection.getConnection();

		String query = "insert into j4f9qe_employee (nicNo,firstName,lastName,salary,enrollDate,resignDate,isUpperStaffFlag,onLeaveFlag,removedFlag) values (?,?,?,?,?,?,?,?,?)";
		String query2 = "insert into j4f9qe_employeecontacts (empID, contactNum) values (?,?)";
		
		PreparedStatement pst = con.prepareStatement(query);
		PreparedStatement pst2 = con.prepareStatement(query2);
		
		Statement st = con.createStatement();
	    	
		pst.setString(1, manager.getNic());
		pst.setString(2, manager.getFirstName());
		pst.setString(3, manager.getLastName());
		pst.setInt(4, manager.getSalary());
		pst.setString(5, manager.getEnrollDate());
		pst.setString(6, manager.getResignDate());
		pst.setInt(7, manager.getIsUpperStaffFlag());
		pst.setInt(8, manager.getOnLeaveFlag());
		pst.setInt(9, manager.getRemovedFlag());
		pst.executeUpdate();
		
		ResultSet rs = st.executeQuery("select last_insert_id()");
		
		rs.next();
		pst2.setInt(1, rs.getInt(1));
		pst2.setInt(2, manager.getcNum());
		pst2.executeUpdate();
		
	}

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
