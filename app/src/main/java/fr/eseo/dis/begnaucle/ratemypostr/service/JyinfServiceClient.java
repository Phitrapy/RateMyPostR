package fr.eseo.dis.begnaucle.ratemypostr.service;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import fr.eseo.dis.begnaucle.ratemypostr.model.User;
import fr.eseo.dis.begnaucle.ratemypostr.service.result.Error;
import fr.eseo.dis.begnaucle.ratemypostr.service.result.JyinfServiceResult;
import fr.eseo.dis.begnaucle.ratemypostr.service.result.LijurServiceResult;

/**
 * Created by cleme on 13/01/2018.
 */

public class JyinfServiceClient extends ServiceClient {
    public final static String URL_LOGON = "https://192.168.4.10/www/pfe/webservice.php?q=JYINF";

    private String userName;
    private int juryId;
    private String token;

    public JyinfServiceClient(Context context, String user, int juryId, String token) {
        super(context);
        this.userName = user;
        this.juryId = juryId;
        this.token = token;
    }

    public JyinfServiceClient(Context context, User user, int juryId) {
        this(context, user.getUserName(), juryId, user.getToken());
    }

    @Override
    public URL getServiceURL() throws MalformedURLException {
        return new URL(URL_LOGON +
                "&user=" + userName +
                "&jury=" + juryId +
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
            this.result = new Gson().fromJson(response.toString(), JyinfServiceResult.class);
        } else {
            Error e = new Gson().fromJson(response.toString(), Error.class);
            this.result = e;
        }
        sendResult();
        notify(result);
    }
}
