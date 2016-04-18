package it.fmd.cocecl.utilclass;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * BroadcastReceiver for toolbar connection icons
 */

public class ConnectionBroadcastReceiver {

    ConnectivityManager cm;
    ToolbarIconStates tis;

    private Context context;

    public ConnectionBroadcastReceiver(Context context, Activity activity) {
        this.context = context;
        tis = new ToolbarIconStates(context, activity);
    }

    public final BroadcastReceiver toolbarBReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null) {

                // connected to the internet
                if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {

                    // connected to wifi
                    tis.setwifigreen();

                } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {

                    // connected to the mobile network
                    tis.setmobilegreen();
                }
            } else {

                // not connected to the internet
                tis.setred();
            }
        }
    };
}
