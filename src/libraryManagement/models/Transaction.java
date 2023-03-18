package libraryManagement.models;

import java.util.ArrayList;
import java.util.Date;

public class Transaction implements Identifiable{
    private String id;
    private Date borrowDate;
    private ArrayList<String> transactionLines = new ArrayList<>();

    public Transaction(Date borrowDate, String id) {
        this.borrowDate = borrowDate;
        this.id = id;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public boolean isTransactionLinesEmpty() {
        if(transactionLines.isEmpty()) {
            return true;
        }
        return false;
    }
    public void addTransactionLine(String id) {
        transactionLines.add(id);
    }

    public ArrayList<String> getTransactionLines() {
        return transactionLines;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSearchableValue() {
        return id;
    }
}
