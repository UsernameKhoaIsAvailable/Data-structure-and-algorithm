package libraryManagement.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/library_management?allowMultiQueries=true";
    private static final String USER = "root";
    private static final String PASS = "Pass@123456";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, USER, PASS);
    }

    public static void init() throws SQLException {
        Connection connection = Database.getConnection();
        Statement statement = connection.createStatement();
        String sqlQuery = """
                        CREATE TABLE IF NOT EXISTS reader (
                        Id varchar(20) NOT NULL,
                        Name varchar(30) NOT NULL,
                        Gender BOOL,
                        Date_of_birth DATE,
                        Id_card varchar(20),
                        Address varchar(255),
                        Date DATE,
                        Can_borrow BOOL,
                        Number_of_book_borrowed INT,
                        PRIMARY KEY (Id)
                        );
                        
                        CREATE TABLE IF NOT EXISTS title (
                        Id varchar(20) NOT NULL,
                        Name varchar(255) NOT NULL,
                        Category varchar(255),
                        Author varchar(30),
                        Release_date DATE,
                        Publisher varchar(255),
                        Language varchar(30),
                        Price INT,
                        PRIMARY KEY (Id)
                        );
                        
                        CREATE TABLE IF NOT EXISTS book (
                        Id varchar(20) NOT NULL,
                        Import_date DATE,
                        Location varchar(255),
                        Borrowed BOOL,
                        TitleId varchar(20),
                        PRIMARY KEY (Id),
                        FOREIGN KEY (TitleId) REFERENCES title(Id)
                        );
                        
                        CREATE TABLE IF NOT EXISTS transaction (
                        Id varchar(20) NOT NULL,
                        Borrow_date DATE,
                        Number_of_transaction_line INT,
                        Returned BOOL,
                        ReaderId varchar(20),
                        PRIMARY KEY (Id),
                        FOREIGN KEY (ReaderId) REFERENCES reader(Id)
                        );
                        
                        CREATE TABLE IF NOT EXISTS transaction_line (
                        Id varchar(20) NOT NULL,
                        Expire_date DATE,
                        Extended BOOL,
                        Return_date DATE,
                        Fine DOUBLE(10,2),
                        Returned BOOL,
                        BookId varchar(20),
                        TransactionId varchar(20),
                        PRIMARY KEY (Id),
                        FOREIGN KEY (BookId) REFERENCES book(Id),
                        FOREIGN KEY (TransactionId) REFERENCES transaction(Id)
                        );
                """;
        statement.executeUpdate(sqlQuery);
        statement.close();
        connection.close();
    }
}
