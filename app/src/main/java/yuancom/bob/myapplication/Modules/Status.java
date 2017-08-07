package yuancom.bob.myapplication.Modules;

/**
 * Created by bobyuan on 06/08/2017.
 */

public class Status {
    public String statusCode;

    public final String OK = "OK";//indicates the response contains a valid result.
    public final String NOT_FOUND = "NOT_FOUND";// indicates at least one of the locations specified in the request's origin, destination, or waypoints could not be geocoded.
    public final String ZERO_RESULTS = "ZERO_RESULTS";// indicates no route could be found between the origin and destination.
    public final String MAX_WAYPOINTS_EXCEEDED = "MAX_WAYPOINTS_EXCEEDED";// indicates that too many waypoints were provided in the request. For applications using the Google Maps Directions API as a web service, or the directions service in the Google Maps JavaScript API, the maximum allowed number of waypoints is 23, plus the origin and destination. Google Maps APIs Premium Plan customers may submit requests with up to 23 waypoints, plus the origin and destination.
    public final String MAX_ROUTE_LENGTH_EXCEEDED = "MAX_ROUTE_LENGTH_EXCEEDED";// indicates the requested route is too long and cannot be processed.
    public final String INVALID_REQUEST = "INVALID_REQUEST";// indicates that the provided request was invalid. Common causes of this status include an invalid parameter or parameter value.
    public final String OVER_QUERY_LIMIT = "OVER_QUERY_LIMIT";// indicates the service has received too many requests from your application within the allowed time period.
    public final String REQUEST_DENIED = "REQUEST_DENIED";// indicates that the service denied use of the directions service by your application.
    public final String UNKNOWN_ERROR  = "UNKNOWN_ERROR";//indicates a directions request could not be processed due to a server error. The request may succeed if you try again.
}
