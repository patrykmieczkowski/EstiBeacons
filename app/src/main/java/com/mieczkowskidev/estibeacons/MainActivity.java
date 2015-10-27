package com.mieczkowskidev.estibeacons;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Button monitoringButton, rangingButton, nearableRangingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        monitoringButton = (Button) findViewById(R.id.monitoring_button);
        rangingButton = (Button) findViewById(R.id.ranging_button);
        nearableRangingButton = (Button) findViewById(R.id.nearable_ranging_button);

        monitoringButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MonitoringActivity.class);
                startActivity(intent);
            }
        });

        rangingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RangingActivity.class);
                startActivity(intent);
            }
        });

        nearableRangingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NearableRangingActivity.class);
                startActivity(intent);
            }
        });

    }

}
