package com.g34.quicksalon.controller.serviceProvider;

import com.g34.quicksalon.dao.ServiceDAO;
import com.g34.quicksalon.dao.ServiceDAOImple;
import com.g34.quicksalon.dao.ServiceProviderDAO;
import com.g34.quicksalon.dao.ServiceProviderDAOImple;
import com.g34.quicksalon.model.Service;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServiceListSPServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

//    Load all the services provided by user ID
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        HttpSession session= request.getSession();
//        int userID=(Integer) session.getAttribute("userID");

        int userID=33;
        ArrayList<Service> service=new ArrayList<>();

        //get employeeID by userID
        ServiceProviderDAO serviceProviderDAO=new ServiceProviderDAOImple();
        try {
            int employeeID=serviceProviderDAO.getSPIDByUserID(userID);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ServiceDAO serviceDAO=new ServiceDAOImple();

        try {
            service=serviceDAO.getServiceByuserID(userID);

            String json = new Gson().toJson(service);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
