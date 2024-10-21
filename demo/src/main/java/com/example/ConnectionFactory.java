package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectionFactory {
  public static Connection getConnection() throws SQLException {
   
    String url = System.getenv("URL");
    String username = System.getenv("USERNAME");
    String password = System.getenv("PASSWORD");
    return DriverManager.getConnection(url, username, password);
  }
}

