package fr.eseo.dis.begnaucle.ratemypostr.service.result;

import java.util.ArrayList;

import fr.eseo.dis.begnaucle.ratemypostr.model.People;
import fr.eseo.dis.begnaucle.ratemypostr.model.Projet;

/**
 * Created by cleme on 13/01/2018.
 */

public class JyinfServiceResult extends ServiceResult {

    public static String SERVICE_NAME = "JYINF";

    private ArrayList<Projet> projects;
    private ArrayList<People> members;

    public JyinfServiceResult(String result, String api, ArrayList<Projet> projects, ArrayList<People> members) {
        super(result);
        this.api = api;
        this.projects = projects;
        this.members = members;
    }
}
