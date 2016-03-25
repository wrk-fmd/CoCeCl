package it.fmd.cocecl.gmapsnav.routing;

import java.util.List;

//. by Haseem Saheed
public interface Parser {
    List<Route> parse() throws RouteException;
}