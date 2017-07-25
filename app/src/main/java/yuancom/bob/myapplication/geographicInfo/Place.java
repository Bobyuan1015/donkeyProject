package yuancom.bob.myapplication.geographicInfo;

/**
 * Created by bob on 25/07/2017.
 */

public class Place {

    private String mPlaceName;
    private double mLongitude;
    private double mLatitude;

    public Place(String name, double longitude, double latitude){
        this.mPlaceName = name;
        this.mLongitude = longitude;
        this.mLatitude = latitude;
    }
    public String getName()
    {
        return mPlaceName;
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
        if(!(obj instanceof Place)) return false;


        if ( mPlaceName.equals(((Place)obj).getName() )
                &&( mLongitude == ((Place)obj).geLatitude())
                && ( mLongitude == ((Place)obj).getLongitude()))
            return true;

        return false ;
    }

    public String toString()
    {
        return mPlaceName + " ( " + mLongitude + " , " + mLatitude + " )";
    }

}
