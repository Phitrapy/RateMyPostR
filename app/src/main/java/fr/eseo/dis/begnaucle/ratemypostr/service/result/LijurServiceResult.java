package fr.eseo.dis.begnaucle.ratemypostr.service.result;

import java.util.ArrayList;

import fr.eseo.dis.begnaucle.ratemypostr.model.Jury;
import fr.eseo.dis.begnaucle.ratemypostr.model.Projet;

/**
 * Created by cleme on 10/01/2018.
 */

public class LijurServiceResult extends ServiceResult {

    private static String SERVICE_NAME = "LIJUR";

    protected ArrayList<Jury> juries;

    public LijurServiceResult(String result) {
        super(result);
    }

    public LijurServiceResult(String result, ArrayList<Jury> juries) {
        this(result);
        this.juries = juries;
    }

    public ArrayList<Jury> getJuries(){
        return this.juries;
    }
}
