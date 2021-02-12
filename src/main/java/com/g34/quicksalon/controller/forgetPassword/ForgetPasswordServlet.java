package com.g34.quicksalon.controller.forgetPassword;

import com.g34.quicksalon.controller.login.JavaMailUtil;
import com.g34.quicksalon.dao.UserDAO;
import com.g34.quicksalon.dao.UserDAOImple;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.sql.SQLException;

public class ForgetPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String pinCode=request.getParameter("pin");
        HttpSession session=request.getSession();
        String sentPin= (String) session.getAttribute("pin");

        if(pinCode.equals(sentPin)){
            //same pin
            out.println(1);
        }else{
            //wrong
            out.println(0);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email=request.getParameter("email");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        UserDAO userDAO=new UserDAOImple();
        try {
            if(userDAO.isRegisteredUser(email)){
                HttpSession session = request.getSession();

                JavaMailUtil javaMailUtil=new JavaMailUtil();
                SecureRandom rand = new SecureRandom();
                String pin=""+rand.nextInt(10000);

                session.setAttribute("email",email);
                session.setAttribute("pin",pin);

                try {
                    javaMailUtil.sendMail(email,""+pin);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                out.println(1);
            }else{
                out.println(0);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
