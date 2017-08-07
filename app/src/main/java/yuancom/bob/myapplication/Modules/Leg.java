package yuancom.bob.myapplication.Modules;

import org.joda.time.DateTime;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by bobyuan on 06/08/2017.
 */

public class Leg {


    /**
     * {@code steps[]} contains an array of steps denoting information about each separate step of the
     * leg of the journey.
     */
    public Step[] steps;

    /** {@code distance} indicates the total distance covered by this leg. */
    public Distance distance;

    /** {@code duration} indicates the total duration of this leg */
    public Duration duration;

    /**
     * {@code durationInTraffic} indicates the total duration of this leg, taking into account current
     * traffic conditions. The duration in traffic will only be returned if all of the following are
     * true:
     *
     * <ol>
     *   <li>The directions request includes a departureTime parameter set to a value within a few
     *       minutes of the current time.
     *   <li>The request includes a valid Maps for Work client and signature parameter.
     *   <li>Traffic conditions are available for the requested route.
     *   <li>The directions request does not include stopover waypoints.
     * </ol>
     */
    public Duration durationInTraffic;

    /**
     * {@code arrivalTime} contains the estimated time of arrival for this leg. This property is only
     * returned for transit directions.
     */
    public DateTime arrivalTime;

    /**
     * {@code departureTime} contains the estimated time of departure for this leg. The departureTime
     * is only available for transit directions.
     */
    public DateTime departureTime;

    /**
     * {@code startLocation} contains the latitude/longitude coordinates of the origin of this leg.
     * Because the Directions API calculates directions between locations by using the nearest
     * transportation option (usually a road) at the start and end points, startLocation may be
     * different than the provided origin of this leg if, for example, a road is not near the origin.
     */
    public LatLng startLocation;

    /**
     * {@code endLocation} contains the latitude/longitude coordinates of the given destination of
     * this leg. Because the Directions API calculates directions between locations by using the
     * nearest transportation option (usually a road) at the start and end points, endLocation may be
     * different than the provided destination of this leg if, for example, a road is not near the
     * destination.
     */
    public LatLng endLocation;

    /**
     * {@code startAddress} contains the human-readable address (typically a street address)
     * reflecting the start location of this leg.
     */
    public String startAddress;

    /**
     * {@code endAddress} contains the human-readable address (typically a street address) reflecting
     * the end location of this leg.
     */
    public String endAddress;


}



