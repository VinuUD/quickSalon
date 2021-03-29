package com.g34.quicksalon.controller.customer;

import com.g34.quicksalon.dao.CustomerDAO;
import com.g34.quicksalon.dao.CustomerDAOImple;
import com.g34.quicksalon.model.Customer;
import com.g34.quicksalon.model.CustomerDetails;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AllCustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String key = request.getParameter("key");

        CustomerDAO customerDAO = new CustomerDAOImple();

        ArrayList<Customer> customerArray = new ArrayList<Customer>();

        try {
            customerArray = customerDAO.getCustomersByKey(key);

//            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        String json = new Gson().toJson(customerArray);
        System.out.println(json);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(json);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
