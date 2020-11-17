package com.g34.quicksalon.controller;

import com.g34.quicksalon.entity.Appointment;
import com.g34.quicksalon.entity.Service;
import com.g34.quicksalon.model.AppointmentModel;
import com.g34.quicksalon.model.ServiceModel;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AppointmentsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AppointmentModel appointmentModel=new AppointmentModel();
        List<Appointment> appointments=appointmentModel.getAllAppointments();
        String json = new Gson().toJson(appointments);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }
}
