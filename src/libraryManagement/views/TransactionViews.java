package libraryManagement.views;

import libraryManagement.models.Transaction;
import libraryManagement.models.TransactionLine;
import libraryManagement.storage.TransactionDAO;
import libraryManagement.storage.TransactionLineDAO;
import libraryManagement.utils.Utils;

import java.util.Date;

public class TransactionViews {
    public static Transaction addTransaction(String readerId) {
        String id = Utils.generateId(18);
        Date borrowDate = Utils.getDate();
        Transaction transaction = new Transaction(id, borrowDate, readerId);
        TransactionDAO transactionDAO = new TransactionDAO();
        transactionDAO.add(transaction);
        return transaction;
    }

    public static TransactionLine addTransactionLine(Date borrowDate, String bookId, String transactionId) {
        String id = Utils.generateId(19);
        Date expireDate = Utils.calculateExpireDate(borrowDate);
        TransactionLine transactionLine = new TransactionLine(id, expireDate, bookId, transactionId);
        transactionLine.setReturnDate(expireDate);
        TransactionLineDAO transactionLineDAO = new TransactionLineDAO();
        transactionLineDAO.add(transactionLine);
        return transactionLine;
    }
}