package com.g34.quicksalon.controller.serviceProvider;

import com.g34.quicksalon.dao.AppointmentDAO;
import com.g34.quicksalon.dao.AppointmentDAOImple;
import com.g34.quicksalon.dao.CustomerDAOImple;
import com.g34.quicksalon.dao.ServiceDAOImple;
import com.g34.quicksalon.dao.ServiceProviderDAOImple;
import com.g34.quicksalon.model.Appointment;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

public class SpAppointmentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        int spId = Integer.parseInt(request.getParameter("spId"));
        int serviceID = Integer.parseInt(request.getParameter("serviceID"));
        String fName = request.getParameter("fname");
        String lName = request.getParameter("lname");
        String telephone = request.getParameter("telephone");
        long d = Long.parseLong(request.getParameter("date"));
        Date date = new Date(d);
        String time = request.getParameter("time");
        String timeTaken = request.getParameter("timeTaken");

        int customerId = 0;
        int qId = 0;
        boolean assignService;
        boolean assignedService;
        Time startTime = Time.valueOf(timeStrToTime(time));
        Time endTime = Time.valueOf(plusTime(timeStrToTime(time), timeTaken));

        CustomerDAOImple customerModel = new CustomerDAOImple();
        AppointmentDAO appointmentModel = new AppointmentDAOImple();
        ServiceProviderDAOImple serviceProviderModel=new ServiceProviderDAOImple();
        ServiceDAOImple serviceModel=new ServiceDAOImple();

        try {
            //Add add Walk-in customer to customer table
            customerId=customerModel.addWalkInCustomers(fName,lName,telephone);

            if(customerId!=0){
                Appointment appointment=new Appointment(0,customerId,date.toString(),startTime.toString(),endTime.toString(),0);
                 //Place an appointment
                qId= appointmentModel.placeAppointment(appointment);
                if(qId!=0){
                    //Appointment done//Update the appointmentAssigned table
                    assignService=serviceProviderModel.assignedService(qId,spId);
                    if(assignService){
                        //sp assigned for appointment// Update the  j4f9qe_appointmentservice table
                        assignedService=serviceModel.addServicetoAppointments(qId,serviceID);
                        if(assignedService){
                            out.println("Success");
                        }else{
                            //Rollback
                            out.println("Error occurred in appointmentservice table--");
                        }

                    }else {
                        //Rollback
                        out.println("Error occurred in assignService table--");
                    }

                }

            }else {
                //Rollback
                out.println("Error occurred in assignService table--");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//        out.println("Time-"+startTime+","+endTime+"--"+customerId);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,NumberFormatException {

        ArrayList<Integer> qIds=new ArrayList<Integer>();
        int spId= Integer.parseInt(request.getParameter("spId"));
        try {
            AppointmentDAO appointmentModel = new AppointmentDAOImple();
            qIds=appointmentModel.getAllAppointmentsBySP(spId);
            String json = new Gson().toJson(qIds);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        }catch (Exception e){
            response.getWriter().println(e.getMessage());
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

    public String plusTime(String time,String timeTaken){
        String[] arrOftt = timeTaken.split(":", 2);
        int h1=Integer.parseInt(arrOftt[0]);
        int m1=Integer.parseInt(arrOftt[1]);

        int m;
        // time=18:30:00
        String[] arrOft = time.split(":", 3);
        int ht=Integer.parseInt(arrOft[0]);
        int mt=Integer.parseInt(arrOft[1]);

        if((m1+mt)>60){
            m=(m1+mt)%60;
            ht++;
        }else{
            m=mt+m1;
        }

        if(m==0){
            mt='0'+mt;
        }
        ht=ht+h1;

        if(ht<10){
            return "0"+ht+":"+m+":00";
        }else{
            return ""+ht+":"+m+":00";
        }

    }



}
