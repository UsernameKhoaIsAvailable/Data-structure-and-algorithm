package libraryManagement.views;

import libraryManagement.models.Reader;
import libraryManagement.storage.ReaderDAO;
import libraryManagement.utils.Utils;

import java.util.Date;


public class ReaderViews {
    public static void add() {
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

    public static void delete(String id) {
        ReaderDAO readerDAO = new ReaderDAO();
        if (readerDAO.exists(id)) {
            readerDAO.delete(id);
            System.out.println("Reader deleted");
            return;
        }
        System.out.println("Reader does not exist");
    }
}
