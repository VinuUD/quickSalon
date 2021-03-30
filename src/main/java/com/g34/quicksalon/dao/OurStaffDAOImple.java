package com.g34.quicksalon.dao;

import com.g34.quicksalon.database.DBConnection;
import com.g34.quicksalon.model.OurStaff;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OurStaffDAOImple implements OurStaffDAO {


    private ArrayList<OurStaff> staffDetails = new ArrayList<>();
    public ArrayList<OurStaff> getStaffDetails() throws SQLException, ClassNotFoundException {

        Connection con = DBConnection.getConnection();

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select j4f9qe_employee.firstName, j4f9qe_employee.lastName,  j4f9qe_employee.isUpperStaffFlag from j4f9qe_employee WHERE j4f9qe_employee.removedFlag = 0");

        while(rs.next())
        {
            staffDetails.add(new OurStaff(rs.getString(1), rs.getString(2), rs.getInt(3)));

        }

        return staffDetails;
    }
}
