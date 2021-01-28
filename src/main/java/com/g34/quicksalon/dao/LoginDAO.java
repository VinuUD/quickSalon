package com.g34.quicksalon.dao;

import java.sql.SQLException;
import java.util.HashMap;

import com.g34.quicksalon.model.LoginInfo;

public interface LoginDAO {

    public HashMap<String,Integer> getUserType(LoginInfo loginInfo) throws SQLException, ClassNotFoundException ;

}
