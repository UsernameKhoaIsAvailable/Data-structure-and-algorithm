package libraryManagement.views;

import libraryManagement.models.Book;
import libraryManagement.models.Transaction;
import libraryManagement.models.TransactionLine;
import libraryManagement.storage.Storage;
import libraryManagement.utils.Utils;
import java.util.Date;

public class TransactionViews {
    public static Transaction addTransaction() {
        String id = Utils.generateId(14);
        Date borrowDate = Utils.getDate();
        return new Transaction(borrowDate, id);
    }

    public static TransactionLine addTransactionLine(Date borrowDate, Book book, int price) {
        String id = Utils.generateId(14);
        Date expireDate = Utils.calculateExpireDate(borrowDate);
        String bookId = book.getId();
        TransactionLine transactionLine = new TransactionLine(id, expireDate, bookId);
        transactionLine.setFine(price + price * 0.1);
        Storage<TransactionLine> transactionLineStorage = new Storage<>(TransactionLine.class.getSimpleName());
        transactionLineStorage.add(transactionLine);
        return transactionLine;
    }

    public static Date extendExpireDate(TransactionLine transactionLine) {
        Date expireDate = transactionLine.getExpireDate();
        return Utils.extendExpireDate(expireDate);
    }
}