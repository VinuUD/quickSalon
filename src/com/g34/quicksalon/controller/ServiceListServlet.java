package com.g34.quicksalon.controller;

import com.g34.quicksalon.dao.ServiceDAO;
import com.g34.quicksalon.dao.ServiceDAOImple;
import com.g34.quicksalon.model.Service;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ServiceListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
             ServiceDAO serviceModel=new ServiceDAOImple();
             List<Service> services=serviceModel.getAllServices();
             String json = new Gson().toJson(services);
             response.setContentType("application/json");
             response.setCharacterEncoding("UTF-8");
             response.getWriter().write(json);

         }catch (Exception e){
             response.getWriter().println(e.getMessage());
         }
    }
}
