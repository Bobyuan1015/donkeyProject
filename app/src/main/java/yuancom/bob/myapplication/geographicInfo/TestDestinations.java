package yuancom.bob.myapplication.geographicInfo;

import java.util.ArrayList;

/**
 * Created by bobyuan on 02/08/2017.
 */

public class TestDestinations {
    public static ArrayList<Place> getDestinationsInfo(){
        PlaceOperations placeOperations = PlaceOperations.getInstance();
        placeOperations.add( new Place("a",-1,-2));
        placeOperations.add( new Place("a",-1,-2));
        placeOperations.add( new Place("a",-1,-2));
        placeOperations.add( new Place("a",-1,-2));
        placeOperations.add( new Place("a",-1,-2));
        placeOperations.add( new Place("a",-1,-2));
        placeOperations.add( new Place("a",-1,-2));
        placeOperations.add( new Place("a",-1,-2));
        placeOperations.add( new Place("a",-1,-2));
        placeOperations.add( new Place("a",-1,-2));
        placeOperations.add( new Place("a",-1,-2));
        placeOperations.add( new Place("a",-1,-2));
        placeOperations.add( new Place("a",-1,-2));
        placeOperations.add( new Place("a",-1,-2));
        placeOperations.add( new Place("a",-1,-2));
        placeOperations.add( new Place("a",-1,-2));
        placeOperations.add( new Place("a",-1,-2));
        placeOperations.add( new Place("a",-1,-2));
        return placeOperations.getmArrayPlaceList();
    }
}
