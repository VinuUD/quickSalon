package com.g34.quicksalon.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

        private static Connection connection;

        public static Connection getConnection() throws ClassNotFoundException, SQLException {
            //singleton pattern
            if(connection ==null){
                Class.forName("com.mysql.jdbc.Driver");
                connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/quick_salon", "root", "toor");
            }
            return connection;
        }




}
