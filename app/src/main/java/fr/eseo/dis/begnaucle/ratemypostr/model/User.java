package fr.eseo.dis.begnaucle.ratemypostr.model;

/**
 * Created by cleme on 10/01/2018.
 */

public class User {

    private static User connectedUser = null;
    private String userName;
    private String token;

    public User(String userName){
        this.userName = userName;
    }

    public User(String userName, String token){
        this.userName = userName;
        this.token = token;
    }

    public String getUserName(){
        return this.userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getToken(){
        return this.token;
    }

    public void setToken(String token){
        this.token = token;
    }

    public static void setConnectedUser(User user) {
        connectedUser = user;
    }

    public static User getConnectedUser(){
        return connectedUser;
    }

}
