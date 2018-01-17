package fr.eseo.dis.begnaucle.ratemypostr.service;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import fr.eseo.dis.begnaucle.ratemypostr.model.User;
import fr.eseo.dis.begnaucle.ratemypostr.service.result.Error;
import fr.eseo.dis.begnaucle.ratemypostr.service.result.PostrServiceResult;

/**
 * Created by cleme on 13/01/2018.
 */

public class PostrServiceClient extends ServiceClient {
    public final static String URL_POSTR = "https://192.168.4.10/www/pfe/webservice.php?q=POSTR";

    private String userName;
    private StylePoster style = StylePoster.FULL;
    private int projectId;
    private String token;

    public PostrServiceClient(Context context, String user, int projectId, String token, StylePoster style){
        super(context);
        this.userName = user;
        this.projectId = projectId;
        this.token = token;
        this.style = style;
    }

    public PostrServiceClient(Context context, String user, int projectId, String token) {
        super(context);
        this.userName = user;
        this.projectId = projectId;
        this.token = token;
    }

    public PostrServiceClient(Context context, User user, int projectId, StylePoster style) {
        this(context, user.getUserName(), projectId, user.getToken(), style);
    }

    public PostrServiceClient(Context context, User user, int projectId) {
        this(context, user.getUserName(), projectId, user.getToken());
    }

    @Override
    public URL getServiceURL() throws MalformedURLException {
        return new URL(URL_POSTR +
                "&user=" + userName +
                "&proj=" + projectId +
                "&style=" + style +
                "&token=" + token);
    }

    @Override
    public void run() {
        try {
            System.out.println(getServiceURL().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        this.execute();
    }

    @Override
    protected JsonObject doInBackground(Void... voids) {
        JsonObject response = null;
        try {
            response = this.consumeService();
        } catch (ProtocolException e){
            System.out.println("Erreur de connexion");
        }
        return response;
    }

    @Override
    protected void onPostExecute(JsonObject response) {
        super.onPostExecute(response);
        String result = response.get("result").getAsString();
        if (result.equals("OK")) {
            //this.result = new Gson().fromJson(response.toString(), PostrServiceResult.class);
        } else {
            Error e = new Gson().fromJson(response.toString(), Error.class);
            this.result = e;
        }
        sendResult();
        notify(result);
    }
}
