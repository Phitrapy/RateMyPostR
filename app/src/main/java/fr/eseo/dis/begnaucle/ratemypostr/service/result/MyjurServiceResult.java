package fr.eseo.dis.begnaucle.ratemypostr.service.result;

import java.util.ArrayList;

import fr.eseo.dis.begnaucle.ratemypostr.model.Jury;

/**
 * Created by cleme on 12/01/2018.
 */

public class MyjurServiceResult extends LijurServiceResult {
    private static String SERVICE_NAME = "MYJUR";

    public MyjurServiceResult(String result) {
        super(result);
    }

    public MyjurServiceResult(String result, ArrayList<Jury> juries) {
        this(result);
        this.juries = juries;
    }
}
