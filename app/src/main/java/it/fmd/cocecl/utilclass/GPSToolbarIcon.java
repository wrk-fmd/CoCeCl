package it.fmd.cocecl.utilclass;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

public class GPSToolbarIcon implements LocationListener {

    private Context context;
    private Activity activity;

    public GPSToolbarIcon(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    ToolbarIconStates tis;
    GPSManager gpsm;

    public void onCreate() {

        tis = new ToolbarIconStates(context, activity);
        gpsm = new GPSManager(context);
    }

    public void checkGPS() {

        if (gpsm.canGetLocation()) {
            tis.gpsenabled();
            Toast.makeText(context, "Mobile Location (GPS)", Toast.LENGTH_LONG).show();
        } else {
            tis.gpsdisabled();
        }

        if (gpsm.isGPSEnabled) {
            tis.gpsenabled();
            Toast.makeText(context, "Mobile Location (GPS)", Toast.LENGTH_LONG).show();
        } else {
            tis.gpsdisabled();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location == null) {
            return;
        } else {
            checkGPS();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        checkGPS();
    }

    @Override
    public void onProviderEnabled(String provider) {
        checkGPS();
    }

    @Override
    public void onProviderDisabled(String provider) {
        checkGPS();
    }
}
