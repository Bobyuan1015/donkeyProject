package yuancom.bob.myapplication;

import android.Manifest;

import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.ui.IconGenerator;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


import yuancom.bob.myapplication.Modules.BackEndSession;
import yuancom.bob.myapplication.Modules.DownloadListener;
import yuancom.bob.myapplication.Modules.Leg;
import yuancom.bob.myapplication.Modules.RequestUrlBuilder;
import yuancom.bob.myapplication.Modules.ResponseElements;
import yuancom.bob.myapplication.Modules.Route;
import yuancom.bob.myapplication.Modules.Step;
import yuancom.bob.myapplication.geographicInfo.AddDestinationFragment;

import yuancom.bob.myapplication.geographicInfo.Destination;
import yuancom.bob.myapplication.geographicInfo.DestinationsFragment;
import yuancom.bob.myapplication.geographicInfo.TestDestinations;


public class MainActivity extends AppCompatActivity
        implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener,DownloadListener {

    final String Tag = "MainActivity";
    private GoogleMap mMap;
    private MapFragment mapFragment;
    private static final int GENPATH = 2;
    private static final int SETMARKER = 1;
    private int mapflage = -1;
    private Route[] routes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(Tag, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mapFragment = MapFragment.newInstance();
        getFragmentManager().beginTransaction().add(R.id.mainfragment, mapFragment).commit();
    }

    @Override
    public void onBackPressed() {
        Log.d(Tag, "onBackPressed");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            getFragmentManager().popBackStack();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(Tag, "onCreateOptionsMenu");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        //MenuItem searchItem = menu.findItem(R.id.search_Destination);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Log.d(Tag, "onOptionsItemSelected  id=" + id);
        //noinspection SimplifiableIfStatement
//        if (id == R.id.search_Destination) {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        // getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.mainfragment)).commit();

        if (id == R.id.login) {
            Log.d(Tag, "onNavigationItemSelected  login3");
            fragmentTransaction.replace(R.id.mainfragment, mapFragment);
            fragmentTransaction.addToBackStack("1");
            mapFragment.getMapAsync(this);
            mapflage = SETMARKER;

        } else if (id == R.id.DestinationsList) {

            Log.d(Tag, "onNavigationItemSelected  DestinationsList");
            DestinationsFragment Destinationsfragment = DestinationsFragment.newInstance("", "");
            fragmentTransaction.replace(R.id.mainfragment, Destinationsfragment);
            fragmentTransaction.addToBackStack("1");
            mapflage = 0;

        } else if (id == R.id.addDestination) {

            Log.d(Tag, "onNavigationItemSelected  addDestination");
            AddDestinationFragment addDestinationFragment = AddDestinationFragment.newInstance("", "");
            fragmentTransaction.replace(R.id.mainfragment, addDestinationFragment);
            fragmentTransaction.addToBackStack("1");
            mapflage = 0;


        } else if (id == R.id.getPath) {
            Log.d(Tag, "onNavigationItemSelected  getPath");
            fragmentTransaction.replace(R.id.mainfragment, mapFragment);
            fragmentTransaction.addToBackStack("1");
            mapFragment.getMapAsync(this);
            mapflage = GENPATH;
        }
        fragmentTransaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void moveCameraCenter( List<LatLng> latLngs) {
//        Log.d(Tag, "moveCameraCenter: ");
//        LatLngBounds.Builder boundsBuilder = LatLngBounds.builder();
//
//        TestDestinations.getInstance().getDestinationsInfo();
//
//        ArrayList<LatLng> destinationLatLngs = TestDestinations.getInstance().getLatLngInfo();
//
//        Iterator<LatLng> iterLatLng = destinationLatLngs.iterator();
//
//        while (iterLatLng.hasNext()) {
//            LatLng address = iterLatLng.next();
//            boundsBuilder.include(address);
//            // Log.d(Tag,address.toString());
//        }
//
//        // Move camera to show all markers and locations
//        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 50));

        Log.d(Tag, "moveCameraCenter ");
        LatLngBounds.Builder boundsBuilder = LatLngBounds.builder();

       for(LatLng coordinate: latLngs)
            boundsBuilder.include(coordinate);

        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 50));
    }

    public void setMarker() {
        Log.d(Tag, "setMarker");
        if (mMap != null) {

//            //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
//                    new LatLng(-18.142, 178.431), 2));

            ArrayList<LatLng> destinationLatLngs = TestDestinations.getInstance().getLatLngInfo();

            Iterator<LatLng> iterLatLng = destinationLatLngs.iterator();

            while (iterLatLng.hasNext()) {
                LatLng address = iterLatLng.next();
                mMap.addMarker(new MarkerOptions()
                        .position(address)
                        .title("target")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            }

        }

    }

    public void setPath() throws UnsupportedEncodingException{
        Log.d(Tag, "setPath");


        TestDestinations.getInstance().getDestinationsInfo();
        ArrayList<LatLng> testlatLngs = TestDestinations.getInstance().getLatLngInfo();


        RequestUrlBuilder requestUrlBuilder = new RequestUrlBuilder();
        BackEndSession backEndSession = new BackEndSession(this);
        backEndSession.execute(requestUrlBuilder.urlCreator(new LatLng(52.410101, -1.508444),new LatLng(52.410101, -1.508444),testlatLngs));

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(Tag, "onMapReady 1mapflage=" + mapflage);
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            Log.d(Tag, "onMapReady setMyLocationEnabled" );
            mMap.setMyLocationEnabled(true);
        }
        switch ( mapflage ){
            case SETMARKER:   break;
            case GENPATH:
                try {
                    setPath();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
            default:
                Log.d(Tag,"Nothing to do on the map");
        }



//        moveCameraCenter();
//        switch ( mapflage ){
//            case SETMARKER:  setMarker(); break;
//            case GENPATH:  setPath(); break;
//            default:
//                Log.d(Tag,"Nothing to do on the map");
//        }







//        LatLng mapCenter = new LatLng(41.889, -87.622);
//
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mapCenter, 13));
//
//        // Flat markers will rotate when the map is rotated,
//        // and change perspective when the map is tilted.
//        mMap.addMarker(new MarkerOptions()
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.navigationsymbol))
//                .position(mapCenter)
//                .flat(true)
//                .rotation(0));
//
//        CameraPosition cameraPosition = CameraPosition.builder()
//                .target(new LatLng(41.889, -87.632))
//                .zoom(18)
//                .bearing(50)
//                .build();//
//
//        // Animate the change in camera view over 2 seconds
//        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),
//                2000, null);


    }


    @Override
    public void onStartDownload() {

    }

    @Override
    public void onFinishDownload(ResponseElements responseElements) {
        Log.d(Tag,"responseElements:");
        routes = responseElements.routs;
        Log.d(Tag,responseElements.toString());
//test whether decodePolyLine() can work or not ,the testdata from https://developers.google.com/maps/documentation/utilities/polylinealgorithm#example
//        List<LatLng> decod = decodePolyLine("_p~iF~ps|U_ulLnnqC_mqNvxq`@");
//        Log.d(Tag,"dec="+ Arrays.toString(decod.toArray()));
//        String decodedPolylines = "";
//        PolylineOptions polylineOptions = new PolylineOptions();
        List<LatLng> line = new ArrayList<LatLng>();
        int distanceLeg = 0;
        int legNum = -1;
        for ( Route route : responseElements.routs)
        {
            for( Leg leg : route.legs ){
                distanceLeg = leg.distance.value;
                if( legNum > -1 && legNum < route.legs.length )
                {
                    int wayPointorderWithStart = -1;
                    if (legNum == 0)
                        wayPointorderWithStart = 0;
                    else
                        wayPointorderWithStart = route.waypoint_order[legNum -1 ]+1;
                    Log.d(Tag,"wayPointorderWithStart"+wayPointorderWithStart);
                    mMap.addMarker(new MarkerOptions()
                            .position(leg.startLocation)
                            .snippet(Integer.toString(distanceLeg)+"m")
                            .icon(BitmapDescriptorFactory.
                                    fromBitmap(
                                            new IconGenerator(this).
                                                    makeIcon(Integer.
                                                            toString(wayPointorderWithStart)))));
                }
                legNum++;
                int stepnumber = 0;
                for(Step step : leg.steps)
                {
                    try {
//                        decodedPolylines = step.polyline; // errr,   an encodedPolylines can't be combined by multiple single polyline
                      //  Log.d(Tag, "polyline = " + step.polyline);

                        ArrayList<LatLng> stepPoints = (ArrayList<LatLng>) decodePolyLine(step.polyline);
                        line.addAll(stepPoints);
                        Log.d(Tag," stepnumber="+stepnumber+"Color.BLACK+stepnumber*0xFF="+Color.BLACK+stepnumber*0xFF);
                        mMap.addPolyline(new PolylineOptions()
                                .addAll(stepPoints)
                                .color(0xFF000000+stepnumber*0xFF));
                        stepnumber++;
                       // Thread.sleep(2000);
                    }catch ( StringIndexOutOfBoundsException e){
                        e.printStackTrace();
                    } /*catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                }
            }
        }
        Log.d(Tag,"distanceLeg"+distanceLeg);
//            mMap.addPolyline(new PolylineOptions()
//                    .addAll(line)
//                    .color(Color.BLUE));
            moveCameraCenter(line);


    }
    private List<LatLng> decodePolyLine(final String poly) {
        int len = poly.length();
        int index = 0;
        List<LatLng> decoded = new ArrayList<LatLng>();
        int lat = 0;
        int lng = 0;

        while (index < len) {
            int b;
            int shift = 0;
            int result = 0;
            do {
                b = poly.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = poly.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            decoded.add(new LatLng(
                    lat / 100000d, lng / 100000d
            ));
        }
      //  Log.d(Tag, "decoded = " + decoded);
        return decoded;
    }

}



