package libraryManagement.models;

import libraryManagement.utils.Utils;

import java.util.Date;

public class Reader implements Identifiable {
    private String id;
    private Date date;
    private String name;
    private Date dateOfBirth;
    private boolean gender;
    private String idCard;
    private String address;
    private boolean canBorrow = true;
    private int bookBorrowed = 0;


    public Reader(String id, Date date, String name, Date dateOfBirth, boolean gender, String idCard, String address) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.idCard = idCard;
        this.address = address;
    }

    public Reader(String id, Date date, String name, Date dateOfBirth, boolean gender, String idCard, String address, boolean canBorrow, int bookBorrowed) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.idCard = idCard;
        this.address = address;
        this.canBorrow = canBorrow;
        this.bookBorrowed = bookBorrowed;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
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
        return canBorrow;
    }

    public void setCanBorrow(boolean canBorrow) {
        this.canBorrow = canBorrow;
    }

    public int getBookBorrowed() {
        return bookBorrowed;
    }

    public void setBookBorrowed(int bookBorrowed) {
        this.bookBorrowed = bookBorrowed;
    }

    @Override
    public String toString() {
        String dateString = Utils.convertDateToString(date);
        String dateOfBirthString = Utils.convertDateToString(dateOfBirth);
        String genderString;
        if (gender) {
            genderString = "Male";
        } else {
            genderString = "Female";
        }
        return "Name: " + name + '\n' +
                "Id: " + id + '\n' +
                "Date: " + dateString + '\n' +
                "Date of birth: " + dateOfBirthString + '\n' +
                "Gender: " + genderString + '\n' +
                "Id card: " + idCard + '\n' +
                "Address: " + address + "\n";
    }
}
