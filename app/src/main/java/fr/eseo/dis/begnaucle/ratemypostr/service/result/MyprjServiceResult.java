package fr.eseo.dis.begnaucle.ratemypostr.service.result;

import java.util.ArrayList;

import fr.eseo.dis.begnaucle.ratemypostr.model.Projet;

/**
 * Created by cleme on 10/01/2018.
 */

public class MyprjServiceResult extends ServiceResult {

    private static String SERVICE_NAME = "MYPRJ";

    private ArrayList<Projet> projets;

    public MyprjServiceResult(String result) {
        super(result);
    }

    public MyprjServiceResult(String result, ArrayList<Projet> projets) {
        this(result);
        this.projets = projets;
    }

    public ArrayList<Projet> getProjets(){
        return this.projets;
    }
}
