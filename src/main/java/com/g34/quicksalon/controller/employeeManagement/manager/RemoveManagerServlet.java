package com.g34.quicksalon.controller.employeeManagement.manager;

import com.g34.quicksalon.dao.OwnerDAO;
import com.g34.quicksalon.dao.OwnerDAOImple;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RemoveManagerServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String idString = req.getParameter("id");
        String password = req.getParameter("password");
        int id = Integer.parseInt(idString);

        try {
            OwnerDAO owner = new OwnerDAOImple();
            int x = owner.removeManager(id, password);
            res.getWriter().println(x);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
