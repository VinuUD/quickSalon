package com.g34.quicksalon.controller.reservation;

import com.g34.quicksalon.dao.AppointmentDAO;
import com.g34.quicksalon.dao.AppointmentDAOImple;
import com.g34.quicksalon.model.AppointmentServiceVIEW;
import com.g34.quicksalon.model.AppointmentVIEWForUpperStaff;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AllAppointmentsByDateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String date =request.getParameter("date");
        AppointmentDAO appointmentDAO=new AppointmentDAOImple();
        ArrayList<AppointmentVIEWForUpperStaff> appointments=new ArrayList<AppointmentVIEWForUpperStaff>();

        try {
           appointments= appointmentDAO.getAppointmentDeatilsByDate(date);

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
}
