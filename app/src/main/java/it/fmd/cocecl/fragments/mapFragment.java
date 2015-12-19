package it.fmd.cocecl.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import it.fmd.cocecl.MainActivity;
import it.fmd.cocecl.R;
import it.fmd.cocecl.utilclass.GPSManager;

public class mapFragment extends Fragment {

    MapView mMapView;
    private GoogleMap googleMap;

    GPSManager gps = new GPSManager(getContext());
    double latitude = gps.getLatitude();
    double longitude = gps.getLongitude();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // inflate and return the layout
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        mMapView = (MapView) v.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        googleMap = mMapView.getMap();

        //UI Settings
        initializeMapLocationSettings();
        initializeMapTraffic();
        initializeMapType();
        initializeUiSettings();
        initializeMapViewSettings();
/*
        // create marker
        MarkerOptions nodo = new MarkerOptions().position(new LatLng(48.1907634, 16.411198)).title("NODO");
        MarkerOptions kss = new MarkerOptions().position(new LatLng(48.2671734, 16.4019968)).title("KSS");
        //Marker icon
        nodo.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        // adding marker
        googleMap.addMarker(nodo);
        googleMap.addMarker(kss);
*/
        // MapCamera to unit position
        mapcamera();

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        //mapcamera();
        //setmapmarker();
    }

    public void setmapmarker() {
        Marker nodo = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(48.1907634, 16.411198))
                .title("NODO"));

        Marker kss = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(48.2671734, 16.4019968))
                .title("KSS"));
        //.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic)));
    }

    public void mapcamera () {
        //unit position from gps
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(latitude, longitude)).zoom(12).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    public void initializeMapLocationSettings() {
        googleMap.setMyLocationEnabled(true);
    }

    public void initializeMapTraffic() {
        googleMap.setTrafficEnabled(true);
    }

    public void initializeMapType() {
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    public void initializeMapViewSettings() {
        googleMap.setIndoorEnabled(true);
        googleMap.setBuildingsEnabled(false);
    }

    public void initializeUiSettings() {
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setRotateGesturesEnabled(false);
        googleMap.getUiSettings().setTiltGesturesEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
    }

    public void autoNavigate() {

        TextView bo = (TextView) getActivity().findViewById(R.id.bofield);
        TextView ao = (TextView) getActivity().findViewById(R.id.aofield);

        if ((bo.getText().toString().trim().length() > 0) && (ao.getText().toString().trim().length() == 0)) {

            Uri gmmIntentUribo = Uri.parse("google.navigation:q="+bo+"&mode=d");
            Intent mapIntentbo = new Intent(Intent.ACTION_VIEW, gmmIntentUribo);
            mapIntentbo.setPackage("com.google.android.apps.maps");
            startActivity(mapIntentbo);

            if ((bo.getText().toString().trim().length() > 0) && (ao.getText().toString().trim().length() > 0)) {

                Uri gmmIntentUriao = Uri.parse("google.navigation:q=" + ao + "&mode=d");
                Intent mapIntentao = new Intent(Intent.ACTION_VIEW, gmmIntentUriao);
                mapIntentao.setPackage("com.google.android.apps.maps");
                startActivity(mapIntentao);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
/*
    private static View view;
    private static GoogleMap mMap;
    private static Double latitude, longitude;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        view = (RelativeLayout) inflater.inflate(R.layout.fragment_map, container, false);

        latitude = 26.78;
        longitude = 72.56;

        setUpMapIfNeeded(); // For setting up the MapFragment

        return view;
    }

    public static void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) MainActivity.fragmentManager
                    .findFragmentById(R.id.map)).getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null)
                setUpMap();
        }
    }

    private static void setUpMap() {
        // For showing a move to my loction button
        mMap.setMyLocationEnabled(true);
        // For dropping a marker at a point on the Map
        mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("My Home").snippet("Home Address"));
        // For zooming automatically to the Dropped PIN Location
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,
                longitude), 12.0f));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        if (mMap != null)
            setUpMap();

        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) MainActivity.fragmentManager
                    .findFragmentById(R.id.map)).getMap(); // getMap is deprecated
            // Check if we were successful in obtaining the map.
            if (mMap != null)
                setUpMap();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mMap != null) {
            MainActivity.fragmentManager.beginTransaction()
                    .remove(MainActivity.fragmentManager.findFragmentById(R.id.map)).commit();
            mMap = null;
        }
    }
}
/*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        return v;
    }
}
*/
