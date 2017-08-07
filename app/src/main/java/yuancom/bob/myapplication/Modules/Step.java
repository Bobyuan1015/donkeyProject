package yuancom.bob.myapplication.Modules;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by bobyuan on 06/08/2017.
 */

public class Step {
    public String travel_mode;
    public String html_instructions;
    // contains formatted instructions for this step, presented as an HTML text string.
    public Distance distance;
    // contains the distance covered by this step until the next step. (See the discussion of this field in Directions Legs above.) This field may be undefined if the distance is unknown.
    public Duration duration;
    // contains the typical time required to perform the step, until the next step. (See the description in Directions Legs above.) This field may be undefined if the duration is unknown.
    public LatLng start_location;
    // contains the location of the starting point of this step, as a single set of lat and lng fields.
    public LatLng end_location;
    // contains the location of the last point of this step, as a single set of lat and lng fields.
    public String polyline;
    // contains a single points object that holds an encoded polyline representation of the step. This polyline is an approximate (smoothed) path of the step.
    public Step substeps[];
    // contains detailed directions for walking or driving steps in transit directions. Substeps are only available when travel_mode is set to "transit". The inner steps array is of the same type as steps.
    public String available_transit_details;
    // contains transit specific information. This field is only returned with travel_mode is set to "transit". See Transit Details below.
}
