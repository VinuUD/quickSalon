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
		String salary = req.getParameter("salary");
		String enrollDate = obj.toString();
		String resignDate = "1111-11-11";
		int upFlag = 1;
		int leaveFlag = 0;
		int removeFlag = 0;
		String cNum = req.getParameter("cNum");
		String email = req.getParameter("email");
		String address = req.getParameter("address");



		
		ManagerDetails Manager = new ManagerDetails(nic, firstName, lastName, salary, enrollDate, resignDate, upFlag, leaveFlag, removeFlag, cNum, email, address);
		
		ManagerDAO managerDAO = new ManagerDAOImple();
		
		try {
			int x = managerDAO.addManager(Manager);
			if(x == 1)
			{
				res.getWriter().println(x);
				System.out.println(x);
			}
			else
			{
				res.getWriter().println(x);
				System.out.println(x);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}