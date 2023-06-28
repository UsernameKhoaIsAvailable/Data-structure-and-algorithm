package libraryManagement.views;

import libraryManagement.models.Reader;
import libraryManagement.storage.ReaderDAO;
import libraryManagement.utils.Utils;

import java.util.ArrayList;
import java.util.Date;


public class ReaderViews {
    public static void addReader() {
        ReaderDAO readerDAO = new ReaderDAO();
        String id = Utils.generateId(15);
        Date date = Utils.getDate();
        String name = Utils.stringScanner("Enter name: ");
        Date dateOfBirth = Utils.dateScanner("Enter date of birth(yyyy-MM-dd): ");
        System.out.println("[1] Male");
        System.out.println("[2] Female");
        int choice = Utils.intScanner("Choose gender: ");
        boolean gender = choice == 1;
        String idCard = Utils.stringScanner("Enter id card: ");
        String address = Utils.stringScanner("Enter address: ");
        Reader reader = new Reader(id, date, name, dateOfBirth, gender, idCard, address);
        readerDAO.add(reader);
        System.out.println("Reader added");
    }

    public static void deleteReader() {
        ReaderDAO readerDAO = new ReaderDAO();
        Reader reader = Utils.getChosenReader();

        if (reader == null) {
            return;
        }

        readerDAO.delete(reader.getId());
        System.out.println("Reader deleted");
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
        Reader reader = Utils.getChosenReader();
        ReaderDAO readerDAO = new ReaderDAO();

        if (reader == null) {
            return;
        }

        System.out.println("[1] Allow");
        System.out.println("[2] Not allow");
        int choice = Utils.intScanner("Choose: ");
        reader.setCanBorrow(choice == 1);
        readerDAO.update(reader);
    }
}
