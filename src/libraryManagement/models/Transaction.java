package libraryManagement.models;

import libraryManagement.utils.Utils;

import java.util.Date;

public class Transaction implements Identifiable {
    private String id;
    private Date borrowDate;
    private int transactionLine = 0;
    private boolean returned = false;
    private String readerId;

    public Transaction(String id, Date borrowDate, String readerId) {
        this.id = id;
        this.borrowDate = borrowDate;
        this.readerId = readerId;
    }

    public Transaction(String id, Date borrowDate, int transactionLine, boolean returned, String readerId) {
        this.id = id;
        this.borrowDate = borrowDate;
        this.transactionLine = transactionLine;
        this.returned = returned;
        this.readerId = readerId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReaderId() {
        return readerId;
    }

    public void setReaderId(String readerId) {
        this.readerId = readerId;
    }

    public int getTransactionLine() {
        return transactionLine;
    }

    public void setTransactionLine(int transactionLine) {
        this.transactionLine = transactionLine;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public String toString() {
        String borrowedDateString = Utils.convertDateToString(borrowDate);
        return "Transaction: " + "\n" +
                "Borrowed date: " + borrowedDateString + "\n" +
                "Transaction line: " + transactionLine;
    }
}
