package fr.eseo.dis.begnaucle.ratemypostr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import fr.eseo.dis.begnaucle.ratemypostr.model.Jury;
import fr.eseo.dis.begnaucle.ratemypostr.model.People;
import fr.eseo.dis.begnaucle.ratemypostr.model.Projet;
import fr.eseo.dis.begnaucle.ratemypostr.model.User;
import fr.eseo.dis.begnaucle.ratemypostr.service.result.Error;
import fr.eseo.dis.begnaucle.ratemypostr.service.result.MyjurServiceResult;
import fr.eseo.dis.begnaucle.ratemypostr.service.result.MyprjServiceResult;
import fr.eseo.dis.begnaucle.ratemypostr.service.result.ServiceResult;

public class HomeActivity extends AbstractActivity {

    List<String> ChildList;
    Map<String, List<String>> ParentListItems;
    RecyclerView juriesListView;
    RecyclerView projectsListView;
    RecyclerView projectsSupListView;
    Button disconnectButton;

    private JuryAdapter jAdapter;
    private ProjectAdapter pAdapter;
    private ProjectAdapter psAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // Call Services
        getMyJuries();
        // Bind views
        this.juriesListView = (RecyclerView) findViewById((R.id.Home_MyJuriesList));
        this.projectsListView = (RecyclerView) findViewById((R.id.Home_MyProjectsList));
        this.projectsSupListView = (RecyclerView) findViewById((R.id.Home_MyProjectsSupList));

        juriesListView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        juriesListView.setLayoutManager(layoutManager);


        projectsListView.setHasFixedSize(true);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        layoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        projectsListView.setLayoutManager(layoutManager2);
//
//        projectsSupListView.setHasFixedSize(true);
//        LinearLayoutManager layoutManager3 = new LinearLayoutManager(this);
//        layoutManager3.setOrientation(LinearLayoutManager.VERTICAL);
//        projectsSupListView.setLayoutManager(layoutManager3);


        ArrayList<Jury> juries123 = new ArrayList<>();
        juries123.add(new Jury(0, "date", new ArrayList<Projet>(), new ArrayList<People>()));

//        while(myJuries == null);
//        getMyProjectsSup();
//        while(myProjectsSup == null);
//        System.out.println("fini");
        //jAdapter.setJuries(juries123);

    }

    @Override
    public void receiveResult(ServiceResult sr) {
        System.out.println(sr.getClass().getSimpleName());
        switch (sr.getClass().getSimpleName()) {
            case "MyprjServiceResult":
                MyprjServiceResult myprj = (MyprjServiceResult) sr;
                processMyProjectsSup(myprj.getProjets());
                updateDisplay();
                break;
            case "MyjurServiceResult":
                MyjurServiceResult myjur = (MyjurServiceResult) sr;
                processMyJuries(myjur.getJuries());
                updateDisplay();
                break;
            case "Error":
                Error e = (Error) sr;
                System.out.println(e.getError());
                break;
            default:
                break;
        }
    }




    public void updateDisplay(){

        System.out.println("myJuries : "+ myJuries);
        System.out.println("myProjects : "+ myProjects);
        System.out.println("myProjectsSup : "+ myProjectsSup);
        // Juries
        if(myJuries != null){
            System.out.println("Juries updated");
            jAdapter = new JuryAdapter(this, myJuries);
            juriesListView.setAdapter(jAdapter);
        }

        // My Projects
        if(myProjects != null){
            System.out.println("Projects updated");
            pAdapter = new ProjectAdapter(this, myProjects);
            projectsListView.setAdapter(pAdapter);
        }


//        // My ProjectsSup
//        if(myProjectsSup != null){
//            System.out.println("ProjectsSup updated");
//            psAdapter = new ProjectAdapter(this, myProjectsSup);
//            projectsSupListView.setAdapter(psAdapter);
//        }
    }

    @Override
    public void clickProject(Projet project) {
        try{
            System.out.println("Going to details of the project " + project.getTitle() +", id : " + project.getId());
            //startActivity(new Intent(this, ProjectDetailsActivity.class));
        } catch (Exception e) {

        }
    }

    @Override
    public void clickJury(Jury jury) {
        try{
            System.out.println("Going to details of the jury " + jury.getIdJury());
            //startActivity(new Intent(this, JuryDetailsActivity.class));
        } catch (Exception e) {

        }
    }
}