package com.g34.quicksalon.controller;

import com.g34.quicksalon.dao.AppointmentDAO;
import com.g34.quicksalon.dao.ApppointmentDAOImple;
import com.g34.quicksalon.dao.ServiceModel;
import com.g34.quicksalon.model.Appointment;
import com.g34.quicksalon.model.Service;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AppointmentsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // InterfaceDemo demo = new InterfaceDemoImpl();

        AppointmentDAO appointmentModel = new ApppointmentDAOImple();
        List<Appointment> appointments = appointmentModel.getAllAppointments();
        String json = new Gson().toJson(appointments);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }
}
