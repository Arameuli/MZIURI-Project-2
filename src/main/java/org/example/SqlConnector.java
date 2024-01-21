package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnector {
    private Connection con;
    public SqlConnector(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/messenger", "root", "root");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public Connection getConnection(){
        return con;
    }
}

// table code

//    CREATE TABLE `chat` (
//        `username` varchar(45) DEFAULT NULL,
//        `password` varchar(45) DEFAULT NULL,
//        `message` varchar(45) DEFAULT NULL
//        ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci