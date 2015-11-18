package it.fmd.cocecl.utilclass;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import it.fmd.cocecl.APPConstants;
import it.fmd.cocecl.LoginActivity;

public class ConnectionManager extends Application {

    //TODO: update connection Manager
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
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

    public boolean isConnectedToServer() {

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnected()) {
            try {
                URL myUrl = new URL(APPConstants.MLS_DOMAIN);
                URLConnection connection = myUrl.openConnection();
                connection.setConnectTimeout(10 * 1000);
                connection.connect();

                Log.wtf("Connection to MLS established", "Success!");
                return true;

            } catch (MalformedURLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                Log.wtf("Connection to MLS not established", "Failure!");
                e.printStackTrace();
            }
        }
        return false;
    }
}

