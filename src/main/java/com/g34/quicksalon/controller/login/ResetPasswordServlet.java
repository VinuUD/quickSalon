package com.g34.quicksalon.controller.login;

import com.g34.quicksalon.dao.UserDAO;
import com.g34.quicksalon.dao.UserDAOImple;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class ResetPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String password=doHash(request.getParameter("password"));
        UserDAO userDAO=new UserDAOImple();

        HttpSession session=request.getSession();
        String email= (String) session.getAttribute("email");
        boolean reset=false;

        try {
            reset=userDAO.resetPassword(email,password);

            if(reset){
                out.println(1);
            }else{
                out.println(0);
            }
            session.invalidate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public String doHash(String password){
        try {
            MessageDigest messageDigest=MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes());
            byte[] hashedByte=messageDigest.digest();
            StringBuilder sb=new StringBuilder();
            for(byte b:hashedByte){
                sb.append(String.format("%02x",b));
            }
            return  sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
