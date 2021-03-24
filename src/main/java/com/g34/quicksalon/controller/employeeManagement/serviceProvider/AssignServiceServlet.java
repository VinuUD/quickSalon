package com.g34.quicksalon.controller.employeeManagement.serviceProvider;

import com.g34.quicksalon.dao.ServiceProviderDAO;
import com.g34.quicksalon.dao.ServiceProviderDAOImple;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AssignServiceServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int serviceProviderID= Integer.parseInt(request.getParameter("spID"));
        int serviceID= Integer.parseInt(request.getParameter("serviceID"));

        ServiceProviderDAO serviceProviderDAO=new ServiceProviderDAOImple();
        boolean success=false;
        try {
            success=serviceProviderDAO.assignServiceToSP(serviceID,serviceProviderID);
            response.getWriter().println(success);
        } catch (SQLException throwables) {

            response.getWriter().println(throwables.getMessage());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().println(e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
