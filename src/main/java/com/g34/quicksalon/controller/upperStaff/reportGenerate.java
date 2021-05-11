package com.g34.quicksalon.controller.upperStaff;

import com.g34.quicksalon.dao.reportDAO;
import com.g34.quicksalon.dao.reportDAOImple;
import com.g34.quicksalon.database.DBConnection;
import com.g34.quicksalon.model.Complaint;
import com.g34.quicksalon.model.Leave2;
import com.g34.quicksalon.model.PersonalSchedule;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

/*@WebServlet("/report2")*/
public class reportGenerate extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        ArrayList<Leave2> leaveArray = new ArrayList<>();
        ArrayList<Complaint> complaintArray = new ArrayList<>();

        PrintWriter out = res.getWriter();
     /*   out.println("hi there  in do get");*/
        String service = req.getParameter("services");
        String from_date = req.getParameter("from");
        String to_date = req.getParameter("to");
        reportDAO reportdao = new reportDAOImple();
        if (service.equals("leave")) {
       /*     out.print("display the leaves");*/
            try {
                if (from_date.equals("") && to_date.equals("")) {
                    leaveArray = reportdao.getLeaveService();
                    String json = new Gson().toJson(leaveArray);
                    res.setContentType("application/json");
                    res.setCharacterEncoding("UTF-8");
                    res.getWriter().write(json);
/*
                    out.println(leaveArray.get(0).getLeaverID());
*/

                }
                else if (!from_date.equals("") && !to_date.equals("")) {
                    leaveArray = reportdao.getLeaveAll(from_date, to_date);

                    String json = new Gson().toJson(leaveArray);
                    res.setContentType("application/json");
                    res.setCharacterEncoding("UTF-8");
                    res.getWriter().write(json);
                }
                else if (!from_date.equals("") && to_date.equals("")) {
                    leaveArray = reportdao.getLeaveServiceAndFrom(from_date);
                    String json = new Gson().toJson(leaveArray);
                    res.setContentType("application/json");
                    res.setCharacterEncoding("UTF-8");
                    res.getWriter().write(json);
                }
                else if (from_date.equals("") && !to_date.equals("")) {
                    leaveArray = reportdao.getLeaveServiceAndTo(to_date);
                    String json = new Gson().toJson(leaveArray);
                    res.setContentType("application/json");
                    res.setCharacterEncoding("UTF-8");
                    res.getWriter().write(json);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else if (service.equals("complaint")) {
          /*  out.print("display the cpmplantss");*/
            try {
                if (from_date.equals("") && to_date.equals("")) {
                    complaintArray = reportdao.getComplaintService();
                  /*  out.println(complaintArray.get(0).getDetails());*/
                    String json = new Gson().toJson(complaintArray);
                    res.setContentType("application/json");
                    res.setCharacterEncoding("UTF-8");
                    res.getWriter().write(json);
                }
                else if (!from_date.equals("") && !to_date.equals("")) {
                    complaintArray = reportdao.getComplaintAll(from_date, to_date);
                    String json = new Gson().toJson(complaintArray);
                    res.setContentType("application/json");
                    res.setCharacterEncoding("UTF-8");
                    res.getWriter().write(json);
                }
                else if (!from_date.equals("") && to_date.equals("")) {
                    complaintArray = reportdao.getComplaintServiceAndFrom(from_date);
                    String json = new Gson().toJson(complaintArray);
                    res.setContentType("application/json");
                    res.setCharacterEncoding("UTF-8");
                    res.getWriter().write(json);            }
                else if (from_date.equals("") && !to_date.equals("")) {
                    complaintArray = reportdao.getComplaintServiceAndTo( to_date);
                    String json = new Gson().toJson(complaintArray);
                    res.setContentType("application/json");
                    res.setCharacterEncoding("UTF-8");
                    res.getWriter().write(json);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res ) throws IOException {
        PrintWriter outa = res.getWriter();
        outa.println("hi there  in do posr");
    }
}


