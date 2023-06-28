package libraryManagement.models;

import libraryManagement.utils.Utils;

import java.util.Date;

public class TransactionLine implements Identifiable {
    private String id;
    private Date expireDate;
    private boolean extended = false;
    private Date returnDate;
    private double fine = 0;
    private boolean returned = false;
    private String bookId;
    private String transactionId;

    public TransactionLine(String id, Date expireDate, String bookId, String transactionId) {
        this.id = id;
        this.expireDate = expireDate;
        this.bookId = bookId;
        this.transactionId = transactionId;
    }

    public TransactionLine(String id, Date expireDate, boolean extended, Date returnDate, double fine, boolean returned, String bookId, String transactionId) {
        this.id = id;
        this.expireDate = expireDate;
        this.extended = extended;
        this.returnDate = returnDate;
        this.fine = fine;
        this.returned = returned;
        this.bookId = bookId;
        this.transactionId = transactionId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public boolean isExtended() {
        return extended;
    }

    public void setExtended(boolean extended) {
        this.extended = extended;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String toString() {
        String expireDateString = Utils.convertDateToString(expireDate);
        return "Expire date: " + expireDateString + "\n" +
                "Book id: " + bookId;
    }
}
