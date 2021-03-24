package com.g34.quicksalon.controller.serviceManagement;

import com.g34.quicksalon.dao.ServiceDAO;
import com.g34.quicksalon.dao.ServiceDAOImple;
import com.g34.quicksalon.model.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RegisterServiceServlet extends HttpServlet {

//    Add service data to service table & return last Service ID
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String serviceName=request.getParameter("sname");
        String desc=request.getParameter("desc");
        String timeTaken=request.getParameter("timeTaken");
        double price= Double.parseDouble(request.getParameter("price"));

        Service service =new Service(serviceName,desc,timeTaken,price,0);
        ServiceDAO serviceDAO=new ServiceDAOImple();
        int serviceID=0;
        try {
            serviceID=serviceDAO.registerService(service);
            response.getWriter().println(serviceID);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
