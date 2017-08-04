package yuancom.bob.myapplication.geographicInfo;

import android.app.Activity;
import android.util.Log;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import yuancom.bob.myapplication.R;

/**
 * Created by bob on 04/08/2017.
 */

public class DestinationTransation {

    final String Tag = "DestinationTransation";

    public static DestinationTransation getInstance(){
        return new DestinationTransation() ;
    }

    public Destination findDestination(Activity context,double latitude , double lonitude)
    {
        return null;
    }
    public Destination findDestination(Activity context, String address)
    {
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                context.getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        Log.i(Tag, "findDestination");
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(Tag, "Place: " + place.getName());

                String placeDetailsStr = place.getName() + "\n"
                        + place.getId() + "\n"
                        + place.getLatLng().toString() + "\n"
                        + place.getAddress() + "\n"
                        + place.getAttributions();
              //  txtPlaceDetails.setText(placeDetailsStr);
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(Tag, "An error occurred: " + status);
            }
        });
        return null;
    }
}
