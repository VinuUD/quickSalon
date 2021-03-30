package com.g34.quicksalon.controller.serviceProvider;

import com.g34.quicksalon.dao.AppointmentDAO;
import com.g34.quicksalon.dao.AppointmentDAOImple;
import com.g34.quicksalon.dao.ServiceProviderDAO;
import com.g34.quicksalon.dao.ServiceProviderDAOImple;
import com.g34.quicksalon.model.Appointment;
import com.g34.quicksalon.model.PersonalSchedule;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class UpcomingPersonalScheduleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    //Get personal schedule of a sp upcoming Appointment -Return today apt list
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<PersonalSchedule> schedules=new ArrayList<>();
        //get session data
//        HttpSession session= request.getSession();
//        int userID=(Integer) session.getAttribute("userID");
        int userID=33; // Integer.parseInt(request.getParameter("userID"));

        AppointmentDAO appointmentDAO=new AppointmentDAOImple();
        try {
            schedules=appointmentDAO.getTodayAppointmentsBySPID(userID);

            String json = new Gson().toJson(schedules);
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
