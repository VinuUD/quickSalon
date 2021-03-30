package com.g34.quicksalon.controller.reservation;

import com.g34.quicksalon.dao.AppointmentDAO;
import com.g34.quicksalon.dao.AppointmentDAOImple;
import com.g34.quicksalon.dao.ServiceProviderDAO;
import com.g34.quicksalon.dao.ServiceProviderDAOImple;
import com.g34.quicksalon.model.Appointment;
import com.g34.quicksalon.model.AppointmentVIEWForUpperStaff;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class UpcomingAppointmentSPServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // Return all upcoming appointments By date
        String date=request.getParameter("date");
        ArrayList<AppointmentVIEWForUpperStaff> appointments=new ArrayList<>();

        AppointmentDAO appointmentDAO=new AppointmentDAOImple();
        try {
            appointments=appointmentDAO.getAppointmentDeatilsByDate(date);

            String json = new Gson().toJson(appointments);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int empID= Integer.parseInt(request.getParameter("empID"));

        ServiceProviderDAO serviceProviderDAO=new ServiceProviderDAOImple();
        ArrayList<Appointment> upAppointments=new ArrayList<>();
        try {
            upAppointments=serviceProviderDAO.getAppointmentDeatilsBySP(empID);

            String json = new Gson().toJson(upAppointments);
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
