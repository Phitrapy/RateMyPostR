package fr.eseo.dis.begnaucle.ratemypostr.model;

/**
 * Created by cleme on 10/01/2018.
 */

public class People extends User{

    private String forename;
    private String surname;

    public People(String userID) {
        super(userID);
    }

    public People(String userID, String forename, String surname) {
        this(userID);
        this.forename = forename;
        this.surname = forename;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String toString(){
        return this.forename + " " + this.surname;
    }
}
