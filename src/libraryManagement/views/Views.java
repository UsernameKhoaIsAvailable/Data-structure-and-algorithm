package libraryManagement.views;

import libraryManagement.models.*;
import libraryManagement.storage.Storage;
import libraryManagement.utils.Utils;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Views {
    public static void views() {
        String[] choices = {"Manage books", "Manage readers", "Borrow & Return", "Exit"};
        while (true) {
            int choice = showChoices(List.of(choices));
            if (choice == 1) {
                manageBooks();
            } else if (choice == 2) {
                manageReaders();
            } else if (choice == 3) {
                borrowAndReturn();
            } else {
                break;
            }
        }
    }

    public static int showChoices(List<String> choices) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        ArrayList<Integer> allChoices = new ArrayList<>();
        do {
            System.out.println("-------- LIBRARY MANAGEMENT --------");
            for (int i = 0; i < choices.size(); i++) {
                allChoices.add(i+1);
                System.out.printf("[%d] %s\n", i + 1, choices.get(i));
            }
            System.out.println("------------------------------------");
            System.out.print("Choose: ");
            choice = scanner.nextInt();
        } while (!allChoices.contains(choice));
        return choice;
    }

    public static int showChoices(String[] choices) {
        return showChoices(List.of(choices));
    }

    public static void manageBooks() {
        String[] choices = {"Add book & title", "Delete book & title", "Search book"};
        int choice = showChoices(List.of(choices));
        if (choice == 1) {
            addBookAndTitle();
        } else if (choice == 2) {
            deleteBookAndTitle();
        } else {
            searchBook();
        }
    }

    public static void addBookAndTitle() {
        String[] choices = {"Add title", "Add book"};
        int choice = showChoices(List.of(choices));
        if (choice == 1) {
            BookViews.addTitle();
        } else {
            BookViews.addBook();
        }
    }

    public static void deleteBookAndTitle() {
        Scanner scanner = new Scanner(System.in);
        String[] choices = {"Delete title", "Delete book"};
        int choice = showChoices(List.of(choices));
        System.out.println("Enter title name: ");
        String keyword = scanner.nextLine();
        Storage<Title> titleStorage = new Storage<>(Title.class.getSimpleName());
        ArrayList<Title> titleArrayList = titleStorage.search(keyword);
        ArrayList<String> titleName = new ArrayList<>();
        for(Title tile : titleArrayList) {
            titleName.add(tile.getName());
        }
        int index = showChoices(titleName);
        if (choice == 1) {
            BookViews.deleteTitle(titleArrayList.get(index - 1).getId());
        } else {
            keyword = titleArrayList.get(index - 1).getId();
            Storage<Book> bookStorage = new Storage<>(Book.class.getSimpleName());
            ArrayList<Book> bookArrayList = bookStorage.search(keyword);
            ArrayList<String> bookId = new ArrayList<>();
            for(Book book : bookArrayList) {
                bookId.add(book.getId());
            }
            index = showChoices(bookId);
            BookViews.deleteBook(bookId.get(index-1));
        }
    }

    public static void searchBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter keyword: ");
        String keyword = scanner.nextLine();
        Storage<Title> titleStorage = new Storage<>(Title.class.getSimpleName());
        ArrayList<Title> titleArrayList = titleStorage.search(keyword);
        if (titleArrayList.isEmpty()) {
            System.out.println("Book does not exist");
            return;
        }
        for (Title title : titleArrayList) {
            System.out.println(title.getName() + " " + title.getId());
        }
    }

    public static void manageReaders() {
        String[] choices = {"Add reader", "Delete reader", "Search reader"};
        int choice = showChoices(List.of(choices));
        if (choice == 1) {
            ReaderViews.addReader();
        } else if (choice == 2) {
            deleteReader();
        } else {
            searchReader();
        }
    }

    public static void deleteReader() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter reader name or id: ");
        String keyword = scanner.nextLine();
        Storage<Reader> readerStorage = new Storage<>(Reader.class.getSimpleName());
        ArrayList<Reader> readerArrayList = readerStorage.search(keyword);
        ArrayList<String> readerId = new ArrayList<>();
        for (Reader reader : readerArrayList) {
            readerId.add(reader.getId());
        }
        int index = showChoices(readerId);
        readerStorage.delete(readerId.get(index-1));
    }

    public static void searchReader() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter keyword: ");
        String keyword = scanner.nextLine();
        Storage<Reader> readerStorage = new Storage<>(Reader.class.getSimpleName());
        ArrayList<Reader> readerArrayList = readerStorage.search(keyword);
        if (readerArrayList.isEmpty()) {
            System.out.println("Reader does not exist");
            return;
        }
        for (Reader reader : readerArrayList) {
            System.out.println(reader.getName());
        }
    }

    public static void borrowAndReturn() {
        String[] choices = {"Borrow", "Return", "Extend expire date"};
        int choice = showChoices(choices);
        if (choice == 1) {
            borrow();
        } else if (choice == 2) {
            _return();
        } else {
            extendExpireDate();
        }
    }

    public static void borrow() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter reader name or id: ");
        String keyword = scanner.nextLine();
        Storage<Reader> readerStorage = new Storage<>(Reader.class.getSimpleName());
        ArrayList<Reader> readerArrayList = readerStorage.search(keyword);
        ArrayList<String> readerId = new ArrayList<>();
        for(Reader reader : readerArrayList) {
            readerId.add(reader.getId());
        }
        int choice = showChoices(readerId);
        Reader reader = readerArrayList.get(choice-1);
        if(reader.isAllowedToBorrow()) {
            System.out.println("Number of books borrowed: ");
            int bookBorrowed = scanner.nextInt();
            if(reader.getBookBorrowed() + bookBorrowed > 10) {
                System.out.println("Exceed number of books borrowed");
                return;
            }
            Transaction transaction = TransactionViews.addTransaction();
            reader.setBookBorrowed(reader.getBookBorrowed() + bookBorrowed);
            reader.setBookBorrowed(bookBorrowed);
            for(int i = 0; i < bookBorrowed; i++) {
                System.out.println("Enter book name or id: ");
                keyword = scanner.nextLine();
                Storage<Title> titleStorage = new Storage<>(Title.class.getSimpleName());
                ArrayList<Title> titleArrayList = titleStorage.search(keyword);
                ArrayList<String> titleName = new ArrayList<>();
                for(Title title : titleArrayList) {
                    titleName.add(title.getName());
                }
                choice = showChoices(titleName);
                int price = titleArrayList.get(choice-1).getPrice();
                keyword = titleArrayList.get(choice-1).getId();
                Storage<Book> bookStorage = new Storage<>(Book.class.getSimpleName());
                ArrayList<Book> bookArrayList = bookStorage.search(keyword);
                ArrayList<String> bookId = new ArrayList<>();
                for(Book book : bookArrayList) {
                    if(!book.isBorrowed()) {
                        bookId.add(book.getId());
                    }
                }
                if(bookId.isEmpty()) {
                    reader.setBookBorrowed(reader.getBookBorrowed() - 1);
                    System.out.println("Out of books");
                    continue;
                }
                choice = showChoices(bookId);
                Book book = bookArrayList.get(choice-1);
                book.setBorrowed(true);
                TransactionLine transactionLine = TransactionViews.addTransactionLine(transaction.getBorrowDate(), book, price);
                transaction.addTransactionLine(transactionLine.getId());
            }
            if(!transaction.isTransactionLinesEmpty()) {
                reader.addTransaction(transaction.getId());
            }
            readerStorage.add(reader);
            Storage<Transaction> transactionStorage = new Storage<>(Transaction.class.getSimpleName());
            transactionStorage.add(transaction);
        }
        else {
            System.out.println("Reader is not allowed to borrow");
        }
    }

    public static void _return() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter reader name or id: ");
        String keyword = scanner.nextLine();
        Storage<Reader> readerStorage = new Storage<>(Reader.class.getSimpleName());
        ArrayList<Reader> readerArrayList = readerStorage.search(keyword);
        ArrayList<String> readerId = new ArrayList<>();
        for(Reader reader : readerArrayList) {
            readerId.add(reader.getId());
        }
        int choice = showChoices(readerId);
        Reader reader = readerArrayList.get(choice-1);
        choice = showChoices(reader.getTransaction());
        keyword = reader.getTransaction().get(choice-1);
        reader.getTransaction().remove(choice-1);
        Storage<Transaction> transactionStorage = new Storage<>(Transaction.class.getSimpleName());
        Transaction transaction = transactionStorage.search(keyword).get(0);
        choice = showChoices(transaction.getTransactionLines());
        Storage<TransactionLine> transactionLineStorage = new Storage<>(TransactionLine.class.getSimpleName());
        keyword = transaction.getTransactionLines().get(choice-1);
        transaction.getTransactionLines().remove(choice-1);
        TransactionLine transactionLine = transactionLineStorage.search(keyword).get(0);
        LocalDateTime localDateTime = LocalDateTime.now();
        Date returnDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        transactionLine.setReturnDate(returnDate);
        int days = Utils.subtractDate(transactionLine.getExpireDate(), returnDate);
        if(days <= 0) {
            transactionLine.setFine(0);
        }
        else {
            System.out.printf("Return book late. Fine %f", transactionLine.getFine());
        }
        readerStorage.add(reader);
        transactionStorage.add(transaction);
    }

    public static void extendExpireDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter reader name or id: ");
        String keyword = scanner.nextLine();
        Storage<Reader> readerStorage = new Storage<>(Reader.class.getSimpleName());
        ArrayList<Reader> readerArrayList = readerStorage.search(keyword);
        ArrayList<String> readerId = new ArrayList<>();
        for(Reader reader : readerArrayList) {
            readerId.add(reader.getId());
        }
        int choice = showChoices(readerId);
        Reader reader = readerArrayList.get(choice-1);
        choice = showChoices(reader.getTransaction());
        keyword = reader.getTransaction().get(choice-1);
        Storage<Transaction> transactionStorage = new Storage<>(Transaction.class.getSimpleName());
        Transaction transaction = transactionStorage.search(keyword).get(0);
        choice = showChoices(transaction.getTransactionLines());
        Storage<TransactionLine> transactionLineStorage = new Storage<>(TransactionLine.class.getSimpleName());
        keyword = transaction.getTransactionLines().get(choice-1);
        TransactionLine transactionLine = transactionLineStorage.search(keyword).get(0);
        if(transactionLine.isExtended()) {
            System.out.println("Expire already extended");
        }
        else {
            transactionLine.setExtended(true);
            transactionLine.setExpireDate(TransactionViews.extendExpireDate(transactionLine));
        }
    }
}
