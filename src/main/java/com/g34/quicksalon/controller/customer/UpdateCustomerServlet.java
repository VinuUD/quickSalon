package com.g34.quicksalon.controller.customer;

import com.g34.quicksalon.dao.*;
import com.g34.quicksalon.database.DBConnection;
import com.g34.quicksalon.model.ServiceProvider;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateCustomerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        int spID = Integer.parseInt(request.getParameter("spID"));



    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {


        HttpSession session= req.getSession();
//        int userID=(Integer) session.getAttribute("userID");
        int userID = 2;


        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String uname = req.getParameter("uname");
        int cnum = Integer.parseInt(req.getParameter("cnum"));
        String nic = req.getParameter("nic");
        String email = req.getParameter("email");
        String address = req.getParameter("address");

        CustomerDAO customer = new CustomerDAOImple();
        try {
            int t = customer.updateCustomer(userID,fname,lname,uname,cnum,nic,email,address);
            res.getWriter().println(t);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }







    }
}
