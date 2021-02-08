package com.g34.quicksalon.controller.login;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class SessionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // session.setAttribute("userID", userID);
        // session.setAttribute("username", username);
        // session.setAttribute("userType", userType);

        PrintWriter out= response.getWriter();
        HttpSession s= request.getSession(false);

        if(s != null){
            HttpSession session= request.getSession();
            String userName= String.valueOf(session.getAttribute("username"));
            String userType= String.valueOf(session.getAttribute("userType"));
    
            if(request.getParameter("utype")!=null){
                out.println(userType);
            }else{
                out.println(userName);
            }

        }else{
            //Session is not configured
            out.println(9);

        }

        

    
    }
}
