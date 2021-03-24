package com.g34.quicksalon.controller.serviceManagement;

import com.g34.quicksalon.dao.ServiceDAO;
import com.g34.quicksalon.dao.ServiceDAOImple;
import com.g34.quicksalon.dao.ServiceProviderDAO;
import com.g34.quicksalon.dao.ServiceProviderDAOImple;
import com.g34.quicksalon.model.Service;
import com.g34.quicksalon.model.ServiceProvider;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

public class ServiceDetailsByIDServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int serviceID = Integer.parseInt(request.getParameter("serviceID"));

        try {
            ServiceDAO serviceDAO=new ServiceDAOImple();
            ArrayList<Service> serviceDetails=new ArrayList<>();
            serviceDetails= serviceDAO.getServiceByID(serviceID);

            String json = new Gson().toJson(serviceDetails);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        }catch (Exception e){
            response.getWriter().println(e.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int spID = Integer.parseInt(request.getParameter("spID"));


        try {
            ServiceProviderDAO sp = new ServiceProviderDAOImple();
            ArrayList<ServiceProvider> spDetails=new ArrayList<>();
            spDetails = sp.getServiceProvidersByIDShort(spID);

            String json = new Gson().toJson(spDetails);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        }catch (Exception e){
            response.getWriter().println(e.getMessage());
        }
    }
}
