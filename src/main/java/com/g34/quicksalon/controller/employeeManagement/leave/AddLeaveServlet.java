package com.g34.quicksalon.controller.employeeManagement.leave;

import com.g34.quicksalon.dao.*;
import com.g34.quicksalon.model.AppointmentDetailsForLeave;
import com.g34.quicksalon.model.Leave;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class AddLeaveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int leaverID= Integer.parseInt(request.getParameter("leaverID"));
        String leaveType=request.getParameter("leaveType");

        String fromDate=request.getParameter("fromDate");
        String toDate=request.getParameter("toDate");

        String fromTime = request.getParameter("fromTime");
        String toTime = request.getParameter("toTime");

        //Obj of Leave
        Leave leave=new Leave(leaverID,fromDate,toDate,fromTime,toTime,leaveType,'n');
        Boolean success;

//        EmployeeManagementDAO employeeManagementDAO=new EmployeeManagementDAOImple();
        try {
            OwnerDAO ownerDAO=new OwnerDAOImple();
            success=ownerDAO.applyLeave(leave);
            response.getWriter().println(success);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("shehan");
        try {
            OwnerDAO owner = new OwnerDAOImple();
            ArrayList<Leave> leaversDetails = new ArrayList<>();
            leaversDetails = owner.getLeaversDetails();

            ArrayList<AppointmentDetailsForLeave> appointmentDetails = new ArrayList<>();
            appointmentDetails = owner.appointmentDetails();

            String json = new Gson().toJson(leaversDetails);
            String json1 = new Gson().toJson(appointmentDetails);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
//            res.getWriter().write(json1);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String timeStrToTime(String time){
        //Covert String time to Time- 18:30:00
        String[] arrOft = time.split(":", 2);
        int h= Integer.parseInt(arrOft[0]);
        String m= arrOft[1].split(" ",2)[0];
        if(m.length()==1){
            m='0'+m;
        }
        //        Convert 12 to 24
        if(arrOft[1].split(" ",2)[1].equals("pm")){
            h=h+12;
        }
        if(h<10){
            return "0"+h+":"+m+":00";
        }else{
            return ""+h+":"+m+":00";
        }

    }
}
