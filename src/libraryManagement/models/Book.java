package libraryManagement.models;

import libraryManagement.utils.Utils;

import java.util.Date;

public class Book implements Identifiable {
    private String id;
    private Date importDate;
    private String location;
    private boolean borrowed;
    private String name;

    public Book(String id,Date importDate, String location, boolean borrow, String name) {
        this.id = id;
        this.importDate = importDate;
        this.location = location;
        this.borrowed = borrow;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImportDate() {
        String importDateString = Utils.convertDateToString(importDate);
        return importDateString;
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

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public String getSearchableValue() {
        return id + name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String importDateString = Utils.convertDateToString(importDate);
        return "Id: " + id + '\n' +
                "Import date: " + importDateString + "\n" +
                "Location: " + location + '\n';
    }
}
