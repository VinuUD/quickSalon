package com.g34.quicksalon.dao;

import com.g34.quicksalon.database.DBConnection;
import com.g34.quicksalon.model.LoginInfo;
import com.g34.quicksalon.model.UserLoginModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class LoginDAOImple implements LoginDAO {

     //check login and if true, return userType & userID
    // @Override
     public UserLoginModel  login(UserLoginModel userLoginModel) throws SQLException, ClassNotFoundException {
         PreparedStatement stmt = DBConnection.getConnection().prepareStatement("SELECT userID,userName,userType FROM j4f9qe_user WHERE email=? AND password=? ;");
         stmt.setString(1,userLoginModel.getEmail());
         stmt.setString(2, userLoginModel.getPassword());

         ResultSet resultSet = stmt.executeQuery();
         if (resultSet.next()) {
             userLoginModel.setId(resultSet.getInt(1));
             userLoginModel.setUsername(resultSet.getString(2));
             userLoginModel.setUserType(resultSet.getInt(3));
             return userLoginModel;
         } else {
             return null;
         }
     }

}
