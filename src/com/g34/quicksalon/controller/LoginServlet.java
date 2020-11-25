package com.g34.quicksalon.controller;

import com.g34.quicksalon.dao.LoginDAOImple;
import com.g34.quicksalon.model.LoginInfo;

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

        String username=request.getParameter("username");
        String password=request.getParameter("password");

        //password hashed - SHA256
        String hashedPW=doHash(password);

//        out.println(hashedPW);

        LoginInfo user = new LoginInfo(username, hashedPW);
        LoginDAOImple loginModel=new LoginDAOImple();


        // Get user details for sessions
        HashMap<String, Integer> userDetails=new HashMap<>();
        int userID=0;
        int userType=0;
        try {
            userDetails=loginModel.getUserType(user);

            if(userDetails==null){
                out.println("Credentials are not matched !");
            }else {

                userID = userDetails.get("userID");
                userType = userDetails.get("userType");

                //Session
                HttpSession session = request.getSession();
                session.setAttribute("userID", userID);
                session.setAttribute("username", username);
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
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        // if(username in db)
            // check hashedPW with pw
                // if match get usertype return userclass homepage
                // else return error


                // query: select userType from login where pw = pw and usrname = username;LL
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
