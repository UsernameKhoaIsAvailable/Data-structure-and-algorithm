package libraryManagement.views;

import libraryManagement.models.*;
import libraryManagement.storage.*;
import libraryManagement.utils.Utils;

import java.util.*;

public class Views {
    public static void views() {
        String[] choices = {"Manage titles & books", "Manage readers", "Borrow & Return", "Exit"};
        while (true) {
            int choice = Utils.showChoices(List.of(choices));
            if (choice == 1) {
                manageTitlesAndBooks();
            } else if (choice == 2) {
                manageReaders();
            } else if (choice == 3) {
                borrowAndReturn();
            } else {
                break;
            }
        }
    }


    public static void manageTitlesAndBooks() {
        String[] choices = {"Add title", "Add book", "Delete title", "Delete book", "Search title", "Search book", "Return"};
        while (true) {
            int choice = Utils.showChoices(List.of(choices));
            if (choice == 1) {
                BookViews.addTitle();
            } else if (choice == 2) {
                addBook();
            } else if (choice == 3) {
                deleteTitle();
            } else if (choice == 4) {
                deleteBook();
            } else if (choice == 5) {
                searchTitle();
            } else if (choice == 6) {
                searchBook();
            } else {
                break;
            }
        }
    }

    public static void addBook() {
        ArrayList<String> printString = Utils.getPrintStringTitle();
        int choice = Utils.showChoices(printString, "title");
        TitleDAO titleDAO = new TitleDAO();
        Title title = titleDAO.list().get(choice - 1);
        BookViews.addBook(title.getId());
    }

    public static void deleteTitle() {
        ArrayList<String> printString = Utils.getPrintStringTitle();
        if (printString.isEmpty()) {
            System.out.println("There are no titles!");
            return;
        }
        int choice = Utils.showChoices(printString, "title");
        TitleDAO titleDAO = new TitleDAO();
        Title title = titleDAO.list().get(choice - 1);
        BookViews.deleteTitle(title.getId());
    }

