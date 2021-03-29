package com.g34.quicksalon.controller.employeeManagement.serviceProvider;

import com.g34.quicksalon.dao.ServiceProviderDAO;
import com.g34.quicksalon.dao.ServiceProviderDAOImple;
import com.g34.quicksalon.model.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;

public class RegisterSPServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int empID= Integer.parseInt(request.getParameter("empID"));
        String fname=request.getParameter("fname");
        String lname=request.getParameter("lname");
        String uname=request.getParameter("uname");
        String nic=request.getParameter("nic");
        String email=request.getParameter("email");
        String contactno=request.getParameter("contactno");
//        String phoneNo=request.getParameter("contactno");
        String address=request.getParameter("address");
        String password=doHash(request.getParameter("password"));
        float salary= Float.parseFloat(request.getParameter("salary"));

        ServiceProvider serviceProvider=new ServiceProvider(empID,fname,lname,uname,nic,email,salary,password,new Date().toString(),null,0,0,0,0,contactno,address);
        ServiceProviderDAO serviceProviderDAO=new ServiceProviderDAOImple();
        boolean success=false;
        try {
            success=serviceProviderDAO.registerServiceProvider(serviceProvider);
            response.getWriter().println(success);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //        Return empId for newly added SP
        ServiceProviderDAO serviceProviderDAO=new ServiceProviderDAOImple();
        try {
            int empID=serviceProviderDAO.getLastEmployeeID();
            response.getWriter().println(empID);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
