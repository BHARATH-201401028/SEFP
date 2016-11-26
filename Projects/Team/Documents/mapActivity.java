package com.example.sainath.e_pollution;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class mapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private double lat = 17.3850;
    private double lng = 78.4867;
    private static final int REQUEST_LOCATION = 2;
    public GoogleMap mMap;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private database DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Intent intent = getIntent();
        lat = intent.getDoubleExtra("lat", 80.0);
        lng = intent.getDoubleExtra("lng", 80.0);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void onSearch(View view) {
        mMap.clear();
        EditText location_tf = (EditText) findViewById(R.id.TFaddress);
        String location = location_tf.getText().toString();
        List<Address> addressList = null;
        if (location != null || !location.equals("")) {

            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressList.get(0);
            LatLng latlng = new LatLng(address.getLatitude(), address.getLongitude());
            mMap.addMarker(new MarkerOptions()
                    .title("Marker")
                    .snippet("The most populous city in Australia. ")
                    .position(latlng));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latlng));

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        LatLng sydney = new LatLng(lat, lng);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION);

            return;
        } else {
            googleMap.setMyLocationEnabled(true);
        }

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));
       /* googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);*/
        googleMap.addMarker(new MarkerOptions()
                .title("My_Location")
                .snippet("Here_I_Am")
                .position(sydney));
        googleMap.addMarker(new MarkerOptions()
                .title("parking_Place")
                .snippet("Park Here!!")
                .position(new LatLng(13.549054, 79.999973))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin1)));
        googleMap.addMarker(new MarkerOptions()
                .title("parking_Place")
                .snippet("Park Here!!")
                .position(new LatLng(13.548846, 79.999597))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin1)));
        googleMap.addMarker(new MarkerOptions()
                .title("parking_Place")
                .snippet("Park Here!!")
                .position(new LatLng(13.549158, 79.997666))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin1)));
        googleMap.addMarker(new MarkerOptions()
                .title("parking_Place")
                .snippet("Park Here!!")
                .position(new LatLng(13.521735, 79.999136))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin1)));
        googleMap.addMarker(new MarkerOptions()
                .title("parking_Place")
                .snippet("Park Here!!")
                .position(new LatLng(13.562285, 80.023408))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin1)));
        googleMap.addMarker(new MarkerOptions()
                .title("parking_Place")
                .snippet("Park Here!!")
                .position(new LatLng(13.556987, 80.025280))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin1)));
        googleMap.addMarker(new MarkerOptions()
                .title("parking_Place")
                .snippet("Park Here!!")
                .position(new LatLng(17.434269, 78.501892))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin1)));
        googleMap.addMarker(new MarkerOptions()
                .title("parking_Place")
                .snippet("Park Here!!")
                .position(new LatLng(17.432570, 78.494264))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin1)));
        googleMap.addMarker(new MarkerOptions()
                .title("parking_Place")
                .snippet("Park Here!!")
                .position(new LatLng(17.448517, 78.492901))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin1)));


    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("map Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }
    public long insertToDB(Double latitude,Double longitude, Float zoomLevel) {
        ContentValues dbContent = new ContentValues();
        dbContent.put(database.FIELD_LAT, latitude);
        dbContent.put(database.FIELD_LNG, longitude);
        dbContent.put(database.FIELD_ZOOM, Float.toString(zoomLevel));
        long rowId = DB.insert(dbContent);
        return rowId;
    }

    public int deleteFromDB(){
        return DB.del();
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
