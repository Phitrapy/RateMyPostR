package fr.eseo.dis.begnaucle.ratemypostr;

import android.app.ListActivity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.begnaucle.ratemypostr.Observer;
import fr.eseo.dis.begnaucle.ratemypostr.Subject;
import fr.eseo.dis.begnaucle.ratemypostr.model.Jury;
import fr.eseo.dis.begnaucle.ratemypostr.model.Projet;
import fr.eseo.dis.begnaucle.ratemypostr.model.User;
import fr.eseo.dis.begnaucle.ratemypostr.service.LiprjServiceClient;
import fr.eseo.dis.begnaucle.ratemypostr.service.MyjurServiceClient;
import fr.eseo.dis.begnaucle.ratemypostr.service.MyprjServiceClient;
import fr.eseo.dis.begnaucle.ratemypostr.service.result.Error;
import fr.eseo.dis.begnaucle.ratemypostr.service.result.LijurServiceResult;
import fr.eseo.dis.begnaucle.ratemypostr.service.result.LiprjServiceResult;
import fr.eseo.dis.begnaucle.ratemypostr.service.result.LogonServiceResult;
import fr.eseo.dis.begnaucle.ratemypostr.service.result.MyprjServiceResult;
import fr.eseo.dis.begnaucle.ratemypostr.service.result.ServiceResult;

public abstract class AbstractActivity extends AppCompatActivity implements Observer {

    ArrayList<Subject> subjects = null;

    protected TextView resultTV = null;
    protected Button LOGONButton = null;
    protected Button LIPRJButton = null;
    protected Button MYPRJButton = null;
    protected Button LIJURButton = null;
    protected Button MYJURButton = null;
    protected Button JYINFButton = null;
    protected Button POSTRButton = null;

    protected Context context = null;

    List<Jury> myJuries = null;
    List<Projet> myProjects = null;
    List<Projet> myProjectsSup = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        this.subjects = new ArrayList<>();
    }

    @Override
    public Subject addSubject(Subject subject) {
        if (!this.subjects.contains(subject)) {
            subject.addObserver(this);
            this.subjects.add(subject);
        }
        return subject;
    }

    @Override
    public Subject removeSubject(Subject subject) {
        if (this.subjects.contains(subject)) {
            subject.removeObserver(this);
            subjects.remove(subject);
        }
        return subject;
    }

    public void removeSubjects() {
        for(Subject s : subjects){
            s.removeObserver(this);
            subjects.remove(s);
        }
    }

    @Override
    public void react(Object obj) {

        String str = (String) obj;
    }

    public void processProjects(ArrayList<Projet> projets){
        for(Projet p : projets){
            System.out.println(p);
        }
    }

    public void processJuries(ArrayList<Jury> juries){
        for(Jury j : juries){
            System.out.println(j);
        }
    }

    public void getMyJuries(){
        try{
            MyjurServiceClient myjurService = new MyjurServiceClient(context, User.getConnectedUser());
            removeSubject(myjurService);
            addSubject(myjurService);

            Thread logonThread = new Thread(myjurService);
            logonThread.start();
        } catch (Exception e){

        }
    }

    public void getMyProjectsSup(){
        if (User.getConnectedUser() != null){
            try{
                MyprjServiceClient myprjService = new MyprjServiceClient(context, User.getConnectedUser());
                removeSubject(myprjService);
                addSubject(myprjService);

                Thread logonThread = new Thread(myprjService);
                logonThread.start();
            } catch (NullPointerException e){

            }
        }

    }

    public void processMyJuries(ArrayList<Jury> juries) {
        this.myJuries = juries;
        this.myProjects = new ArrayList<>();
        for(Jury j : myJuries){
            for(Projet p : j.getAllProjects()){
                this.myProjects.add(p);
            }
        }
        this.updateDisplay();
    }

    public void processMyProjectsSup(ArrayList<Projet> projectsSup){
        this.myProjectsSup = projectsSup;
        this.updateDisplay();
    }

    public void updateDisplay(){

    }

    public void clickProject(Projet projet){
    }

    public void clickJury(Jury jury) {
    }
}
