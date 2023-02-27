package libraryManagement.models;

import java.util.Date;

public class Book {
    private String bookId;
    private Date importDate;
    private String location;
    private boolean borrow;

    public Book(String bookId,Date importDate, String location, boolean borrow) {
        this.bookId = bookId;
        this.importDate = importDate;
        this.location = location;
        this.borrow = borrow;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isBorrow() {
        return borrow;
    }

    public void setBorrow(boolean borrow) {
        this.borrow = borrow;
    }
}
