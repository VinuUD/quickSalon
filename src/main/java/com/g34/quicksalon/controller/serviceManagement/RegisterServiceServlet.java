package com.g34.quicksalon.controller.serviceManagement;

import com.g34.quicksalon.dao.ServiceDAO;
import com.g34.quicksalon.dao.ServiceDAOImple;
import com.g34.quicksalon.dao.ServiceProviderDAO;
import com.g34.quicksalon.dao.ServiceProviderDAOImple;
import com.g34.quicksalon.model.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

public class RegisterServiceServlet extends HttpServlet {

//    Add service data to service table & return last Service ID
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String serviceName=request.getParameter("sname");
        String desc=request.getParameter("desc");
        String timeTaken=request.getParameter("timeTaken");
        double price= Double.parseDouble(request.getParameter("price"));

        String[] spIDs = request.getParameterValues("spIDs[]");

        //1)create a new service
        Service service =new Service(serviceName,desc,timeTaken,price,0);
        ServiceDAO serviceDAO=new ServiceDAOImple();
        int serviceID=0;
        try {
            serviceID=serviceDAO.registerService(service);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //2)Update  j4f9qe_servicesprovided table
        ServiceProviderDAO serviceProviderDAO=new ServiceProviderDAOImple();
        boolean success=false;
        int spCount=0;
        try {
            for(int i = 0; i< spIDs.length; i++){
                success=serviceProviderDAO.assignServiceToSP(serviceID, Integer.parseInt(spIDs[i]));
                if(success){
                    spCount++;
                }
            }
            if(spCount==spIDs.length){
                response.getWriter().println(1);
            }else{
                response.getWriter().println(0);
            }

        } catch (SQLException throwables) {
            response.getWriter().println(throwables.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().println(e.getMessage());
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
