package fr.eseo.dis.begnaucle.ratemypostr.service.result;

/**
 * Created by cleme on 10/01/2018.
 */

public class ServiceResult {

    private static String SERVICE_NAME = "NULL";

    protected String result;
    protected String api;

    public ServiceResult(String result) {
        this.result = result;
        this.api = SERVICE_NAME;
    }

    public String getResult() {

        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getServiceName(){
        return this.SERVICE_NAME;
    }
}
