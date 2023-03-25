package libraryManagement.views;

import libraryManagement.models.Reader;
import libraryManagement.storage.Storage;
import libraryManagement.utils.Utils;
import java.util.Date;
import java.util.Scanner;

public class ReaderViews {
    public static void addReader() {
        Scanner scanner = new Scanner(System.in);
        Storage<Reader> readerStorage = new Storage<>(Reader.class.getSimpleName());
        String id = Utils.generateId(16);
        Date date = Utils.getDate();
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        String dateOfBirthString;
        Date dateOfBirth;
        do {
            System.out.println("Enter date of birth(dd/mm/yyyy): ");
            dateOfBirthString = scanner.nextLine();
            dateOfBirth = Utils.convertStringToDate(dateOfBirthString);
        }while (dateOfBirth == null);
        System.out.println("Enter gender: ");
        String gender = scanner.nextLine();
        System.out.println("Enter id card: ");
        String idCard = scanner.nextLine();
        System.out.println("Enter address: ");
        String address = scanner.nextLine();
        Reader reader = new Reader(id, date, name, dateOfBirth, gender, idCard, address);
        readerStorage.add(reader);
        System.out.println("Reader added");
    }

    public static void deleteReader(String id) {
        Storage<Reader> readerStorage = new Storage<>(Reader.class.getSimpleName());
        if(!readerStorage.exists(id)) {
            System.out.println("Reader does not exist");
            return;
        }

        readerStorage.delete(id);
        System.out.println("Reader deleted");
    }
}
