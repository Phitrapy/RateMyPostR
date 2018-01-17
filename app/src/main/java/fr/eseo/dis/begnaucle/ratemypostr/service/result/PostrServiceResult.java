package fr.eseo.dis.begnaucle.ratemypostr.service.result;

/**
 * Created by cleme on 13/01/2018.
 */

public class PostrServiceResult extends ServiceResult {

    public static String SERVICE_NAME = "POSTR";

    private byte[] image;

    public PostrServiceResult(String result, String api, byte[] image) {
        super(result);
        this.api = api;
        this.image = image;

    }
}