    public static void deleteBook() {
        ArrayList<String> printString = Utils.getPrintStringTitle();
        if (printString.isEmpty()) {
            System.out.println("There are no titles!");
            return;
        }
        int choice = Utils.showChoices(printString, "title");
        TitleDAO titleDAO = new TitleDAO();
        Title title = titleDAO.list().get(choice - 1);
        printString = Utils.getPrintStringBook(title.getId());
        if (printString.isEmpty()) {
            System.out.println("There are no books!");
            return;
        }
        choice = Utils.showChoices(printString, "book");
        BookDAO bookDAO = new BookDAO();
        Book book = bookDAO.search(title.getId()).get(choice - 1);
        BookViews.deleteBook(book.getId());
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

    public static void manageReaders() {
        String[] choices = {"Add reader", "Delete reader", "Search reader", "Change reader's borrowing permission", "Return"};
        while (true) {
            int choice = Utils.showChoices(List.of(choices));
            if (choice == 1) {
                ReaderViews.add();
            } else if (choice == 2) {
                deleteReader();
            } else if (choice == 3) {
                searchReader();
            } else if (choice == 4) {
                changeBookBorrowed();
            } else {
                break;
            }
        }
    }

    public static void deleteReader() {
        ArrayList<String> printString = Utils.getPrintStringReader();
        if (printString.isEmpty()) {
            System.out.println("There are no readers");
            return;
        }
        int choice = Utils.showChoices(printString, "reader");
        ReaderDAO readerDAO = new ReaderDAO();
        Reader reader = readerDAO.list().get(choice - 1);
        ReaderViews.delete(reader.getId());
    }

    public static void searchReader() {
        String keyword = Utils.stringScanner("Enter keyword: ");
        ReaderDAO readerDAO = new ReaderDAO();
        ArrayList<Reader> readers = readerDAO.search(keyword);
        if (readers.isEmpty()) {
            System.out.println("Reader does not exist");
            return;
        }
        for (Reader reader : readers) {
            System.out.println(reader);
            System.out.println();
        }
    }

    public static void changeBookBorrowed() {
        ArrayList<String> printString = Utils.getPrintStringReader();
        if (printString.isEmpty()) {
            System.out.println("There are no readers");
            return;
        }
        int choice = Utils.showChoices(printString, "reader");
        ReaderDAO readerDAO = new ReaderDAO();
        Reader reader = readerDAO.list().get(choice - 1);
        System.out.println("[1] Allow");
        System.out.println("[2] Not allow");
        choice = Utils.intScanner("Choose: ");
        reader.setCanBorrow(choice == 1);
        readerDAO.update(reader);
    }

    public static void borrowAndReturn() {
        String[] choices = {"Borrow book", "Return book", "Extend expire date", "Return"};
        while (true) {
            int choice = Utils.showChoices(choices);
            if (choice == 1) {
                borrowBook();
            } else if (choice == 2) {
                returnBook();
            } else if (choice == 3) {
                extendExpireDate();
            } else {
                break;
            }
        }
    }

    public static void borrowBook() {
        ArrayList<String> printString = Utils.getPrintStringReader();
        if (printString.isEmpty()) {
            System.out.println("There are no readers");
            return;
        }
        int choice = Utils.showChoices(printString, "reader");
        ReaderDAO readerDAO = new ReaderDAO();
        Reader reader = readerDAO.list().get(choice - 1);
        if (reader.isAllowedToBorrow()) {
            int bookBorrowed = Utils.intScanner("Number of books borrowed: ");
            if (reader.getBookBorrowed() + bookBorrowed > 10 || bookBorrowed > 10) {
                System.out.println("Exceed number of books borrowed");
                return;
            }
            printString = Utils.getPrintStringTitle();
            if (printString.isEmpty()) {
                System.out.println("There are no titles!");
                return;
            }
            Transaction transaction = TransactionViews.addTransaction(reader.getId());
            transaction.setTransactionLine(bookBorrowed);
            reader.setBookBorrowed(reader.getBookBorrowed() + bookBorrowed);
            ArrayList<String> printStringTransactionLine = new ArrayList<>();
            for (int i = 0; i < bookBorrowed; i++) {
                printString = Utils.getPrintStringTitle();
                choice = Utils.showChoices(printString, "title");
                TitleDAO titleDAO = new TitleDAO();
                Title title = titleDAO.list().get(choice - 1);
                printString = Utils.getPrintStringNotBorrowedBook(title.getId());
                if (printString.isEmpty()) {
                    reader.setBookBorrowed(reader.getBookBorrowed() - 1);
                    transaction.setTransactionLine(transaction.getTransactionLine() - 1);
                    System.out.println("Out of books");
                    continue;
                }
                choice = Utils.showChoices(printString, "book");
                BookDAO bookDAO = new BookDAO();
                Book book = bookDAO.list(title.getId()).get(choice - 1);
                book.setBorrowed(true);
                bookDAO.update(book);
                TransactionLine transactionLine = TransactionViews.addTransactionLine(transaction.getBorrowDate(), book.getId(), transaction.getId());
                printStringTransactionLine.add(transactionLine.toString());
            }
            readerDAO.update(reader);
            TransactionDAO transactionDAO = new TransactionDAO();
            transactionDAO.update(transaction);
            System.out.println(transaction);
            System.out.println("------------------------------------");
            for (int i = 0; i < printStringTransactionLine.size(); i++) {
                System.out.println(i + 1 + ". " + printStringTransactionLine.get(i));
                System.out.println();
            }
        } else {
            System.out.println("Reader is not allowed to borrow");
        }
    }

    public static void returnBook() {
        ArrayList<String> printString = Utils.getPrintStringReader();
        if (printString.isEmpty()) {
            System.out.println("There are no readers");
            return;
        }
        int choice = Utils.showChoices(printString, "reader");
        ReaderDAO readerDAO = new ReaderDAO();
        Reader reader = readerDAO.list().get(choice - 1);
        printString = Utils.getPrintStringTransaction(reader.getId());
        if (printString.isEmpty()) {
            System.out.println("Reader have returned all books!");
            return;
        }
        choice = Utils.showChoices(printString, "transaction");
        TransactionDAO transactionDAO = new TransactionDAO();
        Transaction transaction = transactionDAO.list(reader.getId()).get(choice - 1);
        printString = Utils.getPrintStringTransactionLine(transaction.getId());
        choice = Utils.showChoices(printString, "transaction line");
        TransactionLineDAO transactionLineDAO = new TransactionLineDAO();
        TransactionLine transactionLine = transactionLineDAO.list(transaction.getId()).get(choice - 1);
        BookDAO bookDAO = new BookDAO();
        Book book = bookDAO.get(transactionLine.getBookId());
        TitleDAO titleDAO = new TitleDAO();
        int price = titleDAO.get(book.getTitleId()).getPrice();
        Date returnDate = Utils.getDate();
        transactionLine.setReturnDate(returnDate);
        int days = Utils.subtractDate(transactionLine.getExpireDate(), returnDate);
        if (days > 0) {
            transactionLine.setFine(price * 0.1);
            System.out.println("Return late! Fine: " + transactionLine.getFine());
            String[] choices = {"Pay", "Return"};
            choice = Utils.showChoices(choices);
            if (choice == 2) {
                System.out.println("Return uncompleted");
                return;
            }
        }
        System.out.println("Return completed");
        transactionLine.setReturned(true);
        transactionLineDAO.update(transactionLine);
        transaction.setTransactionLine(transaction.getTransactionLine() - 1);
        if (transaction.getTransactionLine() == 0) {
            transaction.setReturned(true);
        }
        transactionDAO.update(transaction);
        book.setBorrowed(false);
        bookDAO.update(book);
        reader.setBookBorrowed(reader.getBookBorrowed() - 1);
        readerDAO.update(reader);
    }

    public static void extendExpireDate() {
        ArrayList<String> printString = Utils.getPrintStringReader();
        if (printString.isEmpty()) {
            System.out.println("There are no readers");
            return;
        }
        int choice = Utils.showChoices(printString, "reader");
        ReaderDAO readerDAO = new ReaderDAO();
        String readerId = readerDAO.list().get(choice - 1).getId();
        printString = Utils.getPrintStringTransaction(readerId);
        if (printString.isEmpty()) {
            System.out.println("Reader have returned all books!");
            return;
        }
        choice = Utils.showChoices(printString, "transaction");
        TransactionDAO transactionDAO = new TransactionDAO();
        String transactionId = transactionDAO.list(readerId).get(choice - 1).getId();
        printString = Utils.getPrintStringTransactionLine(transactionId);
        choice = Utils.showChoices(printString, "transaction line");
        TransactionLineDAO transactionLineDAO = new TransactionLineDAO();
        TransactionLine transactionLine = transactionLineDAO.list(transactionId).get(choice - 1);
        if (transactionLine.isExtended()) {
            System.out.println("Expire already extended");
            return;
        }
        transactionLine.setExtended(true);
        transactionLine.setExpireDate(Utils.extendExpireDate(transactionLine.getExpireDate()));
        transactionLineDAO.update(transactionLine);
        System.out.println("Expire date extended");
        System.out.println(transactionLine);
    }
}
