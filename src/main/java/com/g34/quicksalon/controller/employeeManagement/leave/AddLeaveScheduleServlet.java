package com.g34.quicksalon.controller.employeeManagement.leave;

import com.g34.quicksalon.dao.OwnerDAO;
import com.g34.quicksalon.dao.OwnerDAOImple;
import com.g34.quicksalon.model.AppointmentDetailsForLeave;
import com.g34.quicksalon.model.Leave;
import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddLeaveScheduleServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

        System.out.println("scheduleServlet");

        try {
            OwnerDAO owner = new OwnerDAOImple();
            ArrayList<AppointmentDetailsForLeave> appointmentDetails = new ArrayList<>();
            appointmentDetails = owner.appointmentDetails();

            String json1 = new Gson().toJson(appointmentDetails);
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            res.getWriter().write(json1);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
