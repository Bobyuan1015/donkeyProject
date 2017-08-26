package yuancom.bob.myapplication.Modules;


import java.util.Arrays;

/**
 * Created by bobyuan on 06/08/2017.
 */

public class GeocodedWaypoint {
    public String geocoder_status;
    public boolean partial_match;
    public String place_id;
    public String types[];

    @Override
    public String toString() {
        return "status,"+geocoder_status+" partua_match,"+ partial_match+" place_id,"+place_id+" type[],"+ Arrays.toString(types);
    }
}
