package com.g34.quicksalon.dao;

import com.g34.quicksalon.database.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAOImple implements UserDAO{

    @Override
    public boolean isRegisteredUser(String email) throws SQLException, ClassNotFoundException {

        PreparedStatement stmt = DBConnection.getConnection().prepareStatement("SELECT userID FROM j4f9qe_user WHERE email=?");
        stmt.setString(1,email);

        ResultSet resultSet = stmt.executeQuery();
        if (resultSet.next()) {
          return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean resetPassword(String email,String password) throws SQLException, ClassNotFoundException {

        PreparedStatement stmt = DBConnection.getConnection().prepareStatement("UPDATE j4f9qe_user SET password=? WHERE email=? ");
        stmt.setString(1,password);
        stmt.setString(2,email);

        int reset=stmt.executeUpdate();
        if (reset>0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean removeUser(int empID) throws SQLException, ClassNotFoundException {
        Statement stmt = DBConnection.getConnection().createStatement();
        String query = "DELETE FROM j4f9qe_user WHERE userID IN(SELECT userID FROM j4f9qe_employee WHERE employeeID="+empID+")";
        int deletedRows = stmt.executeUpdate(query);

        if(deletedRows==1){
            return true;
        }else{
            return false;
        }

    }
}
