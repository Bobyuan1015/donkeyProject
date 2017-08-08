package yuancom.bob.myapplication.Modules;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by bob on 07/08/2017.
 */

public class RequestUrlBuilder {
    private static final String DIRECTION_URL_API = "https://maps.googleapis.com/maps/api/directions/json?";
    private static final String GOOGLE_API_KEY = "AIzaSyB6UC1vIvYo5WKsZ8I9DqUFqSYVrsnzYQs";
    final String Tag = "RequestUrlBuilder";

    public RequestUrlBuilder(){

    }
    public String urlCreator(String origin, String destination) throws UnsupportedEncodingException {
        String urlOrigin = URLEncoder.encode(origin, "utf-8");
        String urlDestination = URLEncoder.encode(destination, "utf-8");

        return DIRECTION_URL_API + "origin=" + urlOrigin + "&destination=" + urlDestination + "&key=" + GOOGLE_API_KEY;

    }
}
