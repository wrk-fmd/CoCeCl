package it.fmd.cocecl.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import it.fmd.cocecl.R;
import it.fmd.cocecl.gmapsnav.RouteBuilder;
import it.fmd.cocecl.unitstatus.SetIncidentStatus;
import it.fmd.cocecl.utilclass.Animations;
import it.fmd.cocecl.utilclass.GPSManager;

public class MapFragment extends SupportMapFragment implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    // GET MapFragment
    private GoogleApiClient googleApiClient;

    protected GoogleMap googleMap;

    GPSManager gps = new GPSManager(getContext());
    double latitude = gps.getLatitude();
    double longitude = gps.getLongitude();

    RouteBuilder rb;

    private boolean FAB_Status = false;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        buildGoogleApiClient(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rb = new RouteBuilder(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_map, null, false);
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.supportmap);
        mapFragment.getMapAsync(this);


        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_map);
        FloatingActionButton fab1 = (FloatingActionButton) view.findViewById(R.id.fab1_map);
        FloatingActionButton fab2 = (FloatingActionButton) view.findViewById(R.id.fab2_map);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animations anim = new Animations(getActivity());
                //anim.animateFAB();

                if (FAB_Status == false) {
                    //Display FAB menu
                    anim.expandFAB();
                    FAB_Status = true;
                } else {
                    //Close FAB menu
                    anim.hideFAB();
                    FAB_Status = false;
                }
            }
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rb.sendRequest();
            }
        });

        fab2.setOnClickListener(new SetIncidentStatus(getActivity()));

        return view;
    }

    // -- ONCREATE END -- //

    protected synchronized void buildGoogleApiClient(Context context) {
        //Log.i(LogUtil.TAG, "BUILDING GOOGLE API CLIENT");
        googleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //Log.i(Log.TAG, "MAP READY");

        //Log.i(LogUtil.TAG, String.valueOf(googleMap.getMapType()));
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        //UI Settings
        initializeMapLocationSettings(googleMap);
        initializeMapTraffic(googleMap);
        initializeMapType(googleMap);
        initializeUiSettings(googleMap);
        initializeMapViewSettings(googleMap);

        // MapCamera to unit position
        mapcamera(googleMap);

        cameraLocChanges(googleMap);
    }

    public void cameraLocChanges(final GoogleMap googleMap) {
        /*
        googleMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition position) {
                LatLngBounds bounds = googleMap.getProjection().getVisibleRegion().latLngBounds;
                mAdapter.setBounds(bounds);
            }
        });
*/
        CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(48.19062411, 16.41151965));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(10);

        googleMap.moveCamera(center);
        googleMap.animateCamera(zoom);

        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0, new android.location.LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude()));
                CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);

                googleMap.moveCamera(center);
                googleMap.animateCamera(zoom);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        });

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0, new android.location.LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude()));
                CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);

                googleMap.moveCamera(center);
                googleMap.animateCamera(zoom);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        });
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    public void hospitalMarker() {

    }

    public void emergencystationMarker() {

        Marker west = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(48.20061219999999, 16.3085352))
                .title("WEST"));

        Marker nodo = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(48.1907634, 16.411198))
                .title("NODO"));

        Marker kss = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(48.2671734, 16.4019968))
                .title("KSS"));
        //.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic)));


    }

    // UI SETTINGS START

    public void mapcamera(GoogleMap googleMap) {
        //unit position from gps
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(latitude, longitude)).zoom(12).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    public void initializeMapLocationSettings(GoogleMap googleMap) {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);
    }

    public void initializeMapTraffic(GoogleMap googleMap) {
        googleMap.setTrafficEnabled(true);
    }

    public void initializeMapType(GoogleMap googleMap) {
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    public void initializeMapViewSettings(GoogleMap googleMap) {
        googleMap.setIndoorEnabled(true);
        googleMap.setBuildingsEnabled(false);
    }

    public void initializeUiSettings(GoogleMap googleMap) {
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setRotateGesturesEnabled(false);
        googleMap.getUiSettings().setTiltGesturesEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.getUiSettings().isCompassEnabled();
        googleMap.getUiSettings().setMapToolbarEnabled(true);
    }

    // UI SETTINGS END

    @Override
    public void onResume() {
        super.onResume();
        //mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        //mMapView.onLowMemory();
    }

    @Override
    public void onStart() {
        super.onStart();

        googleApiClient.connect();
    }

    @Override
    public void onStop() {
        googleApiClient.disconnect();

        super.onStop();
    }

    @Override
    public void onConnected(Bundle bundle) {
        //Log.i(LogUtil.TAG, "CONNECTED");

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        if (lastLocation != null) {
            //Log.i(LogUtil.TAG, String.valueOf(lastLocation.getLatitude()));
            //Log.i(LogUtil.TAG, String.valueOf(lastLocation.getLongitude()));
            LatLng latlng = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
            //map.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 5));
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        //Log.e(LogUtil.TAG, "CONNECTION FAILED");
        //Log.v(LOG_TAG,connectionResult.toString());
    }

    @Override
    public void onConnectionSuspended(int i) {
        //Log.i(LogUtil.TAG, "CONNECTION SUSPENDED");
    }
}