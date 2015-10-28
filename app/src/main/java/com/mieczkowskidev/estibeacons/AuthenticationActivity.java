package com.mieczkowskidev.estibeacons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.estimote.sdk.EstimoteSDK;
import com.estimote.sdk.MacAddress;
import com.estimote.sdk.cloud.model.BeaconInfo;
import com.estimote.sdk.connection.BeaconConnection;
import com.estimote.sdk.exception.EstimoteDeviceException;

public class AuthenticationActivity extends AppCompatActivity {

    private static final String TAG = AuthenticationActivity.class.getSimpleName();
    private BeaconConnection beaconConnection;
    private MacAddress mintMacAddress = MacAddress.fromString("FC:3D:2D:E8:CC:4E");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        EstimoteSDK.initialize(this, "estimons-mzy", "e2c71dee0a386b6a548d0cde0754384a");

        beaconConnection = new BeaconConnection(this, mintMacAddress, new BeaconConnection.ConnectionCallback() {
            @Override
            public void onAuthorized(BeaconInfo beaconInfo) {
                Log.d(TAG, "onAuthorized() called with: " + "beaconInfo = [" + beaconInfo + "]");

            }

            @Override
            public void onConnected(BeaconInfo beaconInfo) {
                Log.d(TAG, "onConnected() called with: " + "beaconInfo = [" + beaconInfo + "]");

                Log.d(TAG, "temp: " + String.valueOf(beaconConnection.temperature().get()));

                doSomeThings();

            }

            @Override
            public void onAuthenticationError(EstimoteDeviceException e) {
                Log.e(TAG, "onAuthenticationError() called with: " + "e = [" + e + "]");

            }

            @Override
            public void onDisconnected() {
                Log.d(TAG, "onDisconnected() called with: " + "");

            }
        });

        beaconConnection.authenticate();
    }

    @Override
    protected void onDestroy() {
        beaconConnection.close();
        super.onDestroy();
    }

    private void doSomeThings(){
        Log.d(TAG, "doSomeThings() called with: " + "");

        beaconConnection.edit()
                .set(beaconConnection.advertisingIntervalMillis(), 500)
                .commit(new BeaconConnection.WriteCallback() {
                    @Override
                    public void onSuccess() {
                        Log.d(TAG, "onSuccess() beacon edit");
                    }

                    @Override
                    public void onError(EstimoteDeviceException e) {
                        Log.e(TAG, "onError() called with: " + "e = [" + e + "]");
                    }
                });

    }
}

