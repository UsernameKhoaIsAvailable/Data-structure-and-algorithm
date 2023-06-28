package libraryManagement.utils;

import libraryManagement.models.*;
import libraryManagement.storage.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Utils {
    public static String generateId(int length) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder id = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            id.append(AlphaNumericString.charAt(index));
        }
        return id.toString();
    }

    public static Date convertStringToDate(String dateString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Wrong date format!");
        }
        return null;
    }

    public static String convertDateToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    public static Date calculateExpireDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 30);
        return c.getTime();
    }

    public static Date extendExpireDate(Date expireDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(expireDate);
        c.add(Calendar.DATE, 14);
        return c.getTime();
    }

    public static int subtractDate(Date expireDate, Date returnDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long diff = returnDate.getTime() - expireDate.getTime();
        TimeUnit timeUnit = TimeUnit.DAYS;
        long different = timeUnit.convert(diff, TimeUnit.MILLISECONDS);
        return (int) different;
    }

    public static Date getDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static java.sql.Date convertDateToSQLDate(Date date) {
        return new java.sql.Date(date.getTime());
    }

    public static String stringScanner(String request) {
        Scanner scanner = new Scanner(System.in);
        String str;
        do {
            System.out.println(request);
            str = scanner.nextLine();
        }while (str.isBlank());
        return str;
    }

    public static Date dateScanner(String request) {
        Date dateOfBirth;
        do {
            dateOfBirth = Utils.convertStringToDate(stringScanner(request));
        }while (dateOfBirth == null);
        return dateOfBirth;
    }

    public static int intScanner(String request) {
        Scanner scanner = new Scanner(System.in);
        int i;
        while (true) {
            try {
                System.out.println(request);
                i = scanner.nextInt();
                if (i < 0) {
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wrong format!");
                scanner.next();
            }
        }
        return i;
    }

    public static int showChoices(List<String> choices, String guide) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        ArrayList<Integer> allChoices = new ArrayList<>();
        do {
            System.out.println("-------- LIBRARY MANAGEMENT --------");
            for (int i = 0; i < choices.size(); i++) {
                allChoices.add(i + 1);
                System.out.printf("[%d] %s\n", i + 1, choices.get(i));
            }
            System.out.println("------------------------------------");
            if (guide != null) {
                System.out.println("Choose a " + guide);
            }
            System.out.print("Choose: ");
            try {
                choice = scanner.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("Wrong format!");
                scanner.next();
            }
        } while (!allChoices.contains(choice));
        return choice;
    }

    public static int showChoices(String[] choices) {
        return showChoices(List.of(choices), null);
    }

    public static int showChoices(List<String> choices) {
        return showChoices(choices, null);
    }

    public static Title getChosenTitle() {
        TitleDAO titleDAO = new TitleDAO();
        ArrayList<Title> titles = titleDAO.list();
        ArrayList<String> printString = new ArrayList<>();

        for (Title title : titles) {
            printString.add(title.toString());
        }

        if (printString.isEmpty()) {
            System.out.println("There are no titles!");
            return null;
        }

        int choice = showChoices(printString, "title");
        return titleDAO.list().get(choice - 1);
    }

    public static Book getChosenBook(String titleId) {
        BookDAO bookDAO = new BookDAO();
        ArrayList<Book> books = bookDAO.search(titleId);
        ArrayList<String> printString = new ArrayList<>();

        for (Book book : books) {
            printString.add(book.toString());
        }

        if (printString.isEmpty()) {
            System.out.println("There are no books!");
            return null;
        }

        int choice = showChoices(printString, "book");
        return bookDAO.search(titleId).get(choice - 1);
    }

    public static Book getChosenNotBorrowedBook(String titleId) {
        BookDAO bookDAO = new BookDAO();
        ArrayList<Book> books = bookDAO.list(titleId);
        ArrayList<String> printString = new ArrayList<>();

        for (Book book : books) {
            printString.add(book.toString());
        }

        if (printString.isEmpty()) {
            System.out.println("Out of books");
            return null;
        }

        int choice = showChoices(printString, "book");
        return bookDAO.list(titleId).get(choice - 1);
    }

    public static Reader getChosenReader() {
        ReaderDAO readerDAO = new ReaderDAO();
        ArrayList<Reader> readers = readerDAO.list();
        ArrayList<String> printString = new ArrayList<>();

        for (Reader reader : readers) {
            printString.add(reader.toString());
        }

        if (printString.isEmpty()) {
            System.out.println("There are no readers");
            return null;
        }

        int choice = showChoices(printString, "reader");
        return readerDAO.list().get(choice - 1);
    }

    public static Transaction getChosenTransaction(String readerId) {
        TransactionDAO transactionDAO = new TransactionDAO();
        ArrayList<Transaction> transactions = transactionDAO.list(readerId);
        ArrayList<String> printString = new ArrayList<>();

        for (Transaction transaction : transactions) {
            printString.add(transaction.toString());
        }

        if (printString.isEmpty()) {
            System.out.println("Reader have returned all books!");
            return null;
        }

        int choice = showChoices(printString, "transaction");
        return transactionDAO.list(readerId).get(choice - 1);
    }

    public static TransactionLine getChosenTransactionLine(String transactionId) {
        TransactionLineDAO transactionLineDAO = new TransactionLineDAO();
        BookDAO bookDAO = new BookDAO();
        TitleDAO titleDAO = new TitleDAO();
        ArrayList<TransactionLine> transactionLines = transactionLineDAO.list(transactionId);
        ArrayList<String> printString = new ArrayList<>();

        for (TransactionLine transactionLine : transactionLines) {
            Book book = bookDAO.get(transactionLine.getBookId());
            Title title = titleDAO.get(book.getTitleId());
            printString.add(transactionLine + "\nLocation: " + book.getLocation() + "\nTitle: " + title.getName() + "\n");
        }

        int choice = showChoices(printString, "transaction line");
        return transactionLineDAO.list(transactionId).get(choice - 1);
    }
}
