package com.example.richardkolen1.colormemory;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;

/**
 * Created by richardkolen1 on 24-08-15.
 */
public class Tracker extends Service implements LocationListener {

    private final Context context;

    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    boolean canGetLocation = false;

    Location location;

    double latitude;
    double longitude;

    private static final long MIN_DISTANCES_CHANGES_FOR_UPDATES = 10;
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60;

    protected LocationManager locationManager;

    public Tracker (Context context){
        this.context = context;
        getLocation();
    }

    public Location getLocation(){
        try {
            locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled){

            } else {
                this.canGetLocation = true;

                if (isNetworkEnabled){
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES,
                            MIN_DISTANCES_CHANGES_FOR_UPDATES, this);
                }

                if (locationManager != null){
                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                    if (location != null){
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                    }
                }
            }

            if (isGPSEnabled){
                if (location == null){
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES,
                            MIN_DISTANCES_CHANGES_FOR_UPDATES, this);

                    if (locationManager != null){
                        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                        if (location != null){
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }

                }
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return location;
    }

    public void stopUsingGPS(){
        if (locationManager != null){
            locationManager.removeUpdates(Tracker.this);
        }
    }

    public double getLatitude (){
        if (location != null){
            latitude = location.getLatitude();
        }
        return latitude;
    }

    public double getLongitude (){
        if (location != null){
            longitude = location.getLongitude();
        }
        return longitude;
    }

    public boolean canGetLocation (){
        return this.canGetLocation;
    }

    public void showSettingsAlert (){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        alertDialog.setTitle("GPS:");
        alertDialog.setMessage("GPS is niet geactiveerd");
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
            }
        });

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.cancel();
            }
        });

        alertDialog.show();
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

