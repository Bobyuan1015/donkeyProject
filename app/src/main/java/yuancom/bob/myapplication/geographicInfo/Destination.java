package yuancom.bob.myapplication.geographicInfo;



/**
 * Created by bob on 25/07/2017.
 */

public class Destination {

    private String mDestinationName;
    private double mLongitude;
    private double mLatitude;


    public Destination(String name, double longitude, double latitude){
        this.mDestinationName = name;
        this.mLongitude = longitude;
        this.mLatitude = latitude;
    }
    public String getName()
    {
        return mDestinationName;
    }
    public double getLongitude()
    {
        return mLongitude;
    }
    public double geLatitude(){
        return mLatitude;
    }

    /**
     * Override the default equals(), based on registrations
     * @param obj  the other Object
     * @return  true if obj has same registration, else false
     */
    public boolean equals(Object obj)
    {
        if(!(obj instanceof Destination)) return false;


        if ( mDestinationName.equals(((Destination)obj).getName() )
                &&( mLatitude == ((Destination)obj).geLatitude())
                && ( mLongitude == ((Destination)obj).getLongitude()))
            return true;

        return false ;
    }

    public String toString()
    {
        return mDestinationName + " ( " + mLongitude + " , " + mLatitude + " )";
    }

}
