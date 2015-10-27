package com.mieczkowskidev.estibeacons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import java.util.List;
import java.util.UUID;

public class RangingActivity extends AppCompatActivity {

    private static final String TAG = RangingActivity.class.getSimpleName();
    private BeaconManager beaconManager;
    private Region region;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranging);

        beaconManager = new BeaconManager(this);
        region = new Region("ranging region", UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), null, null);

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                Log.d(TAG, "onServiceReady() called with: " + "");
                beaconManager.startRanging(region);
            }
        });

        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
                if (!list.isEmpty()) {
                    Log.d(TAG, "onBeaconsDiscovered()");
                    for (Beacon beacon : list){
                        Log.v(TAG, "found beacon: " + beacon.toString());
                    }
                }
            }
        });
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause() called with: " + "");
        beaconManager.stopRanging(region);
        super.onPause();
    }
}
