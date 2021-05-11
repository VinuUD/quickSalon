package com.g34.quicksalon.controller.employeeManagement.serviceProvider;

import com.g34.quicksalon.dao.ManagerDAO;
import com.g34.quicksalon.dao.ManagerDAOImple;
import com.g34.quicksalon.dao.ServiceProviderDAO;
import com.g34.quicksalon.dao.ServiceProviderDAOImple;
import com.g34.quicksalon.model.ManagerDetailsForView;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class ViewSPsServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res ) throws IOException, ServletException
    {

        try
        {
            ServiceProviderDAO sp = new ServiceProviderDAOImple();
            ArrayList<ManagerDetailsForView> spDetails = new ArrayList<>();
            spDetails = sp.getServiceProvidersDetails();
            int name = spDetails.get(0).getEmpId();

            String json = new Gson().toJson(spDetails);
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            res.getWriter().write(json);





        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e);
        }


    }

}
