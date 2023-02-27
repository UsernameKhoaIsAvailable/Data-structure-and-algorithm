package libraryManagement.models;

import java.util.Date;

public class Reader {
    private String id;
    private Date date;
    private String name;
    private Date dateOfBirth;
    private String gender;
    private String idCard;
    private String address;
    private boolean allowToBorrow;

    public Reader(String id, Date date, String name, Date dateOfBirth, String gender, String idCard, String address, boolean allowToBorrow) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.idCard = idCard;
        this.address = address;
        this.allowToBorrow = allowToBorrow;
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

    public boolean isAllowToBorrow() {
        return allowToBorrow;
    }

    public void setAllowToBorrow(boolean allowToBorrow) {
        this.allowToBorrow = allowToBorrow;
    }
}
