package libraryManagement.storage;

import libraryManagement.models.TransactionLine;
import libraryManagement.utils.Utils;

import java.sql.*;

public class TransactionLineDAO extends AbstractDAO<TransactionLine> {
    public TransactionLineDAO() {
        try {
            Connection connection = MyConnection.getConnection();
            Statement statement = connection.createStatement();
            String sqlQuery = """
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected String getSelectSQL() {
        return "SELECT * FROM transaction_line WHERE Id = ?";
    }

    protected TransactionLine convertToObject(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("Id");
        Date expireDate = resultSet.getDate("Expire_date");
        boolean extended = resultSet.getBoolean("Extended");
        Date returnDate = resultSet.getDate("Return_date");
        double fine = resultSet.getDouble("Fine");
        boolean returned = resultSet.getBoolean("Returned");
        String bookId = resultSet.getString("BookId");
        String transactionId = resultSet.getString("TransactionId");
        return new TransactionLine(id, expireDate, extended, returnDate, fine, returned, bookId, transactionId);
    }

    protected String getDeleteSQL() {
        return "DELETE FROM transaction_line WHERE Id = ?";
    }

    protected String getExistsSQL() {
        return "SELECT EXISTS(SELECT * FROM transaction_line WHERE Id = ?)";
    }

    protected String getSearchSQL() {
        return """
                SELECT * FROM transaction_line
                WHERE Id LIKE "%"?"%" OR TransactionId LIKE "%"?"%"
                """;
    }

    protected String getListSQL() {
        return null;
    }

    protected String getListSQL(String id) {
        return """
                SELECT * FROM transaction_line
                WHERE transactionId = ? AND NOT Returned
                """;
    }

    public void add(TransactionLine transactionLine) {
        try {
            Connection connection = MyConnection.getConnection();
            String sqlQuery = """
                    INSERT INTO transaction_line
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, transactionLine.getId());
            preparedStatement.setDate(2, Utils.convertDateToSQLDate(transactionLine.getExpireDate()));
            preparedStatement.setBoolean(3, transactionLine.isExtended());
            preparedStatement.setDate(4, Utils.convertDateToSQLDate(transactionLine.getReturnDate()));
            preparedStatement.setDouble(5, transactionLine.getFine());
            preparedStatement.setBoolean(6, transactionLine.isReturned());
            preparedStatement.setString(7, transactionLine.getBookId());
            preparedStatement.setString(8, transactionLine.getTransactionId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(TransactionLine transactionLine) {
        try {
            Connection connection = MyConnection.getConnection();
            String sqlQuery = """
                    UPDATE transaction_line
                    SET Expire_date = ?, Extended = ?, Return_date = ?, Fine = ?, Returned = ?, BookId = ?, TransactionId = ?
                    WHERE Id = ?
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setDate(1, Utils.convertDateToSQLDate(transactionLine.getExpireDate()));
            preparedStatement.setBoolean(2, transactionLine.isExtended());
            preparedStatement.setDate(3, Utils.convertDateToSQLDate(transactionLine.getReturnDate()));
            preparedStatement.setDouble(4, transactionLine.getFine());
            preparedStatement.setBoolean(5, transactionLine.isReturned());
            preparedStatement.setString(6, transactionLine.getBookId());
            preparedStatement.setString(7, transactionLine.getTransactionId());
            preparedStatement.setString(8, transactionLine.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
