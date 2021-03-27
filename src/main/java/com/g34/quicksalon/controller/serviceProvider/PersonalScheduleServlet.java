package com.g34.quicksalon.controller.serviceProvider;

import com.g34.quicksalon.dao.*;
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

public class PersonalScheduleServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //Return list of Cust Name, Service Name,startTime, endTime of a given date & session SPId
        ArrayList<PersonalSchedule> schedules=new ArrayList<>();
        //get session data
        HttpSession session= request.getSession();
        int userID=(Integer) session.getAttribute("userID");
//        int userID= Integer.parseInt(request.getParameter("userID"));
        String date=request.getParameter("date");

        ServiceProviderDAO serviceProviderDAO=new ServiceProviderDAOImple();
        try {
            schedules=serviceProviderDAO.getAppointmentsSPByDate(userID,date);

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //get session data
        HttpSession session= request.getSession();
        int userID=(Integer) session.getAttribute("userID");

        ArrayList<PersonalSchedule> schedules=new ArrayList<>();
        ArrayList<Appointment> appointments=new ArrayList<>();
        AppointmentDAO appointmentDAO = new AppointmentDAOImple();
        CustomerDAO customerDAO=new CustomerDAOImple();
        ServiceDAO serviceDAO=new ServiceDAOImple();

        //this returns all appointments of userID
        appointments=appointmentDAO.getAllAppointmentDetailsByUserId(userID);

        appointments.forEach(appointment -> {

            try {
                //get cutomer name by customerId
                String custName=customerDAO.getCustomerNameByID(appointment.getCustomerId());
                //get serviceName by qId
                String serviceName=serviceDAO.getServiceNameByqID(appointment.getqId());
                 schedules.add(new PersonalSchedule(custName,serviceName,appointment.getStartTime(),appointment.getEndTime(),appointment.getDate()));

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            //get serviceName By qId
        });

        String json = new Gson().toJson(schedules);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }
}
