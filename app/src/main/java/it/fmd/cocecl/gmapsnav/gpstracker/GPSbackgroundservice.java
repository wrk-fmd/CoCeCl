package it.fmd.cocecl.gmapsnav.gpstracker;

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

        // ASYNCTASK TO UPDATE THE LOCATION ON SERVER
        // TODO: send method
    }
}
