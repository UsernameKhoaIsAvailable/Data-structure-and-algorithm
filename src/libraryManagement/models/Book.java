package libraryManagement.models;

import libraryManagement.utils.Utils;

import java.util.Date;

public class Book implements Identifiable {
    private String id;
    private Date importDate;
    private String location;
    private boolean borrowed = false;
    private String titleId;

    public Book(String id, Date importDate, String location, String titleId) {
        this.id = id;
        this.importDate = importDate;
        this.location = location;
        this.titleId = titleId;
    }

    public Book(String id, Date importDate, String location, boolean borrowed, String titleId) {
        this.id = id;
        this.importDate = importDate;
        this.location = location;
        this.borrowed = borrowed;
        this.titleId = titleId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    @Override
    public String toString() {
        String importDateString = Utils.convertDateToString(importDate);
        return "Import date: " + importDateString + "\n" +
                "Location: " + location + "\n";
    }
}
