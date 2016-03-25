package it.fmd.cocecl.gmapsnav.gpstracker;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.provider.SyncStateContract;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import it.fmd.cocecl.utilclass.ConnectionManager;
import it.fmd.cocecl.utilclass.ToolbarIconStates;

public class GPSTrackListener extends Service {

    PowerManager.WakeLock wakeLock;

    ConnectionManager cm = new ConnectionManager();

    public Activity activity;

    public GPSTrackListener(Activity _activity) {

        this.activity = _activity;
    }

    ToolbarIconStates tis = new ToolbarIconStates();

    public GPSTrackListener() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();

        PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);

        wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "DoNotSleep");

        // Toast.makeText(getApplicationContext(), "Service Created",
        // Toast.LENGTH_SHORT).show();

        Log.e("Google", "Service Created");

    }

    private LocationListener listener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            // TODO Auto-generated method stub

            Log.e("Google", "Location Changed");

            if (location == null)
                return;

            if (cm.isOnline()) {
                JSONArray jsonArray = new JSONArray();
                JSONObject jsonObject = new JSONObject();

                try {
                    Log.e("latitude", location.getLatitude() + "");
                    Log.e("longitude", location.getLongitude() + "");

                    jsonObject.put("latitude", location.getLatitude());
                    jsonObject.put("longitude", location.getLongitude());

                    jsonArray.put(jsonObject);

                    Log.e("request", jsonArray.toString());
/*
                    new LocationWebService().execute(new String[]{
                            Constants.TRACK_URL, jsonArray.toString()});
                            */
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
            tis.gpsdisabled();

        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
            tis.gpsenabled();

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub

        }
    };

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        wakeLock.release();

    }
}