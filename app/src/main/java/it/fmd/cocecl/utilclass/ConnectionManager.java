package it.fmd.cocecl.utilclass;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;

import it.fmd.cocecl.APPConstants;

public class ConnectionManager {

    ConnectivityManager cm;

    private Context context;

    public ConnectionManager(Context context) {
        this.context = context;
    }

    public final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null) {

                // connected to the internet
                if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                    // connected to wifi

                } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {

                    // connected to the mobile network
                }
            } else {

                // not connected to the internet
            }
        }
    };

    public boolean isOnline(Context context) {
        cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    /*
        public boolean isOnline() {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            //return netInfo != null && netInfo.isConnectedOrConnecting();

            if (cm.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||

                    cm.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                    cm.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                    cm.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {


                return true;

            } else if (
                    cm.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                            cm.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {


                return false;
            }
            return false;
        }
    */
    public boolean ping() {

        Runtime runtime = Runtime.getRuntime();
        try {

            Process ipProcess = runtime.exec("/system/bin/ping -c 1 " + APPConstants.MLS_DOMAIN);
            int exitValue = ipProcess.waitFor();

            //


            return (exitValue == 0);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //


        return false;
    }

    /*
    static public boolean isConnectedToServer(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            try {
                URL url = new URL("http://google.com");
                HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                urlc.setConnectTimeout(10 * 1000);          // 10 s.
                urlc.connect();
                if (urlc.getResponseCode() == 200) {        // 200 = "OK" code (http connection is fine).
                    Log.wtf("Connection", "Success !");
                    return true;
                } else {
                    return false;
                }
            } catch (MalformedURLException e1) {
                return false;
            } catch (IOException e) {
                return false;
            }
        }
        return false;
    }
/*
    public boolean isConnectedToServer() {

        ConnectivityManager cm = (ConnectivityManager) this.activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null && activeNetwork.isConnected()) {
            try {
                URL myUrl = new URL(APPConstants.MLS_DOMAIN);
                URLConnection connection = myUrl.openConnection();
                connection.setConnectTimeout(10 * 1000);
                connection.connect();

                Log.wtf("Connection to MLS established", "Success!");
                tis.mlsonline();
                tis.mlsonlineicon();
                return true;

            } catch (MalformedURLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                Log.wtf("Connection to MLS not established", "Failure!");
                tis.mlsoffline();
                tis.mlsofflineicon();
                e.printStackTrace();
            }
        }
        return false;
    }*/
}

