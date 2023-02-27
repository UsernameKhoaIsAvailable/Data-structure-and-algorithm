package libraryManagement.models;

import java.util.Date;

public class Title {
    private String id;
    private String name;
    private String bookshelf;
    private String category;
    private String author;
    private Date releaseDate;
    private String publisher;
    private String language;
    private int numberOfBooks;

    public Title(String id, String name, String bookshelf, String category, String author, Date releaseDate, String publisher, String language, int numberOfBooks) {
        this.id = id;
        this.name = name;
        this.bookshelf = bookshelf;
        this.category = category;
        this.author = author;
        this.releaseDate = releaseDate;
        this.publisher = publisher;
        this.language = language;
        this.numberOfBooks = numberOfBooks;
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

    public String getBookshelf() {
        return bookshelf;
    }

    public void setBookshelf(String bookshelf) {
        this.bookshelf = bookshelf;
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

    public int getNumberOfBooks() {
        return numberOfBooks;
    }

    public void setNumberOfBooks(int numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }
}
