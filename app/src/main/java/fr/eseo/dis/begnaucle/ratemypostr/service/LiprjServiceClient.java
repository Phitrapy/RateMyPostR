package fr.eseo.dis.begnaucle.ratemypostr.service;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import fr.eseo.dis.begnaucle.ratemypostr.model.Projet;
import fr.eseo.dis.begnaucle.ratemypostr.model.User;
import fr.eseo.dis.begnaucle.ratemypostr.service.result.LijurServiceResult;
import fr.eseo.dis.begnaucle.ratemypostr.service.result.LiprjServiceResult;

/**
 * Created by cleme on 10/01/2018.
 */

public class LiprjServiceClient extends ServiceClient {
    public final static String URL_LOGON = "https://192.168.4.10/www/pfe/webservice.php?q=LIPRJ";

    private String userName;
    private String token;

    public LiprjServiceClient(Context context, String user, String token) {
        super(context);
        this.userName = user;
        this.token = token;
    }

    public LiprjServiceClient(Context context, User user) {
        this(context, user.getUserName(), user.getToken());
    }

    @Override
    public URL getServiceURL() throws MalformedURLException {
        return new URL(URL_LOGON +
                "&user=" + userName +
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
            this.result = new Gson().fromJson(response.toString(), LiprjServiceResult.class);
        } else {
            Error e = new Gson().fromJson(response.toString(), Error.class);
        }
        sendResult();
        notify(result);
    }
}
