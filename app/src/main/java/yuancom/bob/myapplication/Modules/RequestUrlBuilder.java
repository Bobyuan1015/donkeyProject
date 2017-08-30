package yuancom.bob.myapplication.Modules;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by bob on 07/08/2017.
 */

public class RequestUrlBuilder {
    private static final String DIRECTION_URL_API = "https://maps.googleapis.com/maps/api/directions/json?";
    private static final String GOOGLE_API_KEY = "AIzaSyB6UC1vIvYo5WKsZ8I9DqUFqSYVrsnzYQs";
    final String Tag = "RequestUrlBuilder";

    public RequestUrlBuilder(){

    }
//URLs must be properly encoded to be valid and are limited to 8192 characters for all web services. Be aware of this limit when constructing your URLs.
    public String urlCreator(LatLng origin, LatLng destination, ArrayList<LatLng> wayPoints) throws UnsupportedEncodingException {

        String urlOrigin = encodeToUtf8( Double.toString(origin.latitude) +","+ Double.toString(origin.longitude));
        String urlDestination = encodeToUtf8( Double.toString(destination.latitude) +","+ Double.toString(destination.longitude));
        String urlwaypPoints = "";
        if( wayPoints != null)
        {
            for ( LatLng waypoint : wayPoints)
            {    urlwaypPoints += "|"+Double.toString(waypoint.latitude) +","+ Double.toString(waypoint.longitude) ;
                Log.d(Tag,"urlCreator waypoint="+waypoint);
            }
            Log.d(Tag,"no utf-8 waypPoints= "+urlwaypPoints);
            urlwaypPoints = encodeToUtf8(urlwaypPoints);
            Log.d(Tag," utf-8 waypPoints= "+ urlwaypPoints);
        }
        return DIRECTION_URL_API + "origin=" + urlOrigin + "&destination=" + urlDestination +
                (wayPoints != null ? "&waypoints=optimize:true"+urlwaypPoints:"")+"&key=" + GOOGLE_API_KEY;

    }
    String encodeToUtf8(String url)
    {
        try {
            return URLEncoder.encode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  url;
    }
}
