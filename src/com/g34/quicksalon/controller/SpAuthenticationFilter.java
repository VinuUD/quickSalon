package com.g34.quicksalon.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/serviceProvider/*")
public class SpAuthenticationFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request=(HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse) resp;

        HttpSession session = request.getSession(false);


        if (session == null) {
            // Session is not created.
            response.sendRedirect("../restricted.html");
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.html");
//            dispatcher.forward(request, response);


        } else {
            // Session is already created.
            HttpSession ses= request.getSession();

            //check is he/she sp?
            int userType=(Integer) ses.getAttribute("userType");

            if( userType== 3){
                chain.doFilter(req, resp);
            }else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("../restricted.html");
                dispatcher.forward(request, response);
            }


        }


    }

    public void init(FilterConfig config) throws ServletException {

    }

}