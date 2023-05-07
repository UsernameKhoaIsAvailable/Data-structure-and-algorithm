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

    public static void addBook(String titleId) {
        BookDAO bookDAO = new BookDAO();
        String id = Utils.generateId(17);
        Date importDate = Utils.getDate();
        String location = Utils.stringScanner("Enter location of book: ");
        Book book = new Book(id, importDate, location, titleId);
        bookDAO.add(book);
        System.out.println("Book added!");
    }

    public static void deleteBook(String id) {
        BookDAO bookDAO = new BookDAO();
        if (bookDAO.exists(id)) {
            bookDAO.delete(id);
            System.out.println("Book deleted!");
            return;
        }
        System.out.println("Book does not exist!");

    }

    public static void deleteTitle(String titleId) {
        TitleDAO titleDAO = new TitleDAO();
        BookDAO bookDAO = new BookDAO();
        if (!titleDAO.exists(titleId)) {
            System.out.println("Title does not exist!");
            return;
        }
        titleDAO.delete(titleId);
        ArrayList<Book> arrayList = bookDAO.search(titleId);
        for (Book book : arrayList) {
            bookDAO.delete(book.getId());
        }
        System.out.println("Title deleted!");
    }
}