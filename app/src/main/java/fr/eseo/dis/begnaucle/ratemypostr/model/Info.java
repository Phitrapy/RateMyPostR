package fr.eseo.dis.begnaucle.ratemypostr.model;

import java.util.ArrayList;

/**
 * Created by cleme on 12/01/2018.
 */

public class Info {

    private ArrayList<People> members;
    private ArrayList<Projet> projects;

    public ArrayList<People> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<People> members) {
        this.members = members;
    }

    public ArrayList<Projet> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Projet> projects) {
        this.projects = projects;
    }

    public Info(ArrayList<People> members, ArrayList<Projet> projects) {

        this.members = members;
        this.projects = projects;
    }
}
