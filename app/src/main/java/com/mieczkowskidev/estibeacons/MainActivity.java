package com.mieczkowskidev.estibeacons;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private BeaconManager beaconManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate() called with: " + "savedInstanceState = [" + savedInstanceState + "]");

        beaconManager = new BeaconManager(this);

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                Log.d(TAG, "onServiceReady() called with: " + "");
                beaconManager.startMonitoring(new Region("region", UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), 52302, 11752));
            }
        });

        beaconManager.setMonitoringListener(new BeaconManager.MonitoringListener() {
            @Override
            public void onEnteredRegion(Region region, List<Beacon> list) {
                Log.d(TAG, "onEnteredRegion() called with: " + "region = [" + region + "], list = [" + list + "]");
                for (Beacon beacon : list) {
                    Log.d(TAG, "onEnteredRegion() returned mac of beacon: " + beacon.getMacAddress());
                }

                showNotification("My region", "Witaj na naszym regionie. Tutaj dowiesz sie wszysztkiego o beaconach C:");
            }

            @Override
            public void onExitedRegion(Region region) {
                Log.d(TAG, "onExitedRegion() called with: " + "region = [" + region + "]");

            }
        });

    }

    public void showNotification(String title, String message) {
        Log.d(TAG, "showNotification() called with: " + "title = [" + title + "], message = [" + message + "]");

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0, new Intent[]{intent}, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new Notification.Builder(this)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();

        notification.defaults |= Notification.DEFAULT_SOUND;
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);

    }
}
