package fr.eseo.dis.begnaucle.ratemypostr.service;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

import fr.eseo.dis.begnaucle.ratemypostr.CAValidator;
import fr.eseo.dis.begnaucle.ratemypostr.Observer;
import fr.eseo.dis.begnaucle.ratemypostr.Subject;
import fr.eseo.dis.begnaucle.ratemypostr.service.result.ServiceResult;

/**
 * Created by cleme on 10/01/2018.
 */

public abstract class ServiceClient extends AsyncTask<Void, Void, JsonObject> implements Runnable, Subject {

    public final String URL_DEFAULT = "https://192.168.4.10/www/pfe/webservice.php";

    protected ArrayList<Observer> observers = null;
    protected Context context;
    protected SSLContext sslContext;
    protected ServiceResult result;

    public ServiceClient(Context context) {
        this.observers = new ArrayList<>();
        this.context = context;
        this.sslContext = new CAValidator(context).getSSLContext();
    }

    public ServiceResult getServiceResult() {
        return this.result;
    }

    public URL getServiceURL() throws MalformedURLException {
        return new URL(URL_DEFAULT);
    }

    public JsonObject consumeService() throws ProtocolException{
        InputStream in = null;
        try {
            // Tell the URLConnection to use a SocketFactory from our SSLContext
            URL url = getServiceURL();
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setSSLSocketFactory(sslContext.getSocketFactory());
            urlConnection.connect();

            if (urlConnection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + urlConnection.getResponseCode());
            }
            in = urlConnection.getInputStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //JsonObject jsonObj = parseInputStream(in);
        //System.out.println(jsonObj.toString());
        String output = getStringFromInputStream(in);
        System.out.println(output);
        JsonObject jsonObj = new JsonParser().parse(output).getAsJsonObject();
        return jsonObj;
    }

    public String getStringFromInputStream(InputStream in) {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String result = "";
        String output;
        try {
            while ((output = br.readLine()) != null) {
                result += output + "\n";
            }
        } catch (IOException e) {
        }
        return result;
    }

    public JsonObject parseInputStream(InputStream in) {
        return (JsonObject) new JsonParser().parse(new InputStreamReader(in));
    }

    @Override
    public void addObserver(Observer obs) {
        if (!observers.contains(obs)) {
            observers.add(obs);
        }
    }

    @Override
    public void removeObserver(Observer obs) {
        if (observers.contains(obs)) {
            observers.remove(obs);
        }
    }

    public void notify(String in) {
        for (Observer o : observers) {
            o.react(in);
        }
    }

    protected void sendResult() {
        for (Observer obs : observers) {
            obs.receiveResult(this.getServiceResult());
        }
    }

}
