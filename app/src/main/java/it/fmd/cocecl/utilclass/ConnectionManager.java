package it.fmd.cocecl.utilclass;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import it.fmd.cocecl.APPConstants;

public class ConnectionManager extends Application {

    //registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

    //public class ConnectivityBroadcast extends BroadcastReceiver {
    public final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null) { // connected to the internet
                if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                    // connected to wifi
                    //Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                    // connected to the mobile provider's data plan
                    //Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                }
            } else {
                // not connected to the internet
            }
        }
    };

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
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

    public boolean isConnectedToServer() {

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null && activeNetwork.isConnected()) {
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

    // Check if GPS enabled / show icon in appbar
/*
    public void checkGPS() {

        TextView gpstext = (TextView) findViewById(R.id.textView108);

        LocationManager lm = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

        } catch (Exception ex) {}

        if (!gps_enabled) {
            gpstext.setBackgroundColor(RED);
        }

        if (gps_enabled) {
            gpstext.setBackgroundColor(GREEN);
        }
    }
    */
}

