package yuancom.bob.myapplication;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.Iterator;

import yuancom.bob.myapplication.geographicInfo.AddDestinationFragment;

import yuancom.bob.myapplication.geographicInfo.DestinationsFragment;
import yuancom.bob.myapplication.geographicInfo.TestDestinations;


public class MainActivity extends AppCompatActivity
        implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener {

    final String Tag = "MainActivity";
    private GoogleMap mMap;
    private MapFragment mapFragment;
    private static final int GENPATH = 2;
    private static final int SETMARKER = 1;
    private int mapflage = -1;


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

    public void moveCameraCenter() {
        Log.d(Tag, "moveCameraCenter: ");
        LatLngBounds.Builder boundsBuilder = LatLngBounds.builder();

        TestDestinations.getInstance().getDestinationsInfo();

        ArrayList<LatLng> destinationLatLngs = TestDestinations.getInstance().getLatLngInfo();

        Iterator<LatLng> iterLatLng = destinationLatLngs.iterator();

        while (iterLatLng.hasNext()) {
            LatLng address = iterLatLng.next();
            boundsBuilder.include(address);
            // Log.d(Tag,address.toString());
        }

        // Move camera to show all markers and locations
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

    public void setPath() {
        Log.d(Tag, "setPath");

        PolylineOptions polylineOptions = new PolylineOptions();
        TestDestinations.getInstance().getDestinationsInfo();
        polylineOptions.addAll(TestDestinations.getInstance().getLatLngInfo());
        mMap.addPolyline(polylineOptions);

//        ArrayList<LatLng> destinationLatLngs = TestDestinations.getInstance().getLatLngInfo();
//
//        Iterator<LatLng> iterLatLng = destinationLatLngs.iterator();
//
//        while ( iterLatLng.hasNext()) {
//            LatLng address= iterLatLng.next();
//            mMap.addMarker(new MarkerOptions()
//                    .position(address)
//                    .title("target")
//                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
//        }
//        mMap.addPolyline(new PolylineOptions().geodesic(true)
//                .add(new LatLng(-33.866, 151.195))  // Sydney
//                .add(new LatLng(-18.142, 178.431))  // Fiji
//                .add(new LatLng(21.291, -157.821))  // Hawaii
//
//        );
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(Tag, "onMapReady 1mapflage=" + mapflage);
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
          //  return;
            Log.d(Tag, "onMapReady setMyLocationEnabled" );
            mMap.setMyLocationEnabled(true);
        }

       moveCameraCenter();
        switch ( mapflage ){
            case SETMARKER:  setMarker(); break;
            case GENPATH:  setPath(); break;
            default:
                Log.d(Tag,"Nothing to do on the map");
        }





        // Polylines are useful for marking paths and routes on the map.











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


}



