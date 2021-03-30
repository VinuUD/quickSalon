package com.g34.quicksalon.controller;

import com.g34.quicksalon.dao.OurStaffDAO;
import com.g34.quicksalon.dao.OurStaffDAOImple;
import com.g34.quicksalon.model.OurStaff;
import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class OurStaffServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res ) throws IOException {

        OurStaffDAO ourStaff = new OurStaffDAOImple();
        try {
            ArrayList<OurStaff> staffDetails = new ArrayList<>();
            staffDetails = ourStaff.getStaffDetails();

            String json = new Gson().toJson(staffDetails);
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            res.getWriter().write(json);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
