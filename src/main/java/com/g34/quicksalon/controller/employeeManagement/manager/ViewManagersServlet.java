package com.g34.quicksalon.controller.employeeManagement.manager;

import com.g34.quicksalon.dao.ManagerDAO;
import com.g34.quicksalon.dao.ManagerDAOImple;
import com.g34.quicksalon.model.ManagerDetailsForView;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import  java.io.PrintWriter;

public class ViewManagersServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res ) throws IOException, ServletException
    {

        try
        {
            ManagerDAO manager = new ManagerDAOImple();
            ArrayList<ManagerDetailsForView> managerDetails = new ArrayList<>();
            managerDetails = manager.getManagersDetails();
            int name = managerDetails.get(0).getEmpId();
            

            String json = new Gson().toJson(managerDetails);
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
