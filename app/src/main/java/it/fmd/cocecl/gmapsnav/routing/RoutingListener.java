package it.fmd.cocecl.gmapsnav.routing;

import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;

public interface RoutingListener {
    void onRoutingFailure(RouteException e);

    void onRoutingStart();

    void onRoutingSuccess(ArrayList<Route> route, int shortestRouteIndex);

    void onRoutingCancelled();
}
