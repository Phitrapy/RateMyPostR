package fr.eseo.dis.begnaucle.ratemypostr.model;

import java.util.ArrayList;

/**
 * Created by cleme on 10/01/2018.
 */

public class Jury {
    private int idJury;
    private String date;
    private Info info;


    public Jury(int idJury, String date, ArrayList<Projet> projects, ArrayList<People> members) {
        this.idJury = idJury;
        this.info = new Info(members, projects);
        this.date = date;

    }

    public int getIdJury() {
        return idJury;
    }

    public void setIdJury(int idJury) {
        this.idJury = idJury;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String toString(){
        String membersSTR = "";
        for (People p : info.getMembers()){
            membersSTR += p.toString() + " | ";
        }
        String projetsSTR = "";
        for (Projet p : info.getProjects()){
            projetsSTR += p.toString() + " | ";
        }
        return "id : " + idJury + " |" +
                "date : " + date + " |" +
                membersSTR +  " |" +
                projetsSTR + " |";
    }

    public ArrayList<Projet> getAllProjects(){
        return this.info.getProjects();
    }
}
