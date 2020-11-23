package com.g34.quicksalon.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class RegisterCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//         String fname=request.getParameter("fname");
//         lname
//         uname
//         contactno
//         nic
//         email
//         address
//         password
//         password-repeat

        String fname=request.getParameter("fname");
        String lname=request.getParameter("lname");
        String uname=request.getParameter("uname");
        String contactno=request.getParameter("contactno");
        String nic=request.getParameter("nic");
        String email=request.getParameter("email");
        String address=request.getParameter("address");
        String password=doHash(request.getParameter("password"));





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
