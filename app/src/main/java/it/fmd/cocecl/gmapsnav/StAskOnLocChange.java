package it.fmd.cocecl.gmapsnav;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import it.fmd.cocecl.MainActivity;

/**
 * If Unit moves away from location (BO or AO) & didn´t update status within 100m, app ask for status change
 */
//TODO: implement method to ask for status change
public abstract class StAskOnLocChange extends Activity {

    private LocationListener mLocationListener;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(final Location location) {
                updateLocation(location);
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

    protected abstract void updateLocation(Location location);

}