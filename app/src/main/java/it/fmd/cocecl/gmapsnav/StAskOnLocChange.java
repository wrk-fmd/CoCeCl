package it.fmd.cocecl.gmapsnav;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.View;

import it.fmd.cocecl.unitstatus.SetIncidentStatus;
import it.fmd.cocecl.utilclass.GPSManager;

/**
 * If Unit moves away from location (BO or AO) & didn´t update status within 100m, app ask for status change
 */
//TODO: implement method to ask for status change

public class StAskOnLocChange extends Activity {

    GPSManager gps = new GPSManager(this);

    Location locationA = new Location("point A");
    Location locationB = new Location("point B");

    float distance = locationA.distanceTo(locationB);

    private LocationListener mLocationListener;

    public void locA() {

        double latA = gps.getLatitude();
        double lngA = gps.getLongitude();
/*
        double latA = locdata.getLatBO();
        double lngA = locdata.getLonBO();
*/
        locationA.setLatitude(latA);
        locationA.setLongitude(lngA);
    }

    public void locB() {

        double latB = gps.getLatitude();
        double lngB = gps.getLongitude();

        locationB.setLatitude(latB);
        locationB.setLongitude(lngB);
    }

    public void calcDist(View v) {

        if (distance > 100) {

            SetIncidentStatus sis = new SetIncidentStatus(this);

            sis.onClick(v);
        }
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(final Location location) {
                locB();
            }

            @Override
            public void onStatusChanged(final String provider,
                                        final int status, final Bundle extras) {
            }

            @Override
            public void onProviderEnabled(final String provider) {
            }

            @Override
            public void onProviderDisabled(final String provider) {
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();

        // register your listener with the location manager
    }

    @Override
    protected void onStop() {
        super.onStop();

        // unregister your listener from the location manager
    }

    //protected abstract void updateLocation(Location location);

}