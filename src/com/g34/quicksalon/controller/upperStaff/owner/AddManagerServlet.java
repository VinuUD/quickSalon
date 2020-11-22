package com.g34.quicksalon.controller.upperStaff.owner;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

import com.g34.quicksalon.dao.ManagerDAO;
import com.g34.quicksalon.dao.ManagerDAOImple;
import com.g34.quicksalon.model.ManagerDetails;

public class AddManagerServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		LocalDate obj = LocalDate.now();
		String nic = req.getParameter("nic");
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		int salary = Integer.parseInt(req.getParameter("salary"));
		String enrollDate = obj.toString();
		String resignDate = "1111-11-11";
		int upFlag = 1;
		int leaveFlag = 0;
		int removeFlag = 0;
		int cNum = Integer.parseInt(req.getParameter("cNum"));
		

		
		ManagerDetails Manager = new ManagerDetails(nic, firstName, lastName, salary, enrollDate, resignDate, upFlag, leaveFlag, removeFlag, cNum);
		
		ManagerDAO managerDAO = new ManagerDAOImple();
		
		try {
			managerDAO.addManager(Manager);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}