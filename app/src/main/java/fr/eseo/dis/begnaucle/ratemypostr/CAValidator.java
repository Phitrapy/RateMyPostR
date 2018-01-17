package fr.eseo.dis.begnaucle.ratemypostr;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.xml.transform.Result;


/**
 * Created by cleme on 04/01/2018.
 */

public class CAValidator extends AsyncTask<Void, Void, InputStream> implements Subject, Runnable {

    private ArrayList<Observer> observers = null;

    public final String DEFAULT_CERTFILENAME = "root.crt";

    private Context context;
    private String certFileName;

    public CAValidator(Context appContext){
        observers = new ArrayList<Observer>();
        this.context = appContext;
        this.certFileName = DEFAULT_CERTFILENAME;
    }

    public CAValidator(Context appContext, String certFileName) {
        this(appContext);
        this.certFileName = certFileName;
    }

    @Override
    public void run() {
        this.execute();
    }
    @Override
    protected InputStream doInBackground(Void... arg0) {
        return this.securityThing();
    }

    @Override
    protected void onPostExecute(InputStream inputStream) {
        super.onPostExecute(inputStream);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String output;

        try {
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                //notify(output);
            }
        } catch (IOException e) {
            notify("error");
        }
    }

    public SSLContext getSSLContext(){
        SSLContext sslContext = null;
        try {
            // Load CAs from an InputStream
            // (could be from a resource or ByteArrayInputStream or ...)
            CertificateFactory cf = null;
            cf = CertificateFactory.getInstance("X.509");

            // From https://www.washington.edu/itconnect/security/ca/load-der.crt
            // Google's solution : InputStream caInput = null;
            InputStream caInput = null;
            caInput = context.getAssets().open(certFileName);
            //caInput = new BufferedInputStream(new FileInputStream(certFileName));
            Certificate ca;
            try {
                ca = cf.generateCertificate(caInput);
                //System.out.println("ca=" + ((X509Certificate) ca).getSubjectDN());
            } finally {
                caInput.close();
            }

            // Create a KeyStore containing our trusted CAs
            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);

            // Create a TrustManager that trusts the CAs in our KeyStore
            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);

            // Create an SSLContext that uses our TrustManager
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return sslContext;
    }

    public InputStream securityThing(){
        InputStream in = null;
        try {
            // Load CAs from an InputStream
            // (could be from a resource or ByteArrayInputStream or ...)
            CertificateFactory cf = null;
            cf = CertificateFactory.getInstance("X.509");

            // From https://www.washington.edu/itconnect/security/ca/load-der.crt
            // Google's solution : InputStream caInput = null;
            InputStream caInput = null;
            caInput = context.getAssets().open(certFileName);
            //caInput = new BufferedInputStream(new FileInputStream(certFileName));
            Certificate ca;
            try {
                ca = cf.generateCertificate(caInput);
                //System.out.println("ca=" + ((X509Certificate) ca).getSubjectDN());
            } finally {
                caInput.close();
            }

            // Create a KeyStore containing our trusted CAs
            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);

            // Create a TrustManager that trusts the CAs in our KeyStore
            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);

            // Create an SSLContext that uses our TrustManager
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, tmf.getTrustManagers(), null);

            // Tell the URLConnection to use a SocketFactory from our SSLContext
            URL url = new URL("http://172.21.1.62:8181/mission?numTrain=7061");
            //HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.connect();
            //urlConnection.setSSLSocketFactory(context.getSocketFactory());

            if (urlConnection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + urlConnection.getResponseCode());
            }
            in = urlConnection.getInputStream();

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return in;
    }

    private void copyInputStreamToOutputStream(InputStream in, PrintStream out) {
        //out.println(in.toString());
    }

    private void notify(String in){
        for (Observer obs : observers){
            obs.react(in);
        }
    }

    @Override
    public void addObserver(Observer obs) {
        if(!observers.contains(obs)){
            observers.add(obs);
        }
    }

    @Override
    public void removeObserver(Observer obs) {
        if(observers.contains(obs)){
            observers.remove(obs);
        }
    }

}