package it.fmd.cocecl.gmapsnav;

import android.location.Location;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;

import java.util.List;

public class GoogleMapUtils {

    public static void toggleStyle(GoogleMap googleMap) {
        if (GoogleMap.MAP_TYPE_NORMAL == googleMap.getMapType()) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        } else {
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
    }

    public static Location convertLatLngToLocation(LatLng latLng) {
        Location loc = new Location("someLoc");
        loc.setLatitude(latLng.latitude);
        loc.setLongitude(latLng.longitude);
        return loc;
    }

    public static float bearingBetweenLatLngs(LatLng begin, LatLng end) {
        Location beginL = convertLatLngToLocation(begin);
        Location endL = convertLatLngToLocation(end);
        return beginL.bearingTo(endL);
    }

    public static String encodeMarkerForDirection(Marker marker) {
        return marker.getPosition().latitude + "," + marker.getPosition().longitude;
    }

    public static void fixZoomForLatLngs(GoogleMap googleMap, List<LatLng> latLngs) {
        if (latLngs != null && latLngs.size() > 0) {
            LatLngBounds.Builder bc = new LatLngBounds.Builder();

            for (LatLng latLng : latLngs) {
                bc.include(latLng);
            }

            googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bc.build(), 50), 4000, null);
        }
    }

    public static void fixZoomForMarkers(GoogleMap googleMap, List<Marker> markers) {
        if (markers != null && markers.size() > 0) {
            LatLngBounds.Builder bc = new LatLngBounds.Builder();

            for (Marker marker : markers) {
                bc.include(marker.getPosition());
            }

            googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bc.build(), 50), 4000, null);
        }
    }
}
