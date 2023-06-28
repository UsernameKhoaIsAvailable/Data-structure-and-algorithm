package libraryManagement.views;

import libraryManagement.models.*;
import libraryManagement.storage.*;
import libraryManagement.utils.Utils;

import java.util.ArrayList;
import java.util.Date;

public class BorrowAndReturnViews {
    public static void borrowBook() {
        Reader reader = Utils.getChosenReader();
        if (reader == null) {
            return;
        }

        if (reader.isAllowedToBorrow()) {
            int bookBorrowed = Utils.intScanner("Number of books borrowed: ");
            if (reader.getBookBorrowed() + bookBorrowed > 10 || bookBorrowed > 10) {
                System.out.println("Exceed number of books borrowed");
                return;
            }

            Transaction transaction = TransactionViews.addTransaction(reader.getId());
            transaction.setTransactionLine(bookBorrowed);

            reader.setBookBorrowed(reader.getBookBorrowed() + bookBorrowed);

            ArrayList<String> printStringTransactionLine = new ArrayList<>();

            for (int i = 0; i < bookBorrowed; i++) {
                Title title = Utils.getChosenTitle();
                if (title == null) {
                    return;
                }

                Book book = Utils.getChosenNotBorrowedBook(title.getId());
                if (book == null) {
                    reader.setBookBorrowed(reader.getBookBorrowed() - 1);
                    transaction.setTransactionLine(transaction.getTransactionLine() - 1);
                    continue;
                }

                book.setBorrowed(true);
                BookDAO bookDAO = new BookDAO();
                bookDAO.update(book);

                TransactionLine transactionLine = TransactionViews.addTransactionLine(transaction.getBorrowDate(), book.getId(), transaction.getId());
                printStringTransactionLine.add(transactionLine.toString());
            }

            ReaderDAO readerDAO = new ReaderDAO();
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
        TransactionLine transactionLine = getTransactionLine();
        if (transactionLine == null) {
            return;
        }

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
            int choice = Utils.showChoices(choices);

            if (choice == 2) {
                System.out.println("Return uncompleted");
                return;
            }
        }
        System.out.println("Return completed");

        transactionLine.setReturned(true);
        TransactionLineDAO transactionLineDAO = new TransactionLineDAO();
        transactionLineDAO.update(transactionLine);

        TransactionDAO transactionDAO = new TransactionDAO();
        Transaction transaction = transactionDAO.get(transactionLine.getTransactionId());
        transaction.setTransactionLine(transaction.getTransactionLine() - 1);
        if (transaction.getTransactionLine() == 0) {
            transaction.setReturned(true);
        }

        transactionDAO.update(transaction);

        book.setBorrowed(false);
        bookDAO.update(book);

        ReaderDAO readerDAO = new ReaderDAO();
        Reader reader = readerDAO.get(transaction.getReaderId());
        reader.setBookBorrowed(reader.getBookBorrowed() - 1);
        readerDAO.update(reader);
    }

    public static void extendExpireDate() {
        TransactionLine transactionLine = getTransactionLine();
        if (transactionLine == null) {
            return;
        }

        if (transactionLine.isExtended()) {
            System.out.println("Expire already extended");
            return;
        }
        transactionLine.setExtended(true);
        transactionLine.setExpireDate(Utils.extendExpireDate(transactionLine.getExpireDate()));

        TransactionLineDAO transactionLineDAO = new TransactionLineDAO();
        transactionLineDAO.update(transactionLine);

        System.out.println("Expire date extended");
        System.out.println(transactionLine);
    }

    private static TransactionLine getTransactionLine() {
        Reader reader = Utils.getChosenReader();
        if (reader == null) {
            return null;
        }

        Transaction transaction = Utils.getChosenTransaction(reader.getId());
        if (transaction == null) {
            return null;
        }

        return Utils.getChosenTransactionLine(transaction.getId());
    }
}
