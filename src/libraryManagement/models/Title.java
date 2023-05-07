package libraryManagement.models;

import libraryManagement.utils.Utils;

import java.util.Date;

public class Title implements Identifiable {
    private String id;
    private String name;
    private String category;
    private String author;
    private Date releaseDate;
    private String publisher;
    private String language;
    private int price;

    public Title(String id, String name, String category, String author, Date releaseDate, String publisher, String language, int price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.author = author;
        this.releaseDate = releaseDate;
        this.publisher = publisher;
        this.language = language;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSearchableValue() {
        return id + name;
    }

    @Override
    public String toString() {
        String dateString = Utils.convertDateToString(releaseDate);
        return "Name: " + name + '\n' +
                "Id: " + id + '\n' +
                "Category: " + category + '\n' +
                "Author: " + author + '\n' +
                "Release date: " + dateString + '\n' +
                "Publisher: " + publisher + '\n' +
                "Language: " + language + "\n";
    }
}
