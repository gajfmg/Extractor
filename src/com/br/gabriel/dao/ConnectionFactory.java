package com.br.gabriel.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://projectdata.chbhkdio7enc.sa-east-1.rds.amazonaws.com:3306/db_project_data", "gabriel", "thudor95");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
