package fr.eseo.dis.begnaucle.ratemypostr.model;

/**
 * Created by cleme on 10/01/2018.
 */

public class Supervisor {
    private String forename;
    private String surname;

    public Supervisor(String forename, String surname) {
        this.forename = forename;
        this.surname = surname;
    }

    public String getForename() {

        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String toString(){
        return forename + " " + surname;
    }
}
