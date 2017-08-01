package yuancom.bob.myapplication.geographicInfo;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by bob on 25/07/2017.
 */

public class PlaceOperations {
    private static ArrayList<Place> mArrayPlaceList;


    /**
     Private Constructor, it means users cannot allow to new this object except for getInstance()
     */
    private PlaceOperations()
    {

    }
    /** Single instance class, this method can return a object of PlaceOperations
     * @return the object of PlaceOperations.
     */
    public static PlaceOperations getInstance(){
        if( mArrayPlaceList == null )
            mArrayPlaceList = new ArrayList<Place>();
        return new PlaceOperations() ;
    }

    public ArrayList<Place> getmArrayPlaceList(){
        return mArrayPlaceList;
    }

    /**
     * Add a new Place to the list, if not already there
     * @param  place, the Place to add
     * @return  true if successful, else false
     * @throws NullPointerException if Place is null
     */
    public boolean add(Place place) throws NullPointerException{
        if ( place == null )
        {
            throw new NullPointerException("The Place is empty, data invalid");
        }
        else
        {
            mArrayPlaceList.add( place );
        }
        return true;

    }
    // POST: if Place is null the exception is thrown; else
    // the Place is added to our list and true is returned,
    // or false if the Place was not added for some reason


    /**
     * Remove an Place from the list
     * @param place, the Place to remove
     * @return  true if successful, else false
     * @throws NullPointerException if Place is null
     */

    public boolean remove(Place place) throws NullPointerException
    {

        if ( place == null )
        {
            throw new NullPointerException("The place is empty, data invalid");
        }
        if(mArrayPlaceList == null )
        {
            throw new NullPointerException("The Placelist is empty, data invalid");
        }

        Iterator<Place> iter = mArrayPlaceList.iterator();
        while (iter.hasNext()) {
            Place interatorOfPlace = iter.next();

            if (interatorOfPlace.equals(place ))
            {
                iter.remove();
                return true;
            }
        }
        return false;
    }
    // POST: if string is null the exception is thrown; otherwise
    // if the Place is not in our list false is returned, else
    // matching instance of the Place is removed and true is returned




    /**
     * Get the size of the list (number of Place)
     * @return  the list size (= number of Place)
     */
    public int size()
    {
        return mArrayPlaceList.size();
    }



    /**
     * a string is returned of the form “S0\nS1\nS2\n...”
     * @return a printable string formatted as above
     */
    public String toString(){
        String str = null;
        for( Place interatorOfPlace : mArrayPlaceList )
        {
            str += interatorOfPlace.toString() +"\n";
        }
        return str;
    }





}
