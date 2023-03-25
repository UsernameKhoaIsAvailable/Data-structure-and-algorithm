package libraryManagement.models;

import libraryManagement.utils.Utils;

import java.util.ArrayList;
import java.util.Date;

public class Reader implements Identifiable {
    private String id;
    private Date date;
    private String name;
    private Date dateOfBirth;
    private String gender;
    private String idCard;
    private String address;
    private boolean canBorrow = true;
    private int bookBorrowed = 0;
    private ArrayList<String> transaction = new ArrayList<>();

    public Reader(String id, Date date, String name, Date dateOfBirth, String gender, String idCard, String address) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.idCard = idCard;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        String dateOfBirthString = Utils.convertDateToString(dateOfBirth);
        return dateOfBirthString;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isAllowedToBorrow() {
        if(bookBorrowed >= 10) {
            canBorrow = false;
        }
        else {
            canBorrow = true;
        }
        return canBorrow;
    }

    public int getBookBorrowed() {
        return bookBorrowed;
    }

    public void setBookBorrowed(int bookBorrowed) {
        this.bookBorrowed = bookBorrowed;
    }

    public void addTransaction(String id) {
        transaction.add(id);
    }

    public ArrayList<String> getTransaction() {
        return transaction;
    }

    public String getSearchableValue() {
        return id + name;
    }

    @Override
    public String toString() {
        String dateString = Utils.convertDateToString(date);
        String dateOfBirthString = Utils.convertDateToString(dateOfBirth);
        return "Name: " + name + '\n' +
                "Id: " + id + '\n' +
                "Date: " + dateString + '\n' +
                "Date of birth: " + dateOfBirthString + '\n' +
                "Gender: '" + gender + '\n' +
                "Id card: " + idCard + '\n' +
                "Address: " + address + '\n';
    }
}
