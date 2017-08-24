package yuancom.bob.myapplication.Modules;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.PolyUtil;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bobyuan on 06/08/2017.
 */

public class BackEndSession extends AsyncTask<String, Void, String>{

    final String Tag = "BackEndSession";
    private int mFileCounter = 0;
    private DownloadListener downloadListener;
    private  ResponseElements responseElements;

    public BackEndSession (DownloadListener downloadListener){
        this.downloadListener = downloadListener;
    }

    @Override
    protected String doInBackground(String... params) {
        String link = params[0];
        Log.d(Tag,"doInBackground aa param[0]="+link);
        try {
            URL url = new URL(link);
            InputStream is = url.openConnection().getInputStream();
            StringBuffer buffer = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            return buffer.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    protected void onPostExecute(String res) {
        if( mFileCounter > 100)
            mFileCounter = 0;
        FileOutputStream outputStream;

        try {
            outputStream = new FileOutputStream("/sdcard/donckeydata/DonckeyMapInf"+ mFileCounter +".json");
            outputStream.write(res.getBytes());
            outputStream.close();
            mFileCounter++;
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            parseJSon(res);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void parseJSon(String data) throws JSONException {
        // Log.d(Tag,"parseJSon, data="+data);
        Log.d(Tag,"parseJSon ----begin----");
        if (data == null)
            return;
        responseElements = new ResponseElements();

         String[] available_travel_modes;
         String status;
        JSONObject jsonData = new JSONObject(data);
        responseElements.status = jsonData.getJSONObject("status").toString();

        if( responseElements.status.equals("OK")) {

// parse WayPoints information
            JSONArray jasonWaypoints = jsonData.getJSONArray("geocoded_waypoints");
            Log.d(Tag, "Waypoints length= " + jasonWaypoints.length());
            GeocodedWaypoint[] geocodedWaypoints = new GeocodedWaypoint[jasonWaypoints.length()];
            String[] types;
            Leg leg[];
            for (int i = 0; i < jasonWaypoints.length(); i++) {
                JSONObject jasonWaypoint = jasonWaypoints.getJSONObject(i);
                geocodedWaypoints[i].geocoder_status = jasonWaypoint.getString("geocoder_status");
                geocodedWaypoints[i].place_id = jasonWaypoint.getString("place_id");
                try {
                    geocodedWaypoints[i].partial_match = jasonWaypoint.getBoolean("partial_match");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONArray type = jasonWaypoint.getJSONArray("types");
                Log.d(Tag, "Waypoints.length= " + type.length());
                types = new String[type.length()];
                for (int j = 0; j < type.length(); j++) {
                    types[j] = type.getJSONObject(j).toString();
                    Log.d(Tag, types[j]);
                }
            }
// parse Routes information
            JSONArray jasonRoutes = jsonData.getJSONArray("routes");
            Log.d(Tag, "jasonRoutes length= " + jasonRoutes.length());
            Route[] routs = new Route[jasonRoutes.length()];
            String[] warnings;
            int[] waypointOrder;
            for (int i = 0; i < jasonRoutes.length(); i++) {
                JSONObject jasonRoute = jasonRoutes.getJSONObject(i);
// parse Routes----Bounds information
                routs[i].bounds.northeast = new LatLng(jasonRoute.getJSONObject("northeast").getDouble("lat"),
                        jasonRoute.getJSONObject("southwest").getDouble("lng"));
                routs[i].bounds.northeast = new LatLng(jasonRoute.getJSONObject("southwest").getDouble("lat"),
                        jasonRoute.getJSONObject("southwest").getDouble("lng"));
// parse Routes----copyrights information
                routs[i].copyrights = jasonRoute.getString("copyrights");
// parse Routes----warnings information
                try {
                    JSONArray jasonRoutWarnings = jasonRoute.getJSONArray("warnings");
                    warnings = new String[jasonRoutWarnings.length()];
                    for (int j = 0; j < jasonRoutWarnings.length(); j++) {
                        routs[i].warnings[j] = jasonRoutWarnings.getJSONObject(j).toString();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
// parse Routes----summary information
                routs[i].summary = jasonRoute.getString("summary");
// parse Routes----overview_polylines information
                routs[i].overview_polyline = jasonRoute.getString("overview_polyline");
// parse Routes----fare information
                try {
                    routs[i].fare = new Fare(jasonRoute.getJSONObject("fare").getString("currency"),
                            jasonRoute.getJSONObject("fare").getString("value"),
                            jasonRoute.getJSONObject("fare").getString("text"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
// parse Routes----wayPointOrder information
                try {
                    JSONArray wayPointOrder = jasonRoute.getJSONArray("way_point_order");
                    waypointOrder = new int[wayPointOrder.length()];
                    for (int n = 0; n < wayPointOrder.length(); n++) {
                        waypointOrder[n] = wayPointOrder.getInt(n);
                    }
                    routs[i].waypoint_order = waypointOrder;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
// parse Routes----legs information
                JSONArray jasonLegs = jsonData.getJSONArray("legs");
                leg = new Leg[jasonLegs.length()];
                Step[] steps;
                for (int m = 0; m < jasonLegs.length(); m++) {
                    JSONObject jasonRouteLegs = jasonLegs.getJSONObject(m);
// parse Routes----legs----distance information
                    leg[m].distance = new Distance(jasonRouteLegs.getJSONObject("distance").getString("text"),
                            jasonRouteLegs.getJSONObject("distance").getInt("value"));
// parse Routes----legs----duration information
                    leg[m].duration = new Duration(jasonRouteLegs.getJSONObject("duration").getString("text"),
                            jasonRouteLegs.getJSONObject("duration").getInt("value"));
// parse Routes----legs----start_address information
                    leg[m].startAddress = jasonRouteLegs.getString("start_address");
// parse Routes----legs----start_location information
                    leg[m].startLocation = new LatLng(jasonRouteLegs.getJSONObject("start_location").getDouble("lat"),
                            jasonRouteLegs.getJSONObject("end_location").getDouble("lng"));
// parse Routes----legs----end_address information
                    leg[m].endAddress = jasonRouteLegs.getString("end_address");
// parse Routes----legs----end_Location information
                    leg[m].endLocation = new LatLng(jasonRouteLegs.getJSONObject("start_location").getDouble("lat"),
                            jasonRouteLegs.getJSONObject("end_location").getDouble("lng"));
// parse Routes----legs----arrival_time information
// parse Routes----legs----departure_time information
// parse Routes----legs----via_point information
// parse Routes----legs----traffic_speed_entry information
// parse Routes----legs----duration_in_traffic information
// parse Routes----legs----steps[] information
                    JSONArray jasonSteps = jasonRouteLegs.getJSONArray("steps");
                    steps = new Step[jasonSteps.length()];
                    for (int x = 0; x < jasonSteps.length(); x++) {
                        JSONObject jasonStep = jasonSteps.getJSONObject(x);
// parse Routes----legs----steps[]----distance information
                        steps[x].distance = new Distance(jasonStep.getJSONObject("distance").getString("text"),
                                jasonStep.getJSONObject("distance").getInt("value"));
// parse Routes----legs----steps[]----duration information
                        steps[x].duration = new Duration(jasonStep.getJSONObject("duration").getString("text"),
                                jasonStep.getJSONObject("duration").getInt("value"));
// parse Routes----legs----steps[]----end_location information
                        steps[x].end_location = new LatLng(jasonStep.getJSONObject("end_location").getDouble("lat"),
                                jasonStep.getJSONObject("start_location").getDouble("lng"));
// parse Routes----legs----steps[]----html_instructions information
                        steps[m].html_instructions = jasonStep.getJSONObject("html_instructions").toString();
// parse Routes----legs----steps[]----polyline information
                        steps[m].polyline = jasonStep.getJSONObject("polyline").toString();
// parse Routes----legs----steps[]----start_location information
                        steps[m].polyline = jasonStep.getJSONObject("start_location").toString();
// parse Routes----legs----steps[]----travel_mode information
                        steps[m].polyline = jasonStep.getJSONObject("travel_mode").toString();

                    }
                }

            }
        }else {
            Log.d(Tag,"google service response ( "+ responseElements.status+" )");
        }
        downloadListener.onFinishDownload(responseElements);
        Log.d(Tag,"parseJSon ----end----");
    }
}
