package com.g34.quicksalon.controller.customer;

import com.g34.quicksalon.controller.login.JavaMailUtil;
import com.g34.quicksalon.dao.*;
import com.g34.quicksalon.model.Customer;
import com.google.gson.Gson;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReviewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String msg = request.getParameter("msg");
        String email=null;

        OwnerDAO ownerDAO = null;
        try {
            ownerDAO = new OwnerDAOImple();
            email = ownerDAO.getOwnerEmail();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        //Send Emails
        JavaMailUtil javaMailUtil=new JavaMailUtil();
        try {
            javaMailUtil.notifyUser(email,msg);
            response.getWriter().println(1);
        } catch (MessagingException e) {
            response.getWriter().println(0);
            e.printStackTrace();
        }

    }
}
