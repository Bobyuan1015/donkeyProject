package yuancom.bob.myapplication.geographicInfo;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by bob on 25/07/2017.
 */

public class DestinationOperations {
    private static ArrayList<Destination> mArrayDestinationList;


    /**
     Private Constructor, it means users cannot allow to new this object except for getInstance()
     */
    private DestinationOperations()
    {

    }
    /** Single instance class, this method can return a object of DestinationOperations
     * @return the object of DestinationOperations.
     */
    public static DestinationOperations getInstance(){
        if( mArrayDestinationList == null )
            mArrayDestinationList = new ArrayList<Destination>();
        return new DestinationOperations() ;
    }

    public ArrayList<Destination> getmArrayDestinationList(){
        return mArrayDestinationList;
    }

    /**
     * Add a new Destination to the list, if not already there
     * @param  Destination, the Destination to add
     * @return  true if successful, else false
     * @throws NullPointerException if Destination is null
     */
    public boolean add(Destination Destination) throws NullPointerException{
        if ( Destination == null )
        {
            throw new NullPointerException("The Destination is empty, data invalid");
        }
        else
        {
            mArrayDestinationList.add( Destination );
        }
        return true;

    }
    // POST: if Destination is null the exception is thrown; else
    // the Destination is added to our list and true is returned,
    // or false if the Destination was not added for some reason


    /**
     * Remove an Destination from the list
     * @param Destination, the Destination to remove
     * @return  true if successful, else false
     * @throws NullPointerException if Destination is null
     */

    public boolean remove(Destination Destination) throws NullPointerException
    {

        if ( Destination == null )
        {
            throw new NullPointerException("The Destination is empty, data invalid");
        }
        if(mArrayDestinationList == null )
        {
            throw new NullPointerException("The Destinationlist is empty, data invalid");
        }

        Iterator<Destination> iter = mArrayDestinationList.iterator();
        while (iter.hasNext()) {
            Destination interatorOfDestination = iter.next();

            if (interatorOfDestination.equals(Destination ))
            {
                iter.remove();
                return true;
            }
        }
        return false;
    }
    // POST: if string is null the exception is thrown; otherwise
    // if the Destination is not in our list false is returned, else
    // matching instance of the Destination is removed and true is returned




    /**
     * Get the size of the list (number of Destination)
     * @return  the list size (= number of Destination)
     */
    public int size()
    {
        return mArrayDestinationList.size();
    }



    /**
     * a string is returned of the form “S0\nS1\nS2\n...”
     * @return a printable string formatted as above
     */
    public String toString(){
        String str = null;
        for( Destination interatorOfDestination : mArrayDestinationList )
        {
            str += interatorOfDestination.toString() +"\n";
        }
        return str;
    }





}
