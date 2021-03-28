package com.g34.quicksalon.controller.reservation;

import com.g34.quicksalon.dao.*;
import com.g34.quicksalon.model.Service;
import com.g34.quicksalon.model.ServiceProvider;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class LeastAppCountSpDetailsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int spID = Integer.parseInt(request.getParameter("spID"));
            ServiceProviderDAO serviceProviderDAO = new ServiceProviderDAOImple();
            ServiceProvider sp = new ServiceProvider();


        try {
            sp = serviceProviderDAO.getSpDetails(spID);
            String json = new Gson().toJson(sp);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] spIDs = request.getParameterValues("spIDs[]");
        AppointmentDAO appointments = new AppointmentDAOImple();
        ServiceProvider sp = new ServiceProvider();
        System.out.println("Dn mn inne meke");
        try {
            sp = appointments.getLeastAppCountSp(spIDs);
            String json = new Gson().toJson(sp);
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


