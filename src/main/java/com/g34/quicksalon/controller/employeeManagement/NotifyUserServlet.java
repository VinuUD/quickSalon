package com.g34.quicksalon.controller.employeeManagement;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NotifyUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        request.getParameterValues("userTypes");
        String[] userTypes = request.getParameterValues("userTypes[]");
        for(int i = 0; i < userTypes.length;i++)
        {
            System.out.println(userTypes[i]);
        }

        String json = new Gson().toJson(userTypes);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
