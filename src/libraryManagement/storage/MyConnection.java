package libraryManagement.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/library_management";
    private static final String USER = "root";
    private static final String PASS = "123456";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, USER, PASS);
    }
}
