package libraryManagement.models;

import java.util.Date;

public class TransactionLine implements Identifiable {
    private String id;
    private Date expireDate;
    private String bookId;
    private boolean extended = false;
    private Date returnDate;
    private double fine;

    public TransactionLine(String id, Date expireDate, String bookId) {
        this.id = id;
        this.expireDate = expireDate;
        this.bookId = bookId;
    }

    public String getId() {
        return id;
    }

    @Override
    public String getSearchableValue() {
        return null;
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

    @Override
    public String toString() {
        return "TransactionLine{" +
                "id='" + id + '\'' +
                ", expireDate=" + expireDate +
                ", bookId='" + bookId + '\'' +
                ", extended=" + extended +
                ", returnDate=" + returnDate +
                ", fine=" + fine +
                '}';
    }
}
