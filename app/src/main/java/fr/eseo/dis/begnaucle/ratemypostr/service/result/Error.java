package fr.eseo.dis.begnaucle.ratemypostr.service.result;

import fr.eseo.dis.begnaucle.ratemypostr.service.result.ServiceResult;

/**
 * Created by cleme on 10/01/2018.
 */

public class Error extends ServiceResult{


    private String error;

    public Error(String result, String api, String error){
        super(result);
        this.api = api;
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
