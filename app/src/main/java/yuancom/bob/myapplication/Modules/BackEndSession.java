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
//        File file = new File("/sdcard/donckeydata");
//        file.mkdir();
//        if (!file.exists()) {
//            if (file.mkdir()) {
//                Log.d(Tag,"Directory is created!");
//            } else {
//                Log.d(Tag,"Failed to create directory!");
//            }
//        }


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
        responseElements = null;
        String status;
        GeocodedWaypoint[] geocodedWaypoints;
        Route[] routs;
        String[] available_travel_modes = null ;

        JSONObject jsonData = new JSONObject(data);
        status = jsonData.getString("status");

        if( status.equals("OK")) {
// parse available_travel_modes information
            try {
                JSONArray jasonTravelModes = jsonData.getJSONArray("available_travel_modes");
                available_travel_modes = new String[jasonTravelModes.length()];
                for (int i =0; i< jasonTravelModes.length();i++)
                {
                    available_travel_modes[i] = jasonTravelModes.getJSONObject(i).toString();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
// parse WayPoints information
            JSONArray jasonWaypoints = jsonData.getJSONArray("geocoded_waypoints");
            Log.d(Tag, "Waypoints length= " + jasonWaypoints.length());
            geocodedWaypoints= new GeocodedWaypoint[jasonWaypoints.length()];
            String[] types;
            Leg leg[];
            for (int i = 0; i < jasonWaypoints.length(); i++) {
                JSONObject jasonWaypoint = jasonWaypoints.getJSONObject(i);
                String geocoder_status = jasonWaypoint.getString("geocoder_status");
                String place_id = jasonWaypoint.getString("place_id");
                try {
                    geocodedWaypoints[i].partial_match = jasonWaypoint.getBoolean("partial_match");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONArray type = jasonWaypoint.getJSONArray("types");
                Log.d(Tag, "Waypoints.length= " + type.length());
                types = new String[type.length()];
                for (int j = 0; j < type.length(); j++) {
                    types[j] = type.getString(j);
                    Log.d(Tag, types[j]);
                }
                GeocodedWaypoint geocodedWaypointTem = new GeocodedWaypoint();
                geocodedWaypointTem.geocoder_status = geocoder_status;
                geocodedWaypointTem.place_id = place_id;
                geocodedWaypointTem.types = types;
                geocodedWaypoints[i] = geocodedWaypointTem;
//                geocodedWaypoints[i].geocoder_status = geocoder_status;
//                geocodedWaypoints[i].place_id = place_id;
//                geocodedWaypoints[i].types = types;
            }
// parse Routes information
            JSONArray jasonRoutes = jsonData.getJSONArray("routes");
            Log.d(Tag, "jasonRoutes length= " + jasonRoutes.length());
            routs = new Route[jasonRoutes.length()];
            String[] warnings;
            int[] waypointOrder;
            for (int i = 0; i < jasonRoutes.length(); i++) {
                JSONObject jasonRoute = jasonRoutes.getJSONObject(i);
                Route routstemp = new Route();
// parse Routes----Bounds information
                Bound boundTmp = new Bound();
                Log.d(Tag,"northeast");
                Log.d(Tag," 1lag="+jasonRoute.getJSONObject("bounds").getJSONObject("northeast").getDouble("lat"));
                Log.d(Tag," 1lng"+jasonRoute.getJSONObject("bounds").getJSONObject("northeast").getDouble("lng"));
                boundTmp.northeast = new LatLng(jasonRoute.getJSONObject("bounds").getJSONObject("northeast").getDouble("lat"),
                        jasonRoute.getJSONObject("bounds").getJSONObject("northeast").getDouble("lng"));
                boundTmp.southwest = new LatLng(jasonRoute.getJSONObject("bounds").getJSONObject("southwest").getDouble("lat"),
                        jasonRoute.getJSONObject("bounds").getJSONObject("southwest").getDouble("lng"));
                routstemp.bounds = boundTmp;
//                routs[i].bounds.northeast = new LatLng(jasonRoute.getJSONObject("bounds").getJSONObject("northeast").getDouble("lat"),
//                        jasonRoute.getJSONObject("bounds").getJSONObject("northeast").getDouble("lng"));
//                routs[i].bounds.northeast = new LatLng(jasonRoute.getJSONObject("bounds").getJSONObject("southwest").getDouble("lat"),
//                        jasonRoute.getJSONObject("bounds").getJSONObject("southwest").getDouble("lng"));
// parse Routes----copyrights information
                routstemp.copyrights = jasonRoute.getString("copyrights");
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
                routstemp.summary = jasonRoute.getString("summary");
// parse Routes----overview_polylines information
                routstemp.overview_polyline = jasonRoute.getString("overview_polyline");
// parse Routes----fare information
                try {
                    routstemp.fare = new Fare(jasonRoute.getJSONObject("fare").getString("currency"),
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
                    routstemp.waypoint_order = waypointOrder;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
// parse Routes----legs information
                JSONArray jasonLegs = jasonRoute.getJSONArray("legs");
                leg = new Leg[jasonLegs.length()];

                for (int m = 0; m < jasonLegs.length(); m++) {
                    JSONObject jasonRouteLegs = jasonLegs.getJSONObject(m);
                    Leg legtemp = new Leg();
// parse Routes----legs----distance information
                    legtemp.distance = new Distance(jasonRouteLegs.getJSONObject("distance").getString("text"),
                            jasonRouteLegs.getJSONObject("distance").getInt("value"));
// parse Routes----legs----duration information
                    legtemp.duration = new Duration(jasonRouteLegs.getJSONObject("duration").getString("text"),
                            jasonRouteLegs.getJSONObject("duration").getInt("value"));
// parse Routes----legs----start_address information
                    legtemp.startAddress = jasonRouteLegs.getString("start_address");
// parse Routes----legs----start_location information
                    legtemp.startLocation = new LatLng(jasonRouteLegs.getJSONObject("start_location").getDouble("lat"),
                            jasonRouteLegs.getJSONObject("end_location").getDouble("lng"));
// parse Routes----legs----end_address information
                    legtemp.endAddress = jasonRouteLegs.getString("end_address");
// parse Routes----legs----end_Location information
                    legtemp.endLocation = new LatLng(jasonRouteLegs.getJSONObject("start_location").getDouble("lat"),
                            jasonRouteLegs.getJSONObject("end_location").getDouble("lng"));
                    leg[m] = legtemp;

// parse Routes----legs----arrival_time information
// parse Routes----legs----departure_time information
// parse Routes----legs----via_point information
// parse Routes----legs----traffic_speed_entry information
// parse Routes----legs----duration_in_traffic information
// parse Routes----legs----steps[] information
                    JSONArray jasonSteps = jasonRouteLegs.getJSONArray("steps");
                    Step[] steps = new Step[jasonSteps.length()];
                    for (int x = 0; x < jasonSteps.length(); x++) {
                        JSONObject jasonStep = jasonSteps.getJSONObject(x);
                        Step steptemp = new Step();
// parse Routes----legs----steps[]----distance information
                        steptemp.distance = new Distance(jasonStep.getJSONObject("distance").getString("text"),
                                jasonStep.getJSONObject("distance").getInt("value"));
// parse Routes----legs----steps[]----duration information
                        steptemp.duration = new Duration(jasonStep.getJSONObject("duration").getString("text"),
                                jasonStep.getJSONObject("duration").getInt("value"));
// parse Routes----legs----steps[]----end_location information
                        steptemp.end_location = new LatLng(jasonStep.getJSONObject("end_location").getDouble("lat"),
                                jasonStep.getJSONObject("start_location").getDouble("lng"));
// parse Routes----legs----steps[]----html_instructions information
                        steptemp.html_instructions = jasonStep.getString("html_instructions");
// parse Routes----legs----steps[]----polyline information
                        steptemp.polyline = jasonStep.getJSONObject("polyline").getString("points");
// parse Routes----legs----steps[]----start_location information
                        steptemp.start_location = new LatLng(jasonStep.getJSONObject("start_location").getDouble("lat"),
                                jasonStep.getJSONObject("start_location").getDouble("lng"));
// parse Routes----legs----steps[]----travel_mode information
                        steptemp.travel_mode = jasonStep.getString("travel_mode");
                        steps[x] = steptemp;
                    }
                    leg[m].steps = steps;
                }
                routstemp.legs = leg;
                routs[i] = routstemp;
            }
            responseElements = new ResponseElements( status, geocodedWaypoints, routs, available_travel_modes);

        }else {
            Log.d(Tag,"google service response ( "+ responseElements.status+" )");
        }

        downloadListener.onFinishDownload(responseElements);
        Log.d(Tag,"parseJSon ----end----");

    }
}
