package fr.eseo.dis.begnaucle.ratemypostr;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import fr.eseo.dis.begnaucle.ratemypostr.model.Jury;
import fr.eseo.dis.begnaucle.ratemypostr.model.Projet;
import fr.eseo.dis.begnaucle.ratemypostr.model.User;
import fr.eseo.dis.begnaucle.ratemypostr.service.JyinfServiceClient;
import fr.eseo.dis.begnaucle.ratemypostr.service.LijurServiceClient;
import fr.eseo.dis.begnaucle.ratemypostr.service.LiprjServiceClient;
import fr.eseo.dis.begnaucle.ratemypostr.service.LogonServiceClient;
import fr.eseo.dis.begnaucle.ratemypostr.service.MyjurServiceClient;
import fr.eseo.dis.begnaucle.ratemypostr.service.MyprjServiceClient;
import fr.eseo.dis.begnaucle.ratemypostr.service.PostrServiceClient;
import fr.eseo.dis.begnaucle.ratemypostr.service.StylePoster;
import fr.eseo.dis.begnaucle.ratemypostr.service.result.Error;
import fr.eseo.dis.begnaucle.ratemypostr.service.result.LijurServiceResult;
import fr.eseo.dis.begnaucle.ratemypostr.service.result.LiprjServiceResult;
import fr.eseo.dis.begnaucle.ratemypostr.service.result.LogonServiceResult;
import fr.eseo.dis.begnaucle.ratemypostr.service.result.MyprjServiceResult;
import fr.eseo.dis.begnaucle.ratemypostr.service.result.ServiceResult;

public class MainActivity2 extends AppCompatActivity implements Observer {

    LogonServiceClient logonService = null;

    ArrayList<Subject> subjects = null;

    private TextView resultTV = null;
    private Button findButton = null;
    private Button LIPRJButton = null;
    private Button MYPRJButton = null;
    private Button LIJURButton = null;
    private Button MYJURButton = null;
    private Button JYINFButton = null;
    private Button POSTRButton = null;

    private Context context = null;
    private User user = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        subjects = new ArrayList<>();
        context = this;


        resultTV = (TextView) findViewById(R.id.MainActivity_resultTV);
        // LOGON
        findButton = (Button) findViewById(R.id.MainActivity_findButton);
        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (logonService != null) {
                    removeSubject(logonService);
                }
                logonService = LogonServiceClient.connectGutowNic(context);
                addSubject(logonService);

                Thread logonThread = new Thread(logonService);
                logonThread.start();
            }
        });

        // LIPRJ
        LIPRJButton = (Button) findViewById(R.id.MainActivity_LIPRJButton);
        LIPRJButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    LiprjServiceClient liprjService = new LiprjServiceClient(context, user);
                    removeSubject(liprjService);
                    addSubject(liprjService);

                    Thread logonThread = new Thread(liprjService);
                    logonThread.start();
                } catch (NullPointerException e){
                    System.out.println("User non connecté!");
                }

            }
        });

        // MYPRJ
        MYPRJButton = (Button) findViewById(R.id.MainActivity_MYPRJButton);
        MYPRJButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    MyprjServiceClient myprjService = new MyprjServiceClient(context, user);
                    removeSubject(myprjService);
                    addSubject(myprjService);

                    Thread logonThread = new Thread(myprjService);
                    logonThread.start();
                } catch (NullPointerException e){
                    System.out.println("User non connecté!");
                }

            }
        });

        // LIJUR
        LIJURButton = (Button) findViewById(R.id.MainActivity_LIJURButton);
        LIJURButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    LijurServiceClient lijurService = new LijurServiceClient(context, user);
                    removeSubject(lijurService);
                    addSubject(lijurService);

                    Thread logonThread = new Thread(lijurService);
                    logonThread.start();
                } catch (NullPointerException e){
                    System.out.println("User non connecté!");
                }

            }
        });

        // MYJUR
        MYJURButton = (Button) findViewById(R.id.MainActivity_MYJURButton);
        MYJURButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    MyjurServiceClient myjurService = new MyjurServiceClient(context, user);
                    removeSubject(myjurService);
                    addSubject(myjurService);

                    Thread logonThread = new Thread(myjurService);
                    logonThread.start();
                } catch (NullPointerException e){
                    System.out.println("User non connecté!");
                }

            }
        });

        // JYINF
        JYINFButton = (Button) findViewById(R.id.MainActivity_JYINFButton);
        JYINFButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    JyinfServiceClient jyinfService = new JyinfServiceClient(context, user, 2);
                    removeSubject(jyinfService);
                    addSubject(jyinfService);

                    Thread logonThread = new Thread(jyinfService);
                    logonThread.start();
                } catch (NullPointerException e){
                    System.out.println("User non connecté!");
                }

            }
        });

        // POSTR
        POSTRButton = (Button) findViewById(R.id.MainActivity_POSTRButton);
        POSTRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    PostrServiceClient postrService = new PostrServiceClient(context, user, 1, StylePoster.THUMB);
                    removeSubject(postrService);
                    addSubject(postrService);

                    Thread logonThread = new Thread(postrService);
                    logonThread.start();
                } catch (NullPointerException e){
                    System.out.println("User non connecté!");
                }

            }
        });


        System.out.println("READY---READY---READY---READY---");
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

    @Override
    public void react(Object obj) {

        String str = (String) obj;
        resultTV.setText(str);
    }

    @Override
    public void receiveResult(ServiceResult sr) {
        System.out.println(sr.getClass().getSimpleName());
        switch (sr.getClass().getSimpleName()){
            case "LogonServiceResult":
                this.user = ((LogonServiceResult) sr).getUser();
                System.out.println("NOUVEL UTILISATEUR : " + user.toString());
                break;
            case "LiprjServiceResult":
                LiprjServiceResult liprj = (LiprjServiceResult) sr;
                processProjects(liprj.getProjects());
                break;
            case "MyprjServiceResult":
                MyprjServiceResult myprj = (MyprjServiceResult) sr;
                processProjects(myprj.getProjets());
                break;
            case "LijurServiceResult":
                LijurServiceResult lijur = (LijurServiceResult) sr;
                processJuries(lijur.getJuries());
                break;
            case "Error":
                Error e = (Error) sr;
                System.out.println(e.getError());
                break;
            default :
                break;
        }

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


}
