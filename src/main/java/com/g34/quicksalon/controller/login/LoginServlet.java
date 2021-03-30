package com.g34.quicksalon.controller.login;

import com.g34.quicksalon.dao.LoginDAO;
import com.g34.quicksalon.dao.LoginDAOImple;
import com.g34.quicksalon.model.LoginInfo;
import com.g34.quicksalon.model.UserLoginModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String email=request.getParameter("email");
        String password=request.getParameter("password");

        //password hashed - SHA256
        String hashedPW=doHash(password);
        UserLoginModel userLoginModel=new UserLoginModel();
        userLoginModel.setEmail(email);
        userLoginModel.setPassword(hashedPW);

        LoginDAO loginDAO=new LoginDAOImple ();


        // Get user details for sessions
        try {
            userLoginModel=loginDAO.login(userLoginModel);

            if(userLoginModel==null){
                out.println("Credentials are not matched !");
            }else {
                //Session
                int userType=userLoginModel.getUserType();

                HttpSession session = request.getSession();
                session.setAttribute("userID",userLoginModel.getId());
                session.setAttribute("username", userLoginModel.getUsername());
                session.setAttribute("email", userLoginModel.getEmail());
                session.setAttribute("userType", userType);

                switch (userType) {
                    case 1:     // owner
                        out.println("1");
                        break;
                    case 2:     // manager
                        out.println("2");
                        break;
                    case 3:     // service provider/serviceProvider/sp_home.html
                        //send to SpServlet
                        out.println("3");
                        break;
                    case 4:     // customer
                        out.println("4");
                        break;
                    default:
                        out.println("You can't log now..please contact our administration");
                }
            }
        } catch (SQLException throwables) {
            out.println(throwables.getErrorCode());
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

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
