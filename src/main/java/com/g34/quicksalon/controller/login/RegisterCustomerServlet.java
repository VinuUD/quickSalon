package com.g34.quicksalon.controller.login;

import com.g34.quicksalon.dao.CustomerDAO;
import com.g34.quicksalon.dao.CustomerDAOImple;
import com.g34.quicksalon.model.CustomerDetails;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;


public class RegisterCustomerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out= response.getWriter();
        int success=0;
        String fname=request.getParameter("fname");
        String lname=request.getParameter("lname");
        String uname=request.getParameter("uname");
        String contactno=request.getParameter("contactno");
        String nic=request.getParameter("nic");
        String email=request.getParameter("email");
        String address=request.getParameter("address");
        String password=doHash(request.getParameter("password"));


        CustomerDetails customerDetails=new CustomerDetails(fname,lname,uname,contactno,nic,email,address,password);
//        out.println(""+customerDetails.getFirstName()+"-"+customerDetails.getUserName()+"-"+customerDetails.getContactNo());

        CustomerDAO customerDAO=new CustomerDAOImple();
        try {
            boolean s=customerDAO.registerCustomer(customerDetails);
            if(s){
                //IF sucesss
                out.println(1);
            }else {
                //If failed
                out.println(0);
            }

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
