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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SpAppointmentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out=response.getWriter();
        int spId= Integer.parseInt(request.getParameter("spId"));
        int serviceID= Integer.parseInt(request.getParameter("serviceID"));
        String customerName= request.getParameter("customerName");
        String telephone= request.getParameter("telephone");
        String date=request.getParameter("date");
        String time=request.getParameter("time");
        String timeTaken=request.getParameter("timeTaken");

//        this.qId = qId;
//        this.customerId = customerId;
//        this.date = date;
//        this.startTime = startTime;
//        this.endTime = endTime;
//        this.cancelledFlag = cancelledFlag;


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
