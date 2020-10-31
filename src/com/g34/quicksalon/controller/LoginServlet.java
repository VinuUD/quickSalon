package com.g34.quicksalon.controller;

import com.g34.quicksalon.entity.LoginInfo;
import com.g34.quicksalon.model.LoginModel;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
<<<<<<< HEAD
        try {
            PrintWriter out=response.getWriter();
            out.println("Hello");
        } catch (IOException e) {
            e.printStackTrace();
        }
=======
        String username=request.getParameter("username");
        String password=request.getParameter("password");

        LoginInfo loginInfo=new LoginInfo(username,password);
        LoginModel loginModel=new LoginModel();

        String userGroup = loginModel.login(loginInfo);


>>>>>>> e435ff51c8d670f6ff430556c7e4bceef3cc5e17
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }
}
