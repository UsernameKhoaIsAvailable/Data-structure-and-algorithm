package libraryManagement.views;

import libraryManagement.models.Book;
import libraryManagement.models.Title;
import libraryManagement.storage.BookDAO;
import libraryManagement.storage.TitleDAO;
import libraryManagement.utils.Utils;

import java.util.*;

public class BookViews {
    public static void addTitle() {
        TitleDAO titleDAO = new TitleDAO();
        String id = Utils.generateId(16);
        String name = Utils.stringScanner("Enter title's name: ");
        String category = Utils.stringScanner("Enter category: ");
        String author = Utils.stringScanner("Enter author: ");
        Date releaseDate = Utils.dateScanner("Enter release date(yyyy-MM-dd): ");
        String publisher = Utils.stringScanner("Enter publisher: ");
        String language = Utils.stringScanner("Enter language: ");
        int price = Utils.intScanner("Enter price: ");
        Title title = new Title(id, name, category, author, releaseDate, publisher, language, price);
        titleDAO.add(title);
        System.out.println("Title added!");
    }

    public static void addBook() {
        Title title = Utils.getChosenTitle();
        if (title == null) {
            return;
        }

        BookDAO bookDAO = new BookDAO();
        String id = Utils.generateId(17);
        Date importDate = Utils.getDate();
        String location = Utils.stringScanner("Enter location of book: ");
        Book book = new Book(id, importDate, location, title.getId());
        bookDAO.add(book);
        System.out.println("Book added!");
    }

    public static void deleteBook() {
        Title title = Utils.getChosenTitle();
        if (title == null) {
            return;
        }

        BookDAO bookDAO = new BookDAO();
        Book book = Utils.getChosenBook(title.getId());
        if (book == null) {
            return;
        }
        bookDAO.delete(book.getId());
        System.out.println("Book deleted!");
    }

    public static void deleteTitle() {
        Title title = Utils.getChosenTitle();
        TitleDAO titleDAO = new TitleDAO();
        if (title == null) {
            return;
        }
        titleDAO.delete(title.getId());

        BookDAO bookDAO = new BookDAO();
        ArrayList<Book> arrayList = bookDAO.search(title.getId());
        for (Book book : arrayList) {
            bookDAO.delete(book.getId());
        }
        System.out.println("Title and related books deleted!");
    }

    public static void searchTitle() {
        String keyword = Utils.stringScanner("Enter keyword");
        TitleDAO titleDAO = new TitleDAO();
        ArrayList<Title> titles = titleDAO.search(keyword);
        if (titles.isEmpty()) {
            System.out.println("Title does not exist");
            return;
        }
        for (Title title : titles) {
            System.out.println(title);
            System.out.println();
        }
    }

    public static void searchBook() {
        String keyword = Utils.stringScanner("Enter keyword");
        BookDAO bookDAO = new BookDAO();
        ArrayList<Book> books = bookDAO.search(keyword);
        if (books.isEmpty()) {
            System.out.println("Book does not exist");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
            System.out.println();
        }
    }
}