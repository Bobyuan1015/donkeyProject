package yuancom.bob.myapplication.Modules;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by bobyuan on 06/08/2017.
 */

public class Route {

    public String summary;
    // contains a short textual description for the route, suitable for naming and disambiguating the route from alternatives.
    public Leg[] legs;
    // contains an array which contains information about a leg of the route, between two locations within the given route. A separate leg will be present for each waypoint or destination specified. (A route with no waypoints will contain exactly one leg within the legs array.) Each leg consists of a series of steps. (See Directions Legs below.)
    public int[] waypoint_order;
    //(or <waypoint_index> in XML) contains an array indicating the order of any waypoints in the calculated route. This waypoints may be reordered if the request was passed optimize:true within its waypoints parameter.
    public String overview_polyline;
    // contains a single points object that holds an encoded polyline representation of the route. This polyline is an approximate (smoothed) path of the resulting directions.
    public Bound bounds;
    // contains the viewport bounding box of the overview_polyline.
    public String copyrights;
    // contains the copyrights text to be displayed for this route. You must handle and display this information yourself.
    public String[] warnings;
    // contains an array of warnings to be displayed when showing these directions. You must handle and display these warnings yourself.
    public Fare fare;
    // If present, contains the total fare (that is, the total ticket costs) on this route. This property is only returned for transit requests and only for routes where fare information is available for all transit legs. The information includes:

    @Override
    public String toString() {
        return  "\nsummary," + summary +
                "\nwaypoint_order[]," + (waypoint_order != null ? Arrays.toString(waypoint_order) : null) +
                "\noverview_polyline," + overview_polyline +
                "\nbounds," + bounds.toString() +
                "\ncopyrights" + copyrights +
                "\nfare," + (fare != null ? fare.toString() : null) +
                "\nwarnings[]," + (warnings != null ? Arrays.toString(warnings) : null)+
                "\nlegs," + Arrays.toString(legs);
    }

}
