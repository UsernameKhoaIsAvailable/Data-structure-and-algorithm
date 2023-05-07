package libraryManagement.storage;

import libraryManagement.models.Transaction;
import libraryManagement.utils.Utils;

import java.sql.*;

public class TransactionDAO extends AbstractDAO<Transaction> {
    public TransactionDAO() {
        try {
            Connection connection = MyConnection.getConnection();
            Statement statement = connection.createStatement();
            String sqlQuery = """
                    CREATE TABLE IF NOT EXISTS transaction (
                            Id varchar(20) NOT NULL,
                            Borrow_date DATE,
                            Number_of_transaction_line INT,
                            Returned BOOL,
                            ReaderId varchar(20),
                            PRIMARY KEY (Id),
                            FOREIGN KEY (ReaderId) REFERENCES reader(Id)
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
        return "SELECT * FROM transaction WHERE Id = ?";
    }

    protected Transaction convertToObject(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("Id");
        Date borrowDate = resultSet.getDate("Borrow_date");
        int transactionLine = resultSet.getInt("Number_of_transaction_line");
        boolean returned = resultSet.getBoolean("Returned");
        String readerId = resultSet.getString("ReaderId");
        return new Transaction(id, borrowDate, transactionLine, returned, readerId);
    }

    protected String getDeleteSQL() {
        return "DELETE FROM transaction WHERE Id = ?";
    }

    protected String getExistsSQL() {
        return "SELECT EXISTS(SELECT * FROM transaction WHERE Id = ?)";
    }

    protected String getSearchSQL() {
        return """
                SELECT * FROM transaction
                WHERE Id LIKE "%"?"%" OR ReaderId LIKE "%"?"%"
                """;
    }

    protected String getListSQL() {
        return null;

    }

    protected String getListSQL(String id) {
        return """
                SELECT * FROM transaction
                WHERE ReaderId = ? AND NOT Returned
                """;
    }

    public void add(Transaction transaction) {
        try {
            Connection connection = MyConnection.getConnection();
            String sqlQuery = """
                    INSERT INTO transaction
                    VALUES (?, ?, ?, ?, ?)
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, transaction.getId());
            preparedStatement.setDate(2, Utils.convertDateToSQLDate(transaction.getBorrowDate()));
            preparedStatement.setInt(3, transaction.getTransactionLine());
            preparedStatement.setBoolean(4, transaction.isReturned());
            preparedStatement.setString(5, transaction.getReaderId());
            preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Transaction transaction) {
        try {
            Connection connection = MyConnection.getConnection();
            String sqlQuery = """
                    UPDATE transaction
                    SET Borrow_date = ?, Number_of_transaction_line = ?, Returned = ?, ReaderId = ?
                    WHERE Id = ?
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setDate(1, Utils.convertDateToSQLDate(transaction.getBorrowDate()));
            preparedStatement.setInt(2, transaction.getTransactionLine());
            preparedStatement.setBoolean(3, transaction.isReturned());
            preparedStatement.setString(4, transaction.getReaderId());
            preparedStatement.setString(5, transaction.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
