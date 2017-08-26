package yuancom.bob.myapplication.Modules;

/**
 * Created by bobyuan on 06/08/2017.
 */

public class ResponseElements {
    public GeocodedWaypoint[] geocodedWaypoints;
    public Route[] routs;
    public String[] available_travel_modes;
    public String status;
    public ResponseElements(GeocodedWaypoint[] geocodedWaypoints, Route[] routs,String[] available_travel_modes,String status ){
        this.geocodedWaypoints = geocodedWaypoints;
        this.routs =  routs;
        this.available_travel_modes = available_travel_modes;
        this.status = status;
    }
}
