package com.g34.quicksalon.controller.employeeManagement.serviceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AssignServiceSPListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // String spList=request.getParameterValues("spIDs");
            String[] spList=request.getParameterValues("spIDs");
            String serviceID=request.getParameter("serviceID");
            // for(int i = 0; i < )

        response.getWriter().println(spList[0]); //["3"])
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
