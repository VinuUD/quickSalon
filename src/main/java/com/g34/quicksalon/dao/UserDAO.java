package com.g34.quicksalon.dao;

import java.sql.SQLException;

public interface UserDAO {

    public boolean isRegisteredUser(String email) throws SQLException, ClassNotFoundException;

    public boolean resetPassword(String email,String password) throws SQLException, ClassNotFoundException;
}
