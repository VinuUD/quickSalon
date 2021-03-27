package com.g34.quicksalon.controller.reservation;

import com.g34.quicksalon.dao.AppointmentDAO;
import com.g34.quicksalon.dao.AppointmentDAOImple;
import com.g34.quicksalon.model.AppointmentServiceVIEW;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppointmentListByServiceIDServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int serviceID= Integer.parseInt(request.getParameter("serviceID"));

        ArrayList<AppointmentServiceVIEW> appointments=new ArrayList<AppointmentServiceVIEW>();
        AppointmentDAO appointmentDAO=new AppointmentDAOImple();

        try {
            appointments=appointmentDAO.getAllAppointmentDetailsByServiceID(serviceID);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String json = new Gson().toJson(appointments);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }
}
