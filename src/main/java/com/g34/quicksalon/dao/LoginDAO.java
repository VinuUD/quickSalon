package com.g34.quicksalon.dao;

import java.sql.SQLException;
import java.util.HashMap;

import com.g34.quicksalon.model.LoginInfo;
import com.g34.quicksalon.model.UserLoginModel;

public interface LoginDAO {

    public UserLoginModel login (UserLoginModel userLoginModel) throws SQLException, ClassNotFoundException ;

}
