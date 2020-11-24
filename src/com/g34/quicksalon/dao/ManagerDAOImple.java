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

		String query = "insert into j4f9qe_employee (nicNo,firstName,lastName,salary,email, address, enrollDate,resignDate,isUpperStaffFlag,onLeaveFlag,removedFlag) values (?,?,?,?,?,?,?,?,?,?,?)";
		String query2 = "insert into j4f9qe_employeecontacts (empID, contactNum) values (?,?)";
		
		PreparedStatement pst = con.prepareStatement(query);
		PreparedStatement pst2 = con.prepareStatement(query2);
		
		Statement st = con.createStatement();
	    	
		pst.setString(1, manager.getNic());
		pst.setString(2, manager.getFirstName());
		pst.setString(3, manager.getLastName());
		pst.setInt(4, manager.getSalary());
		pst.setString(5, manager.getEmail());
		pst.setString(6, manager.getAddress());
		pst.setString(7, manager.getEnrollDate());
		pst.setString(8, manager.getResignDate());
		pst.setInt(9, manager.getIsUpperStaffFlag());
		pst.setInt(10, manager.getOnLeaveFlag());
		pst.setInt(11, manager.getRemovedFlag());
		int x = pst.executeUpdate();
		
		ResultSet rs = st.executeQuery("select last_insert_id()");
		
		rs.next();
		pst2.setInt(1, rs.getInt(1));
		pst2.setInt(2, manager.getcNum());
		int y = pst2.executeUpdate();
		System.out.println("x = "+x);
		System.out.println("y = "+y);

		if(x>0 && y>0)
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
