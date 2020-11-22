package com.g34.quicksalon.model;
import java.sql.*;

import com.g34.quicksalon.entity.ManagerDetails;
public class AddManagerModel {
	
	public void addManager(ManagerDetails manager) throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quick_salon","root","");
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
	

}
