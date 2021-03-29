package com.g34.quicksalon.controller.employeeManagement.leave;

import com.g34.quicksalon.dao.OwnerDAO;
import com.g34.quicksalon.dao.OwnerDAOImple;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddLeaveCancelServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String idString = req.getParameter("id");
        int id = Integer.parseInt(idString);
        System.out.println(id);
        System.out.println("Cancel Servlet");


        try {
            OwnerDAO owner = new OwnerDAOImple();
            boolean x = owner.cancelLeave(id);
            if(x == false)
            {

                res.getWriter().println(1);
            }
            if(x == true)
            {
                res.getWriter().println(0);
            }
//            res.getWriter().println(x);
            System.out.println(x);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
