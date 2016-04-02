package it.fmd.cocecl.gmapsnav;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

import it.fmd.cocecl.MainActivity;
import it.fmd.cocecl.R;
import it.fmd.cocecl.gmapsnav.routing.AbstractRouting;
import it.fmd.cocecl.gmapsnav.routing.Route;
import it.fmd.cocecl.gmapsnav.routing.RouteException;
import it.fmd.cocecl.gmapsnav.routing.Routing;
import it.fmd.cocecl.gmapsnav.routing.RoutingListener;
import it.fmd.cocecl.utilclass.ConnectionManager;

public class RouteBuilder implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, RoutingListener {

    public Activity activity;

    public RouteBuilder(Activity _activity) {

        this.activity = _activity;
    }

    ConnectionManager conman = new ConnectionManager(activity);

    // Direction
    private GoogleApiClient googleApiClient;
    private PlaceAutoCompleteAdapter mAdapter;
    //private ProgressDialog progressDialog;
    private ArrayList<Polyline> polylines;
    //
    //protected LatLng start;
    //protected LatLng end;
    //
    protected GoogleMap googleMap;
    private static final LatLngBounds BOUNDS_VIENNA = new LatLngBounds(new LatLng(48.099507082257205, 16.164385009765624),
            new LatLng(48.335918508661244, 16.602471191406266));
    private int[] colors = new int[]{R.color.primary_dark, R.color.primary, R.color.primary_light, R.color.accent, R.color.primary_dark_material_light};


    public void sendRequest() {
        if (conman.isOnline()) {
            route();
        } else {
            //Toast.makeText(getActivity(), "No internet connectivity", Toast.LENGTH_SHORT).show();
        }
    }

    LatLng start = new LatLng(48.2020979, 16.3490971);
    LatLng end = new LatLng(48.1911195, 16.4116613);

    public void route() {
/*
        TextView bofield = (TextView) this.activity.findViewById(R.id.bofield);
        TextView aofield = (TextView) this.activity.findViewById(R.id.aofield);

        if (start == null || end == null) {
            if (start == null) {
                if (bofield.getText().length() > 0) {
                    //starting.setError("Choose location from dropdown.");
                } else {
                    Toast.makeText(activity, "Please choose a starting point.", Toast.LENGTH_SHORT).show();
                }
            }
            if (end == null) {
                if (aofield.getText().length() > 0) {
                    //destination.setError("Choose location from dropdown.");
                } else {
                    Toast.makeText(activity, "Please choose a destination.", Toast.LENGTH_SHORT).show();
                }
            }
        } else*/
        {
            ProgressDialog progressDialog = new ProgressDialog(activity);
            ProgressDialog.show(activity, "Please wait.",
                    "Fetching route information.", true);
            Routing routing = new Routing.Builder()
                    .travelMode(AbstractRouting.TravelMode.DRIVING)
                            //.optimize(true)
                    .withListener(this)
                    .alternativeRoutes(true)
                    .waypoints(start, end)
                    .build();
            routing.execute();
        }
    }

    @Override
    public void onRoutingFailure(RouteException e) {
        ProgressDialog progressDialog = new ProgressDialog(activity);
        // The Routing request failed
        progressDialog.dismiss();
        if (e != null) {
            Toast.makeText(activity, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(activity, "Something went wrong, Try again", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRoutingStart() {
        // The Routing Request starts
    }

    @Override
    public void onRoutingSuccess(ArrayList<Route> route, int shortestRouteIndex) {
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.dismiss();
        CameraUpdate center = CameraUpdateFactory.newLatLng(start);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);

        googleMap.moveCamera(center);


        if (polylines.size() > 0) {
            for (Polyline poly : polylines) {
                poly.remove();
            }
        }

        polylines = new ArrayList<>();
        //add route(s) to the map.
        for (int i = 0; i < route.size(); i++) {

            //In case of more than 5 alternative routes
            //TODO colorindex
            int colorIndex = i % colors.length;

            PolylineOptions polyOptions = new PolylineOptions();
            polyOptions.color(activity.getResources().getColor(colors[colorIndex]));
            polyOptions.width(10 + i * 3);
            polyOptions.addAll(route.get(i).getPoints());
            Polyline polyline = googleMap.addPolyline(polyOptions);
            polylines.add(polyline);

            Toast.makeText(activity, "Route " + (i + 1) + ": distance - " + route.get(i).getDistanceValue() + ": duration - " + route.get(i).getDurationValue(), Toast.LENGTH_SHORT).show();
        }

        // Start marker
        MarkerOptions options = new MarkerOptions();
        options.position(start);
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        //options.icon(BitmapDescriptorFactory.fromResource(R.drawable.start_blue));
        googleMap.addMarker(options);

        // End marker
        options = new MarkerOptions();
        options.position(end);
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        //options.icon(BitmapDescriptorFactory.fromResource(R.drawable.end_green));
        googleMap.addMarker(options);

    }

    @Override
    public void onConnected(Bundle bundle) {
        //Log.i(LogUtil.TAG, "CONNECTED");
    }

    @Override
    public void onRoutingCancelled() {
        //Log.i(LOG_TAG, "Routing was cancelled.");
    }

    // GOOGLE API
    @Override
    public void onConnectionSuspended(int i) {
        //Log.i(LogUtil.TAG, "CONNECTION SUSPENDED");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        //Log.e(LogUtil.TAG, "CONNECTION FAILED");
        //Log.v(LOG_TAG,connectionResult.toString());
    }
}
