package fr.eseo.dis.begnaucle.ratemypostr.service;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import fr.eseo.dis.begnaucle.ratemypostr.model.User;
import fr.eseo.dis.begnaucle.ratemypostr.service.result.Error;
import fr.eseo.dis.begnaucle.ratemypostr.service.result.LogonServiceResult;

/**
 * Created by cleme on 10/01/2018.
 */

public class LogonServiceClient extends ServiceClient {

    public final static String URL_LOGON = "https://192.168.4.10/www/pfe/webservice.php?q=LOGON";

    private String userName;
    private String pass;

    public LogonServiceClient(Context context, String user, String pass) {
        super(context);
        this.userName = user;
        this.pass = pass;
    }

    @Override
    public URL getServiceURL() throws MalformedURLException {
        return new URL(URL_LOGON +
                "&user=" + userName +
                "&pass=" +  pass);
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
        if (response != null){
            String result = response.get("result").getAsString();
            if(result.equals("OK")){
                User user = new User(this.userName, response.get("token").getAsString());
                this.result = new LogonServiceResult(result, user);
            } else {
                Error e = new Gson().fromJson(response.toString(), Error.class);
                this.result = e;
            }
            sendResult();
            notify(result);
        }
    }

    @NonNull
    public static LogonServiceClient connectGutowNic(Context context){
        return new LogonServiceClient(context, "schandan", "aWzsD7tjBboI");
    }


}
