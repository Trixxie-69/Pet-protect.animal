package Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:sqlite::memory:"; // In-memory database for demo
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}