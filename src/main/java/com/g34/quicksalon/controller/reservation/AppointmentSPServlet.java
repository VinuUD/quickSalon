package com.g34.quicksalon.controller.reservation;

import com.g34.quicksalon.dao.*;
import com.g34.quicksalon.model.Appointment;
import com.g34.quicksalon.model.AppointmentServiceVIEW;
import com.g34.quicksalon.model.ServiceProvider;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppointmentSPServlet extends HttpServlet {

    //Get appointment list of a sp by serviceID
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //        HttpSession session= request.getSession();
//        int userID=(Integer) session.getAttribute("userID");
        int userID=33;
        int employeeID;
        int serviceID= Integer.parseInt(request.getParameter("serviceID"));
        ArrayList<AppointmentServiceVIEW> appointments=new ArrayList<AppointmentServiceVIEW>();


        ServiceProviderDAO serviceProviderDAO=new ServiceProviderDAOImple();
        AppointmentDAO appointmentDAO=new AppointmentDAOImple();

        try {
            //get emp ID  by userID
            employeeID=serviceProviderDAO.getSPIDByUserID(userID);
            System.out.println(employeeID);
            //get All appointments by service id and employee ID
            appointments= appointmentDAO.getAllSPAppointmentByServiceID(serviceID,employeeID);
//            System.out.println(appointments.get(0).getDate());

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
      //get All Appointment Details By EmpId
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
