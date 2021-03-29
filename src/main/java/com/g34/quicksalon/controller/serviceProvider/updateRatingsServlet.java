package com.g34.quicksalon.controller.serviceProvider;

import com.g34.quicksalon.dao.ServiceDAO;
import com.g34.quicksalon.dao.ServiceDAOImple;
import com.g34.quicksalon.dao.ServiceProviderDAO;
import com.g34.quicksalon.dao.ServiceProviderDAOImple;
import com.g34.quicksalon.model.Ratings;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class updateRatingsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int empID= Integer.parseInt(request.getParameter("empID"));
        int rateVal= Integer.parseInt(request.getParameter("rateVal"));
        System.out.println("this is updateServlet");

        ServiceDAO serviceDAO=new ServiceDAOImple();


        try {
            int x = serviceDAO.updateRatings(empID, rateVal);
            response.getWriter().println(x);
        } catch (SQLException throwables) {

            response.getWriter().println(throwables.getMessage());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().println(e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Ratings> ratings = new ArrayList<>();
        ServiceDAO sp = new ServiceDAOImple();
        try {
            ratings = sp.getRatings();
            String json = new Gson().toJson(ratings);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
