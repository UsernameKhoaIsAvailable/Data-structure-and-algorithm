package libraryManagement.storage;

import libraryManagement.models.Reader;
import libraryManagement.utils.Utils;

import java.sql.*;


public class ReaderDAO extends AbstractDAO<Reader> {

    protected String getSelectSQL() {
        return "SELECT * FROM reader WHERE Id = ?";
    }

    protected Reader convertToObject(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("Id");
        String name = resultSet.getString("Name");
        boolean gender = resultSet.getBoolean("Gender");
        java.sql.Date dateOfBirth = resultSet.getDate("Date_of_birth");
        String idCard = resultSet.getString("Id_card");
        String address = resultSet.getString("Address");
        java.sql.Date date = resultSet.getDate("Date");
        boolean canBorrow = resultSet.getBoolean("Can_borrow");
        int bookBorrowed = resultSet.getInt("Number_of_book_borrowed");
        return new Reader(id, date, name, dateOfBirth, gender, idCard, address, canBorrow, bookBorrowed);
    }

    protected String getDeleteSQL() {
        return "DELETE FROM reader WHERE Id = ?";
    }

    protected String getExistsSQL() {
        return "SELECT EXISTS(SELECT * FROM reader WHERE Id = ?)";
    }

    protected String getSearchSQL() {
        return """
                SELECT * FROM reader
                WHERE Id LIKE "%"?"%" OR Name LIKE "%"?"%"
                """;
    }

    protected String getListSQL() {
        return "SELECT * FROM reader";
    }

    protected String getListSQL(String id) {
        return null;
    }

    public void add(Reader reader) {
        try {
            Connection connection = Database.getConnection();
            String sqlQuery = """
                    INSERT INTO reader
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, reader.getId());
            preparedStatement.setString(2, reader.getName());
            preparedStatement.setBoolean(3, reader.getGender());
            preparedStatement.setDate(4, Utils.convertDateToSQLDate(reader.getDateOfBirth()));
            preparedStatement.setString(5, reader.getIdCard());
            preparedStatement.setString(6, reader.getAddress());
            preparedStatement.setDate(7, Utils.convertDateToSQLDate(reader.getDate()));
            preparedStatement.setBoolean(8, reader.isAllowedToBorrow());
            preparedStatement.setInt(9, reader.getBookBorrowed());
            preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Reader reader) {
        try {
            Connection connection = Database.getConnection();
            String sqlQuery = """
                    UPDATE reader
                    SET Name = ?, Gender = ?, Date_of_birth = ?, Id_card = ?, Address = ?, Date = ?, Can_borrow = ?, Number_of_book_borrowed = ?
                    WHERE Id = ?
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, reader.getName());
            preparedStatement.setBoolean(2, reader.getGender());
            preparedStatement.setDate(3, Utils.convertDateToSQLDate(reader.getDateOfBirth()));
            preparedStatement.setString(4, reader.getIdCard());
            preparedStatement.setString(5, reader.getAddress());
            preparedStatement.setDate(6, Utils.convertDateToSQLDate(reader.getDate()));
            preparedStatement.setBoolean(7, reader.isAllowedToBorrow());
            preparedStatement.setInt(8, reader.getBookBorrowed());
            preparedStatement.setString(9, reader.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

