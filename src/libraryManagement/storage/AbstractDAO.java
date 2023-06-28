package libraryManagement.storage;

import libraryManagement.models.Identifiable;

import java.sql.*;
import java.util.ArrayList;

public abstract class AbstractDAO<T extends Identifiable> {
    public T get(String id) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getSelectSQL());
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            T i = convertToObject(resultSet);
            resultSet.close();
            connection.close();
            preparedStatement.close();
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected abstract String getSelectSQL();

    protected abstract T convertToObject(ResultSet resultSet) throws SQLException;

    public void delete(String id) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getDeleteSQL());
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate("SET FOREIGN_KEY_CHECKS=0");
            preparedStatement.executeUpdate();
            preparedStatement.executeUpdate("SET FOREIGN_KEY_CHECKS=1");
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected abstract String getDeleteSQL();

    public boolean exists(String id) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getExistsSQL());
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            boolean isExists = resultSet.getBoolean(1);
            resultSet.close();
            connection.close();
            preparedStatement.close();
            return isExists;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected abstract String getExistsSQL();

    private ArrayList<T> executeSQL(Connection connection, PreparedStatement preparedStatement) {
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<T> arrayList = new ArrayList<>();
            while (resultSet.next()) {
                arrayList.add(convertToObject(resultSet));
            }
            connection.close();
            preparedStatement.close();
            resultSet.close();
            return arrayList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public ArrayList<T> search(String keyword) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getSearchSQL());
            preparedStatement.setString(1, keyword);
            preparedStatement.setString(2, keyword);
            return executeSQL(connection, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected abstract String getSearchSQL();

    public ArrayList<T> list() {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getListSQL());
            return executeSQL(connection, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected abstract String getListSQL();

    public ArrayList<T> list(String id) {
        try {
            Connection connection = Database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getListSQL(id));
            preparedStatement.setString(1, id);
            return executeSQL(connection, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected abstract String getListSQL(String id);
}

