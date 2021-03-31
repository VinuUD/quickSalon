package com.g34.quicksalon.controller.reservation;

import com.g34.quicksalon.dao.AppointmentDAO;
import com.g34.quicksalon.dao.AppointmentDAOImple;
import com.g34.quicksalon.dao.AppointmentDAOImple;
import com.g34.quicksalon.dao.ServiceProviderDAO;
import com.g34.quicksalon.dao.ServiceProviderDAOImple;
import com.g34.quicksalon.model.Appointment;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class NewAppointmentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int sID = Integer.parseInt(request.getParameter("sID"));
            int spID = Integer.parseInt(request.getParameter("spID"));
            String date = request.getParameter("date");
            String startTime = request.getParameter("startTime");
            String endTime = request.getParameter("endTime");
//            HttpSession session= request.getSession();
//             int custID=(Integer) session.getAttribute("userID");




            int custID = 4;

        AppointmentDAO newApp = new AppointmentDAOImple();
        try {
            int x = newApp.placeNewAppointment(sID,spID,custID,date,startTime,endTime);
            response.getWriter().println(x);
            System.out.println(x);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

