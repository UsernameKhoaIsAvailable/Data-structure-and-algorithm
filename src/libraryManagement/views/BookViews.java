package libraryManagement.views;

import libraryManagement.models.Book;
import libraryManagement.models.Title;
import libraryManagement.storage.Storage;
import libraryManagement.utils.Utils;
import java.util.*;

public class BookViews {
    public static void addTitle() {
        Scanner scanner = new Scanner(System.in);
        String id = Utils.generateId(12);
        Storage<Title> titleStorage = new Storage<>(Title.class.getSimpleName());
        System.out.println("Enter book's name: ");
        String name = scanner.nextLine();
        System.out.println("Enter category: ");
        String category = scanner.nextLine();
        System.out.println("Enter author: ");
        String author = scanner.nextLine();
        Date date = Utils.getDate();
        System.out.println("Enter publisher: ");
        String publisher = scanner.nextLine();
        System.out.println("Enter language: ");
        String language = scanner.nextLine();
        int price;
        while (true) {
            try {
                System.out.println("Enter price: ");
                price = scanner.nextInt();
                if(price < 0) {
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wrong format!");
                scanner.next();
            }
        }
        Title title = new Title(id, name, category, author, date, publisher, language, price);
        titleStorage.add(title);
        System.out.println("Title added");
    }

    public static void addBook(String titleId) {
        Scanner scanner = new Scanner(System.in);
        Storage<Book> bookStorage = new Storage<>(Book.class.getSimpleName());
        String id = Utils.generateId(12);
        Date date = Utils.getDate();
        System.out.println("Enter location of book: ");
        String location = scanner.nextLine();
        boolean borrowed = false;
        Book book = new Book(id, date, location, borrowed, titleId);
        bookStorage.add(book);
        System.out.println("Book added");
    }

    public static void deleteBook(String id) {
        Storage<Book> bookStorage = new Storage<>(Book.class.getSimpleName());
        if(!bookStorage.exists(id)) {
            System.out.println("Book does not exist");
            return;
        }

        if(bookStorage.delete(id)) {
            System.out.println("Book deleted");
            return;
        }

        System.out.println("Error!");
    }

    public static void deleteTitle(String id) {
        Storage<Title> titleStorage = new Storage<>(Title.class.getSimpleName());
        Storage<Book> bookStorage = new Storage<>(Book.class.getSimpleName());

        if(!titleStorage.exists(id)) {
            System.out.println("Title does not exist");
            return;
        }

        titleStorage.delete(id);
        ArrayList<Book> list = bookStorage.search(id);
        for (Book book : list) {
            bookStorage.delete(book.getId());
        }
    }
}