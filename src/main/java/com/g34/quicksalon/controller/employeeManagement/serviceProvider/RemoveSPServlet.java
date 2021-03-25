package com.g34.quicksalon.controller.employeeManagement.serviceProvider;

import com.g34.quicksalon.dao.OwnerDAO;
import com.g34.quicksalon.dao.OwnerDAOImple;
import com.g34.quicksalon.dao.UserDAO;
import com.g34.quicksalon.dao.UserDAOImple;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RemoveSPServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idString = request.getParameter("id");
        String password = request.getParameter("password");
        int id = Integer.parseInt(idString);

        try {
            OwnerDAO owner = new OwnerDAOImple();
            int x = owner.removeServiceProvider(id, password);
            response.getWriter().println(x);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int spID= Integer.parseInt(request.getParameter("empID"));

        boolean success=false;
        //1) Set removedFlag=1 in emp table
        try {
            OwnerDAO ownerDAO =new OwnerDAOImple();
            success=ownerDAO.setRemovedFlagOfSp(spID);

            if(success){
                //2) Delete row from User table
                UserDAO userDAO=new UserDAOImple();
                success=userDAO.removeUser(spID);
                response.getWriter().println(success);
            }else{
                response.getWriter().println(success);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            response.getWriter().println(throwables.getMessage());
        } catch (ClassNotFoundException e) {
            response.getWriter().println(e.getMessage());
            e.printStackTrace();
        }


        //2) Delete from user table


    }
}
