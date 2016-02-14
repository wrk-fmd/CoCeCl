package it.fmd.cocecl.gmapsnav;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * GPS backgroundservice, sends continuous coordinates to server
 */

public class GPSbackgroundservice extends BroadcastReceiver {

    double latitude, longitude;

    @Override
    public void onReceive(final Context context, final Intent calledIntent) {
        Log.d("LOC_RECEIVER", "Location RECEIVED!");

        latitude = calledIntent.getDoubleExtra("latitude", -1);
        longitude = calledIntent.getDoubleExtra("longitude", -1);

        updateRemote(latitude, longitude);

    }

    private void updateRemote(final double latitude, final double longitude) {

        //HERE YOU CAN PUT YOUR ASYNCTASK TO UPDATE THE LOCATION ON YOUR SERVER
        //TODO: send method
    }
}
