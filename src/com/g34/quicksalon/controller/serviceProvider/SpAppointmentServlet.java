package com.g34.quicksalon.controller.serviceProvider;

import com.g34.quicksalon.entity.Appointment;
import com.g34.quicksalon.entity.ServiceProvider;
import com.g34.quicksalon.model.AppointmentModel;
import com.g34.quicksalon.model.ServiceProviderModel;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class SpAppointmentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String date=request.getParameter("date");
        int spId= Integer.parseInt(request.getParameter("spId"));
        int serviceId= Integer.parseInt(request.getParameter("serviceId"));



        String json = new Gson().toJson(date+spId);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<Integer> qIds=new ArrayList<Integer>();
        int spId= Integer.parseInt(request.getParameter("spId"));
        try {
            AppointmentModel appointmentModel=new AppointmentModel();
            qIds=appointmentModel.getAllAppointmentsBySP(spId);
            String json = new Gson().toJson(qIds);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        }catch (Exception e){
            response.getWriter().println(e.getMessage());
        }
    }
}
