package libraryManagement.storage;

import libraryManagement.models.Title;
import libraryManagement.utils.Utils;

import java.sql.*;
import java.util.Date;

public class TitleDAO extends AbstractDAO<Title> {

    protected String getSelectSQL() {
        return "SELECT * FROM title WHERE Id = ?";
    }

    protected Title convertToObject(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("Id");
        String name = resultSet.getString("Name");
        String category = resultSet.getString("Category");
        String author = resultSet.getString("Author");
        Date releaseDate = resultSet.getDate("Release_date");
        String publisher = resultSet.getString("Publisher");
        String language = resultSet.getString("Language");
        int price = resultSet.getInt("Price");
        return new Title(id, name, category, author, releaseDate, publisher, language, price);
    }

    protected String getDeleteSQL() {
        return "DELETE FROM title WHERE Id = ?";
    }

    protected String getExistsSQL() {
        return "SELECT EXISTS(SELECT * FROM title WHERE Id = ?)";
    }

    protected String getSearchSQL() {
        return """
                SELECT * FROM title
                WHERE Id LIKE "%"?"%" OR Name LIKE "%"?"%"
                """;
    }

    protected String getListSQL() {
        return "SELECT * FROM title";
    }

    protected String getListSQL(String id) {
        return null;
    }

    public void add(Title title) {
        try {
            Connection connection = Database.getConnection();
            String sqlQuery = """
                    INSERT INTO title
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, title.getId());
            preparedStatement.setString(2, title.getName());
            preparedStatement.setString(3, title.getCategory());
            preparedStatement.setString(4, title.getAuthor());
            preparedStatement.setDate(5, Utils.convertDateToSQLDate(title.getReleaseDate()));
            preparedStatement.setString(6, title.getPublisher());
            preparedStatement.setString(7, title.getLanguage());
            preparedStatement.setInt(8, title.getPrice());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Title title) {
        try {
            Connection connection = Database.getConnection();
            String sqlQuery = """
                    UPDATE title
                    SET Name = ?, Category = ?, Author = ?, Release_date = ?, Publisher = ?, Language = ?, Price = ?
                    WHERE Id = ?
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, title.getName());
            preparedStatement.setString(2, title.getCategory());
            preparedStatement.setString(3, title.getAuthor());
            preparedStatement.setDate(4, Utils.convertDateToSQLDate(title.getReleaseDate()));
            preparedStatement.setString(5, title.getPublisher());
            preparedStatement.setString(6, title.getLanguage());
            preparedStatement.setInt(7, title.getPrice());
            preparedStatement.setString(8, title.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
