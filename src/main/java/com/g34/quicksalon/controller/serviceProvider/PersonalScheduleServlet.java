package com.g34.quicksalon.controller.serviceProvider;

import com.g34.quicksalon.dao.*;
import com.g34.quicksalon.model.Appointment;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class PersonalScheduleServlet extends HttpServlet {

    // PersonalSchedule data for view personal schedule view
    class PersonalSchedule{
        String customerName;
        String serviceName;
        Time startTime;
        Time endTime;
        Date date;

        public PersonalSchedule(String customerName, String serviceName, Time startTime, Time endTime, Date date) {
            this.customerName = customerName;
            this.serviceName = serviceName;
            this.startTime = startTime;
            this.endTime = endTime;
            this.date = date;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //get session data
        HttpSession session= request.getSession();
        int userID=(Integer) session.getAttribute("userID");

        ArrayList<PersonalSchedule> schedules=new ArrayList<>();
        ArrayList<Appointment> appointments=new ArrayList<>();
        AppointmentDAO appointmentDAO = new ApppointmentDAOImple();
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
