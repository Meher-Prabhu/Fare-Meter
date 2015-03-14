package com.project.meher.faremeter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    Button submit;
    RadioGroup rgroup;
    LocationManager lm = null;
    boolean gps = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(lm == null)
            lm = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        try {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                gps = (Settings.Secure.getInt(this.getContentResolver(), Settings.Secure.LOCATION_MODE) != Settings.Secure.LOCATION_MODE_OFF);
            }
            else {
                gps = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
            }
        } catch(Exception ex) {}

        if(!gps) {
            //Toast.makeText(getApplicationContext(), "No GPS", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder gps_dialog = new AlertDialog.Builder(this);
            gps_dialog.setMessage("App needs location services. Please turn on location");
            gps_dialog.setPositiveButton("Turn On", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent turnOn = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(turnOn);
                }
            });
            gps_dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.exit(0);
                }
            });
            gps_dialog.create().show();
        }

        submit = (Button) findViewById(R.id.bstart);
        rgroup = (RadioGroup) findViewById(R.id.group);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = rgroup.getCheckedRadioButtonId();
                String vehicle;
                //Toast.makeText(getApplicationContext(), String.valueOf(id),Toast.LENGTH_SHORT).show();
                if(id == -1)
                    Toast.makeText(getApplicationContext(), "Choose a vehicle", Toast.LENGTH_SHORT).show();
                else {
                    vehicle = (id == 1) ? "Taxi":"Auto";
                    Intent launch = new Intent(getApplicationContext(), fareActivity.class);
                    launch.putExtra("Vehicle", vehicle);
                    startActivity(launch);
                }

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(lm == null)
            lm = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        try {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                gps = (Settings.Secure.getInt(this.getContentResolver(), Settings.Secure.LOCATION_MODE) != Settings.Secure.LOCATION_MODE_OFF);
            }
            else {
                gps = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
            }
        } catch(Exception ex) {}

        if(!gps) {
            //Toast.makeText(getApplicationContext(), "No GPS", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder gps_dialog = new AlertDialog.Builder(this);
            gps_dialog.setMessage("App needs location services. Please turn on location");
            gps_dialog.setPositiveButton("Turn On", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent turnOn = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(turnOn);
                }
            });
            gps_dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.exit(0);
                }
            });
            gps_dialog.create().show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
