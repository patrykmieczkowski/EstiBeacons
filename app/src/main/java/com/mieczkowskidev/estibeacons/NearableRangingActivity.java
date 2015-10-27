package com.mieczkowskidev.estibeacons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Nearable;

import java.util.List;

public class NearableRangingActivity extends AppCompatActivity {

    private final static String TAG = NearableRangingActivity.class.getSimpleName();
    private BeaconManager beaconManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_ranging);
        Log.d(TAG, "onCreate() called with: " + "savedInstanceState = [" + savedInstanceState + "]");

        beaconManager = new BeaconManager(this);

        beaconManager.setNearableListener(new BeaconManager.NearableListener() {
            @Override
            public void onNearablesDiscovered(List<Nearable> list) {
                Log.d(TAG, "onNearablesDiscovered()");
                for (Nearable nearable : list){
                    Log.i(TAG, "Nearable: " + nearable.identifier + ", " + nearable.batteryLevel + ", " + nearable.temperature);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called with: " + "");

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                Log.d(TAG, "onServiceReady() called with: " + "");
                beaconManager.startNearableDiscovery();
            }
        });
    }
}
