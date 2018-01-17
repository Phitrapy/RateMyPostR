package fr.eseo.dis.begnaucle.ratemypostr.service.result;

import fr.eseo.dis.begnaucle.ratemypostr.model.User;

/**
 * Created by cleme on 10/01/2018.
 */

public class LogonServiceResult extends ServiceResult {

    public static String SERVICE_NAME = "LOGON";

    private User user;

    public LogonServiceResult(String result) {
        super(result);
    }

    public LogonServiceResult(String result, User user) {
        this(result);
        this.user = user;
    }

    public User getUser(){
        return this.user;
    }
}
