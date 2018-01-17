package fr.eseo.dis.begnaucle.ratemypostr.service;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.net.MalformedURLException;
import java.net.URL;

import fr.eseo.dis.begnaucle.ratemypostr.model.User;
import fr.eseo.dis.begnaucle.ratemypostr.service.result.Error;
import fr.eseo.dis.begnaucle.ratemypostr.service.result.MyjurServiceResult;

/**
 * Created by cleme on 12/01/2018.
 */
public class MyjurServiceClient extends LijurServiceClient {
    public final static String URL_LOGON = "https://192.168.4.10/www/pfe/webservice.php?q=MYJUR";

    public MyjurServiceClient(Context context, String user, String token) {
        super(context, user, token);
    }

    public MyjurServiceClient(Context context, User user) {
        this(context, user.getUserName(), user.getToken());
    }

    @Override
    public URL getServiceURL() throws MalformedURLException {
        return new URL(URL_LOGON +
                "&user=" + userName +
                "&token=" + token);
    }

    @Override
    protected void onPostExecute(JsonObject response) {
        super.onPostExecute(response);
        String result = response.get("result").getAsString();
        if (result.equals("OK")) {
            this.result = new Gson().fromJson(response.toString(), MyjurServiceResult.class);
        } else {
            Error e = new Gson().fromJson(response.toString(), Error.class);
            this.result = e;
        }
        sendResult();
        notify(result);
    }
}

