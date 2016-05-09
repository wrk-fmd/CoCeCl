package it.fmd.cocecl.fragments;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import it.fmd.cocecl.R;
import it.fmd.cocecl.gmapsnav.PlaceAutoCompleteAdapter;
import it.fmd.cocecl.gmapsnav.RouteBuilder;
import it.fmd.cocecl.gmapsnav.routing.AbstractRouting;
import it.fmd.cocecl.gmapsnav.routing.Route;
import it.fmd.cocecl.gmapsnav.routing.RouteException;
import it.fmd.cocecl.gmapsnav.routing.Routing;
import it.fmd.cocecl.gmapsnav.routing.RoutingListener;
import it.fmd.cocecl.unitstatus.SetIncidentStatus;
import it.fmd.cocecl.utilclass.Animations;
import it.fmd.cocecl.utilclass.ConnectionManager;
import it.fmd.cocecl.utilclass.GPSManager;

public class MapFragment extends SupportMapFragment implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, RoutingListener {

    private String TAG = "MapFragment";

    private GoogleApiClient googleApiClient;

    protected GoogleMap googleMap;

    double latitude;
    double longitude;

    RouteBuilder rb;

    ConnectionManager cm = new ConnectionManager(getContext());

    // Direction
    private PlaceAutoCompleteAdapter mAdapter;
    private ProgressDialog progressDialog;
    private ArrayList<Polyline> polylines;

    private LatLng start;
    private LatLng end; // BO or AO

    //Traffic btn
    private boolean trafficstate;

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

        // attach googleMap to Fragment again to use RouteBuilder
        googleMap = mapFragment.getMap();

        polylines = new ArrayList<>();
        /*
        googleApiClient = new GoogleApiClient.Builder(getContext())
                .addApi(Places.GEO_DATA_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        MapsInitializer.initialize(getContext());
        googleApiClient.connect();
*/
        mAdapter = new PlaceAutoCompleteAdapter(getContext(), android.R.layout.simple_list_item_1,
                googleApiClient, BOUNDS_VIENNA, null);


        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_map);
        FloatingActionButton fab1 = (FloatingActionButton) view.findViewById(R.id.fab1_map);
        FloatingActionButton fab2 = (FloatingActionButton) view.findViewById(R.id.fab2_map);
        FloatingActionButton fab3 = (FloatingActionButton) view.findViewById(R.id.fab3_map);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animations anim = new Animations(getActivity());
                //anim.animateFAB();

                if (!FAB_Status) {
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
                sendRequest();
            }
        });

        fab2.setOnClickListener(new SetIncidentStatus(getActivity()));

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trafficstate = !trafficstate;
                if (trafficstate) {
                    googleMap.setTrafficEnabled(true);
                } else {
                    googleMap.setTrafficEnabled(false);
                }
            }
        });

        return view;
    }

    // -- ONCREATE END -- //

    protected synchronized void buildGoogleApiClient(Context context) {
        Log.i(TAG, "BUILDING GOOGLE API CLIENT");
        googleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.i(TAG, "MAP READY");

        Log.i(TAG, String.valueOf(googleMap.getMapType()));
        /*
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
        */

        //UI Settings
        initializeMapLocationSettings(googleMap);
        //initializeMapTraffic(googleMap);
        initializeMapType(googleMap);
        initializeUiSettings(googleMap);
        initializeMapViewSettings(googleMap);

        // MapCamera to unit position
        mapcamera(googleMap);

        cameraLocChanges(googleMap);

        this.googleMap = googleMap;
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

                latitude = location.getLatitude();
                longitude = location.getLongitude();

                googleMap.moveCamera(center);
                googleMap.animateCamera(zoom);
                route();
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

                latitude = location.getLatitude();
                longitude = location.getLongitude();

                route();
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

    public void emergencystationMarker(GoogleMap googleMap) {

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
        Log.i(TAG, "CONNECTED");

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
            Log.i(TAG, String.valueOf(lastLocation.getLatitude()));
            Log.i(TAG, String.valueOf(lastLocation.getLongitude()));
            LatLng latlng = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
            //map.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 5));
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e(TAG, "CONNECTION FAILED");
        Log.v(TAG, connectionResult.toString());
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "CONNECTION SUSPENDED");
    }

    private static final LatLngBounds BOUNDS_VIENNA = new LatLngBounds(new LatLng(48.099507082257205, 16.164385009765624),
            new LatLng(48.335918508661244, 16.602471191406266));
    private int[] colors = new int[]{R.color.primary_dark, R.color.primary, R.color.primary_light, R.color.accent, R.color.primary_dark_material_light};


    public void sendRequest() {
        if (cm.isOnline(getContext())) {

            route();

        } else {

            //Toast.makeText(getActivity(), "No internet connectivity", Toast.LENGTH_SHORT).show();
        }
    }

    public void route() {
        start = new LatLng(latitude, longitude);

        end = new LatLng(48.1911195, 16.4116613); //BO or AO

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
            //progressDialog.show(getContext(), "Route", "Fetching route information.", true);
            Routing routing = new Routing.Builder()
                    .travelMode(AbstractRouting.TravelMode.DRIVING)
                    //.optimize(true)
                    .withListener(this)
                    .alternativeRoutes(false)
                    .waypoints(start, end)
                    .build();
            routing.execute();
        }
    }

    @Override
    public void onRoutingFailure(RouteException e) {
        // The Routing request failed
        //progressDialog.dismiss();
        if (e != null) {
            Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), "Something went wrong, Try again", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRoutingStart() {
        // The Routing Request starts
    }

    @Override
    public void onRoutingSuccess(List<Route> route, int shortestRouteIndex) {
        //progressDialog.dismiss();
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
            int colorIndex = i % colors.length;

            PolylineOptions polyOptions = new PolylineOptions();
            polyOptions.color(getContext().getResources().getColor(colors[colorIndex]));
            polyOptions.width(10 + i * 3);
            polyOptions.addAll(route.get(i).getPoints());
            Polyline polyline = googleMap.addPolyline(polyOptions);
            polylines.add(polyline);

            Toast.makeText(getContext(), "Route " + (i + 1) + ": distance - " + route.get(i).getDistanceValue() + ": duration - " + route.get(i).getDurationValue(), Toast.LENGTH_LONG).show();
        }

        // Start marker
        MarkerOptions options = new MarkerOptions();
        options.position(start);
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        //options.icon(BitmapDescriptorFactory.fromResource(R.drawable.start_blue));
        //googleMap.addMarker(options);

        // End marker
        options = new MarkerOptions();
        options.position(end);
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        //options.icon(BitmapDescriptorFactory.fromResource(R.drawable.end_green));
        googleMap.addMarker(options);
    }

    @Override
    public void onRoutingCancelled() {
        Log.i(TAG, "Routing was cancelled.");
    }
}