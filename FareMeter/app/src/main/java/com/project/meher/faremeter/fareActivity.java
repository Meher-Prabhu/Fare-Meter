package com.project.meher.faremeter;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class fareActivity extends ActionBarActivity {

    TextView locView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fare);

        locView = (TextView) findViewById(R.id.locationView);

        Intent call = getIntent();
        String vehicle = call.getStringExtra("Vehicle");

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

// Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener(){
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                makeUseOfNewLocation(location);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };

// Register the listener with the Location Manager to receive location updates
        Location lastLocation;
        if((lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)) == null)
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        else {
            Toast.makeText(getApplicationContext(), "YO MAX", Toast.LENGTH_SHORT).show();
            locView.setText("YO MAX");
            //locView.setText(lastLocation.toString());
        }


        //locView.setText(loc.toString());

    }

    void makeUseOfNewLocation(Location location) {
        //locView.setText(location.toString());
        Toast.makeText(getApplicationContext(),"YO",Toast.LENGTH_SHORT).show();
        locView.setText("Yo");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fare, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
