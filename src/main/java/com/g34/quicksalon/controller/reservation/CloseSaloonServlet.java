package com.g34.quicksalon.controller.reservation;

import com.g34.quicksalon.controller.login.JavaMailUtil;
import com.g34.quicksalon.dao.OwnerDAO;
import com.g34.quicksalon.dao.OwnerDAOImple;
import com.g34.quicksalon.dao.UserDAO;
import com.g34.quicksalon.dao.UserDAOImple;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CloseSaloonServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String msg = req.getParameter("msg");
        String[] userTypes = {"2", "3", "4"};

        UserDAO userDAO =new UserDAOImple();

        ArrayList<String> emails=new ArrayList<>();
        ArrayList<String> email1=new ArrayList<>();

        for(int i = 0; i < userTypes.length;i++) {
            try {
                email1= userDAO.getUserEmails(Integer.parseInt(userTypes[i]));
                emails.addAll(email1);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        for(int i = 0; i < emails.size()-1; i++) {

            //Send Emails
            JavaMailUtil javaMailUtil=new JavaMailUtil();
            try {
                javaMailUtil.notifyUser(emails.get(i),msg);
//                res.getWriter().println(1);
            } catch (MessagingException e) {
//                res.getWriter().println(0);
                e.printStackTrace();
            }



        }

    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("this is closing dates servlets");
        String fromDate = req.getParameter("fromDate");
        String toDate = req.getParameter("toDate");
        String msg = req.getParameter("msg");

        try {
            OwnerDAO ownerDAO = new OwnerDAOImple();
            int x = ownerDAO.addClosingDates(fromDate, toDate);
            res.getWriter().println(x);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
