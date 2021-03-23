package com.g34.quicksalon.controller.employeeManagement.manager;

import com.g34.quicksalon.dao.OwnerDAO;
import com.g34.quicksalon.dao.OwnerDAOImple;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateManagerServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("mee update eka");
        int eID = Integer.parseInt(req.getParameter("eID"));
        String cNum = req.getParameter("cNum");
        int salary = Integer.parseInt(req.getParameter("salary"));
        String email = req.getParameter("email");
        String address = req.getParameter("address");

        try {
            OwnerDAO owner = new OwnerDAOImple();
            int x = owner.updateManager(eID, cNum,salary,email,address);
            res.getWriter().println(x);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}