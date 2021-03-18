package com.g34.quicksalon.controller.serviceProvider;

import com.g34.quicksalon.dao.ServiceProviderDAO;
import com.g34.quicksalon.dao.ServiceProviderDAOImple;
import com.g34.quicksalon.model.ServiceProvider;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class SearchSPServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name=request.getParameter("name");
        ServiceProviderDAO serviceProviderDAO=new ServiceProviderDAOImple();
        ArrayList<ServiceProvider> serviceProviders=new ArrayList<>();
        serviceProviders =serviceProviderDAO.getServiceProvidersByName(name);
        String json = new Gson().toJson(serviceProviders);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
