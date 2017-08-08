package yuancom.bob.myapplication.Modules;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.PolyUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bobyuan on 06/08/2017.
 */

public class BackEndSession extends AsyncTask<String, Void, String>{

    final String Tag = "BackEndSession";
    private DownloadListener downloadListener;
    public BackEndSession (DownloadListener downloadListener){
        this.downloadListener = downloadListener;
    }

    @Override
    protected String doInBackground(String... params) {
        String link = params[0];
        Log.d(Tag,"doInBackground param[0]="+link);
        try {
            URL url = new URL(link);
            InputStream is = url.openConnection().getInputStream();
            StringBuffer buffer = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
                buffer.append(line );
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
        Log.d(Tag,"onPostExecute, res="+res);
        try {
            parseJSon(res);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private void parseJSon(String data) throws JSONException {
        Log.d(Tag,"parseJSon, data="+data);
        if (data == null)
            return;

        List<Route> routes = new ArrayList<Route>();
        JSONObject jsonData = new JSONObject(data);
        JSONArray jsonRoutes = jsonData.getJSONArray("routes");

        ArrayList<LatLng> overlayPolyline = new ArrayList<LatLng>();
        for (int i = 0; i < jsonRoutes.length(); i++) {
            JSONObject jsonRoute = jsonRoutes.getJSONObject(i);
            Route route = new Route();

            JSONObject overview_polylineJson = jsonRoute.getJSONObject("overview_polyline");
            JSONArray jsonLegs = jsonRoute.getJSONArray("legs");

            ArrayList <Object> objectlegs=  ArrayJasonUtil.convert(jsonLegs);
            JSONObject jsonLeg = jsonLegs.getJSONObject(0);
            JSONObject jsonDistance = jsonLeg.getJSONObject("distance");
            JSONObject jsonDuration = jsonLeg.getJSONObject("duration");
            JSONObject jsonEndLocation = jsonLeg.getJSONObject("end_location");
            JSONObject jsonStartLocation = jsonLeg.getJSONObject("start_location");

//            route.legs. = new Distance(jsonDistance.getString("text"), jsonDistance.getInt("value"));
//            route.duration = new Duration(jsonDuration.getString("text"), jsonDuration.getInt("value"));
//            route.endAddress = jsonLeg.getString("end_address");
//            route.startAddress = jsonLeg.getString("start_address");
//            route.startLocation = new LatLng(jsonStartLocation.getDouble("lat"), jsonStartLocation.getDouble("lng"));
//            route.endLocation = new LatLng(jsonEndLocation.getDouble("lat"), jsonEndLocation.getDouble("lng"));
//
//            overlayPolyline = PolyUtil.decode(overview_polylineJson.getString("points");
//
//            routes.add(route);
        }
        JSONArray jasonWaypoints = jsonData.getJSONArray("geocoded_waypoints");
        for (int i = 0; i < jasonWaypoints.length(); i++) {
            JSONObject jasonWaypoint = jasonWaypoints.getJSONObject(i);

        }

        JSONArray jasonAvailableTravelModes = jsonData.getJSONArray("available_travel_modes");
        for (int i = 0; i < jasonAvailableTravelModes.length(); i++) {
            JSONObject jasonAvailableTravelMode = jasonAvailableTravelModes.getJSONObject(i);

        }
        JSONObject jasonStatus= jsonData.getJSONObject("status");


            ResponseElements responseContent = new ResponseElements();

        downloadListener.onFinishDownload(responseContent);
    }
}
