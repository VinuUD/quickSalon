package com.g34.quicksalon.controller.employeeManagement;

import com.g34.quicksalon.controller.login.JavaMailUtil;
import com.g34.quicksalon.dao.UserDAO;
import com.g34.quicksalon.dao.UserDAOImple;
import com.google.gson.Gson;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class NotifyUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        request.getParameterValues("userTypes");
        String[] userTypes = request.getParameterValues("userTypes[]");

        String msg=request.getParameter("msg");
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
//            emails.addAll();
//            System.out.println(userTypes[i]);
        }

        for(int i = 0; i < emails.size()-1; i++) {

            //Send Emails
            JavaMailUtil javaMailUtil=new JavaMailUtil();
            try {
                javaMailUtil.sendMail(emails.get(i),msg);
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            System.out.println(emails.get(i));

        }

//        String json = new Gson().toJson(userTypes);
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
