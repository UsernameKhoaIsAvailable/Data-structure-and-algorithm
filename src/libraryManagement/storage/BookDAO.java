package libraryManagement.storage;

import libraryManagement.models.Book;
import libraryManagement.utils.Utils;

import java.sql.*;
import java.util.Date;

public class BookDAO extends AbstractDAO<Book> {
    public BookDAO() {
        try {
            Connection connection = MyConnection.getConnection();
            Statement statement = connection.createStatement();
            String sqlQuery = """
                    CREATE TABLE IF NOT EXISTS book (
                            Id varchar(20) NOT NULL,
                            Import_date DATE,
                            Location varchar(255),
                            Borrowed BOOL,
                            TitleId varchar(20),
                            PRIMARY KEY (Id),
                            FOREIGN KEY (TitleId) REFERENCES title(Id)
                            );
                    """;
            statement.executeUpdate(sqlQuery);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected String getSelectSQL() {
        return "SELECT * FROM book WHERE Id = ?";
    }

    protected Book convertToObject(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("Id");
        Date importDate = resultSet.getDate("Import_date");
        String location = resultSet.getString("Location");
        boolean borrowed = resultSet.getBoolean("Borrowed");
        String titleId = resultSet.getString("TitleId");
        return new Book(id, importDate, location, borrowed, titleId);
    }

    protected String getDeleteSQL() {
        return "DELETE FROM book WHERE Id = ?";
    }

    protected String getExistsSQL() {
        return "SELECT EXISTS(SELECT * FROM book WHERE Id = ?)";
    }

    protected String getSearchSQL() {
        return """
                SELECT * FROM book
                WHERE TitleId LIKE "%"?"%" OR Id LIKE "%"?"%"
                """;
    }

    protected String getListSQL() {
        return null;
    }

    protected String getListSQL(String id) {
        return """
                SELECT * FROM book
                WHERE TitleId = ? AND NOT Borrowed
                """;
    }

    public void add(Book book) {
        try {
            Connection connection = MyConnection.getConnection();
            String sqlQuery = """
                    INSERT INTO book
                    VALUES (?, ?, ?, ?, ?)
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, book.getId());
            preparedStatement.setDate(2, Utils.convertDateToSQLDate(book.getImportDate()));
            preparedStatement.setString(3, book.getLocation());
            preparedStatement.setBoolean(4, book.isBorrowed());
            preparedStatement.setString(5, book.getTitleId());
            preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Book book) {
        try {
            Connection connection = MyConnection.getConnection();
            String sqlQuery = """
                    UPDATE book
                    SET Import_date = ?, Location = ?, Borrowed = ?, TitleId = ?
                    WHERE Id = ?
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setDate(1, Utils.convertDateToSQLDate(book.getImportDate()));
            preparedStatement.setString(2, book.getLocation());
            preparedStatement.setBoolean(3, book.isBorrowed());
            preparedStatement.setString(4, book.getTitleId());
            preparedStatement.setString(5, book.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
