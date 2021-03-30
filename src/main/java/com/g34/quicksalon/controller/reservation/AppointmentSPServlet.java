package com.g34.quicksalon.controller.reservation;

import com.g34.quicksalon.dao.AppointmentDAO;
import com.g34.quicksalon.dao.AppointmentDAOImple;
import com.g34.quicksalon.model.Appointment;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AppointmentSPServlet extends HttpServlet {

    //Get appointment list of a sp by serviceID
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //        HttpSession session= request.getSession();
//        int userID=(Integer) session.getAttribute("userID");

        int userID=33;
        int serviceID= Integer.parseInt(request.getParameter("serviceID"));

        



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Appointment> appointments=new ArrayList<Appointment>();
        int spID= Integer.parseInt(request.getParameter("spId"));
        AppointmentDAO appointmentDAO=new AppointmentDAOImple();
        appointments=appointmentDAO.getAllAppointmentDetailsByEmpId(spID);

        String json = new Gson().toJson(appointments);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);


    }
}
