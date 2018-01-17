package fr.eseo.dis.begnaucle.ratemypostr.model;

import java.util.ArrayList;

/**
 * Created by cleme on 10/01/2018.
 */

public class Projet {
    private int projectId;
    private String title;
    private String descrip;
    private boolean poster;
    private Supervisor supervisor;
    private int confid;
    private People[] students;

    public Projet(int id, String title, String descrip, boolean poster, Supervisor supervisor, int confid, People[] students) {
        this.projectId = id;
        this.title = title;
        this.descrip = descrip;
        this.poster = poster;
        this.supervisor = supervisor;
        this.confid = confid;
        this.students = students;
    }

    public int getId() {
        return projectId;
    }

    public void setId(int id) {
        this.projectId = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public boolean isPoster() {
        return poster;
    }

    public void setPoster(boolean poster) {
        this.poster = poster;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    public int getConfid() {
        return confid;
    }

    public void setConfid(int confid) {
        this.confid = confid;
    }

    public People[] getStudents() {
        return students;
    }

    public void setStudents(People[] students) {
        this.students = students;
    }

    public String toString(){
        String studentsSTR = "";
        if (students!=null){
            for(int i =0; i < this.students.length; i++){
                studentsSTR += this.students[i].toString() + ", ";
            }
        } else {
            studentsSTR = "null";
        }

        return "id : " + projectId +
                " title : " + title +
                " supervisor " + supervisor.toString() +
                " etudiants : " + studentsSTR;
    }

}
