package fr.eseo.dis.begnaucle.ratemypostr.service.result;

import java.util.ArrayList;

import fr.eseo.dis.begnaucle.ratemypostr.model.Jury;
import fr.eseo.dis.begnaucle.ratemypostr.model.Projet;

/**
 * Created by cleme on 10/01/2018.
 */

public class LiprjServiceResult extends ServiceResult {

    public static String SERVICE_NAME = "LIPRJ";

    private ArrayList<Projet> projects;


    public LiprjServiceResult(String result, ArrayList<Projet> projects) {
        super(result);
        this.projects = projects;
    }

    public ArrayList<Projet> getProjects(){
        return this.projects;
    }
}
