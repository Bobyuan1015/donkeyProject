package yuancom.bob.myapplication.View.geographicInfo;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by bobyuan on 02/08/2017.
 */

public class TestDestinations {

    private static DestinationOperations destinationOperations;
    private static int initialFlag = -1;

    private  TestDestinations()
    {

    }
    public static TestDestinations getInstance()
    {
        destinationOperations = DestinationOperations.getInstance();
        return  new TestDestinations();
    }

    public ArrayList<Destination> getDestinationsInfo(){
        if( initialFlag == -1) {
           // destinationOperations.add(new Destination("Pool Meadow Bus Station Fairfax St, Coventry CV1 5SA", 52.410101, -1.508444));

//
            initialFlag = 1;
        }
        return destinationOperations.getmArrayDestinationList();
    }
    public ArrayList<LatLng> getLatLngInfo(){


            destinationOperations.add(new Destination("McDonald's  Arlington Business Park, Tile Hill Ln, Coventry ", 52.402868, -1.557833,"CV4 9BJ"));
            destinationOperations.add(new Destination("Asda Coventry Daventry Road Supermarket Daventry Road, Cheylesmore ", 52.393846, -1.503981,"CV3 5HN"));
            destinationOperations.add(new Destination("Lanchester Library", 52.405764, -1.500293));
            destinationOperations.add(new Destination("Coventry", 52.401044, -1.513843));
            destinationOperations.add(new Destination("Brandon Coventry", 52.384383, -1.401187));
            destinationOperations.add(new Destination("Asda Coventry ", 52.431356, -1.432177,"CV2 2QZ"));
            destinationOperations.add(new Destination("Exhall", 52.466736, -1.482156));
            destinationOperations.add(new Destination("Astley Nuneaton", 52.501116, -1.541511));
            destinationOperations.add(new Destination("Shustoke", 52.515120, -1.666948));
            destinationOperations.add(new Destination("Aldridge Walsall", 52.610501, -1.912211));
            destinationOperations.add(new Destination("Birmingham", 52.546284, -1.900845));
            destinationOperations.add(new Destination("Birmingham", 52.475385, -1.838916));
            destinationOperations.add(new Destination("Sutton Coldfield", 52.565266, -1.816496));
            destinationOperations.add(new Destination("Beacon Park Village Lower Sandford St, Lichfield ", 52.681755, -1.828010,"WS13 6JZ"));
            destinationOperations.add(new Destination("Coventry", 52.425741, -1.571099));
            destinationOperations.add(new Destination("Keresley", 52.449947, -1.531392));
            destinationOperations.add(new Destination("Wolston", 52.373191, -1.408356));
        return destinationOperations.getmArrayLatLngList();
    }

    public void addDestination( Destination desination )
    {
        if( desination != null )
            destinationOperations.add(desination);

    }

    public void removeDestination( Destination desination )
    {
        if( desination != null )
            destinationOperations.remove(desination);

    }
    public void removeDestination( int index  )
    {
            destinationOperations.remove(index);

    }

}
