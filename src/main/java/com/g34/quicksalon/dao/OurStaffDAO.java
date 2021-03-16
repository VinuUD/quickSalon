package com.g34.quicksalon.dao;

import com.g34.quicksalon.model.OurStaff;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OurStaffDAO {
    public ArrayList<OurStaff> getStaffDetails() throws SQLException, ClassNotFoundException;
}
