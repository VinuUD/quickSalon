package com.g34.quicksalon.controller;

import com.g34.quicksalon.entity.LoginInfo;
import com.g34.quicksalon.model.LoginModel;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String username=request.getParameter("username");
        String password=request.getParameter("password");

        LoginInfo loginInfo=new LoginInfo(username,password);
        LoginModel loginModel=new LoginModel();

        String userGroup = loginModel.login(loginInfo);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }
}
