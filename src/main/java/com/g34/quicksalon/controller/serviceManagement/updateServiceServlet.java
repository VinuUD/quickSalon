package com.g34.quicksalon.controller.serviceManagement;

import com.g34.quicksalon.dao.ServiceDAO;
import com.g34.quicksalon.dao.ServiceDAOImple;
import com.g34.quicksalon.dao.ServiceProviderDAO;
import com.g34.quicksalon.dao.ServiceProviderDAOImple;
import com.g34.quicksalon.model.Service;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

public class updateServiceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int serviceID = Integer.parseInt(request.getParameter("serviceID"));
        String serviceName=request.getParameter("serviceName");
        String serviceDescription=request.getParameter("serviceDescription");
        String timeTaken=request.getParameter("timeTaken");
        double price= Double.parseDouble(request.getParameter("price"));
        int holdFlag = Integer.parseInt(request.getParameter("holdFlag"));
        String[] spIDs = request.getParameterValues("spIDs[]");

        Service service = new Service();
        service.setServiceID(serviceID);
        service.setServiceName(serviceName);
        service.setServiceDescription(serviceDescription);
        service.setTimeTaken(timeTaken);
        service.setPrice(price);
        service.setHoldFlag(holdFlag);

        ServiceDAO serviceDAO = new ServiceDAOImple();
        ServiceProviderDAO serviceProviderDAO = new ServiceProviderDAOImple();
        try {
            int delete = serviceProviderDAO.deleteServiceFromServiceProvided(serviceID);
            System.out.println("methantawath awa");
            System.out.println(delete);

                System.out.println("methanta awa");
                int len = spIDs.length;
                for(int i=0; i<len; i++)
                {
                    serviceProviderDAO.assignServiceToSP(serviceID, Integer.parseInt(spIDs[i]));
                    System.out.println(spIDs[i]);
                }




            int x = serviceDAO.updateServiceTable(service);
            response.getWriter().println(x);
            System.out.println(x);


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int serviceID = Integer.parseInt(request.getParameter("serviceID"));
        ServiceDAO serviceDAO = new ServiceDAOImple();
        try {
            int x = serviceDAO.removeService(serviceID);
            response.getWriter().println(x);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
