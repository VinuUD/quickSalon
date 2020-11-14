package com.g34.quicksalon.controller.serviceProvider;

import com.g34.quicksalon.entity.ServiceProvider;
import com.g34.quicksalon.model.ServiceModel;
import com.g34.quicksalon.model.ServiceProviderModel;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceProviderListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<ServiceProvider> spList=new ArrayList<>();
        int serviceId= Integer.parseInt(request.getParameter("sid"));
        try {
            ServiceProviderModel serviceProviderModel=new ServiceProviderModel();
            spList=serviceProviderModel.getServiceProvidersByID(serviceId);
            String json = new Gson().toJson(spList);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        }catch (Exception e){
            response.getWriter().println(e.getMessage());
        }
    }
}
