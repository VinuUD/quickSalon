package com.g34.quicksalon.dao;

import com.g34.quicksalon.database.DBConnection;
import com.g34.quicksalon.model.ManagerDetails;
import com.g34.quicksalon.model.ManagerDetailsForView;

import java.sql.*;
import java.util.ArrayList;

public class ManagerDAOImple implements ManagerDAO {


	public int addManager(ManagerDetails manager) throws Exception
	{



		Connection	con = DBConnection.getConnection();

		String query = "insert into j4f9qe_employee (nicNo,firstName,lastName,salary, address, enrollDate,resignDate,isUpperStaffFlag,onLeaveFlag,removedFlag,contactNum) values (?,?,?,?,?,?,?,?,?,?,?)";


		PreparedStatement pst = con.prepareStatement(query);


		Statement st = con.createStatement();

		pst.setString(1, manager.getNic());
		pst.setString(2, manager.getFirstName());
		pst.setString(3, manager.getLastName());
		pst.setString(4, manager.getSalary());

		pst.setString(5, manager.getAddress());
		pst.setString(6, manager.getEnrollDate());
		pst.setString(7, manager.getResignDate());
		pst.setInt(8, manager.getIsUpperStaffFlag());
		pst.setInt(9, manager.getOnLeaveFlag());
		pst.setInt(10, manager.getRemovedFlag());
		pst.setString(11, manager.getcNum());
		int x = pst.executeUpdate();


		System.out.println("x = "+x);


		if(x>0)
		{
			return 1;
		}
		else
		{
			return 0;
		}


	}

	private ArrayList<ManagerDetailsForView> AllManagersDetails = new ArrayList<>();

	public ArrayList<ManagerDetailsForView> getManagersDetails() throws Exception
	{
		Connection	con = DBConnection.getConnection();
		Statement st = con.createStatement();


		ResultSet rs = st.executeQuery("select j4f9qe_employee.employeeID, j4f9qe_employee.firstName, j4f9qe_employee.lastName, j4f9qe_employee.contactNum, j4f9qe_employee.nicNo, j4f9qe_employee.salary,  j4f9qe_employee.address from j4f9qe_employee WHERE j4f9qe_employee.isUpperStaffFlag = 1 AND j4f9qe_employee.removedFlag = 0");

		while(rs.next())
		{
			AllManagersDetails.add(new ManagerDetailsForView(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5), rs.getString(6), rs.getString(7)));

		}

		return AllManagersDetails;
	}


}