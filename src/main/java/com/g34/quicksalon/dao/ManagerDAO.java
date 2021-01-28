package com.g34.quicksalon.dao;

import java.util.ArrayList;

import com.g34.quicksalon.model.ManagerDetails;
import com.g34.quicksalon.model.ManagerDetailsForView;

public interface ManagerDAO {

    public int addManager(ManagerDetails manager) throws Exception;
    
    public ArrayList<ManagerDetailsForView> getManagersDetails() throws Exception;

    
}
