package com.example.flipmeet;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity implements LocListenerInterface {
    private Button act_change;

    private LocationManager locationManager;
    private TextView tvDistance, tvVelocity;
    private Location lastLocation;
    private MyLocListener myLocListener;
    private int CODE_LOCATION_PERMISSION = 100;
    private int TIME_UPDATE_SEC = 3;
    private int DISTANCE_UPDATE_METER = 10;
    private int distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButtonSearch();
        addListenerOnButtonMap();
        init();
    }

    private void init() {
        tvVelocity = findViewById(R.id.tvVelocity);
        tvDistance = findViewById(R.id.tvDistance);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        myLocListener = new MyLocListener();
        myLocListener.setLocListenerInterface(this);
        checkPermissions();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CODE_LOCATION_PERMISSION && grantResults[0] == RESULT_OK) {
            checkPermissions();
        }
//        else {
//            Toast.makeText(this, "No GPS permission", Toast.LENGTH_SHORT).show();
//        }
    }

    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, CODE_LOCATION_PERMISSION);
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, TIME_UPDATE_SEC, DISTANCE_UPDATE_METER, myLocListener);
        }
    }


    @Override
    public void onLocationChanged(Location loc) {
        if (loc.hasSpeed() && lastLocation != null) {
            distance += lastLocation.distanceTo(loc);
        }
        lastLocation = loc;
        checkDistance(distance);
        tvDistance.setText(String.valueOf(checkDistance(distance)));
        tvVelocity.setText(String.valueOf(loc.getSpeed()));
    }

    private int checkDistance(int distance) {
        return distance > 100 ? 0 : distance;
//        if (distance > 100) {
//            return 0;
//        } else {
//            return distance;
//        }
    }


    private void addListenerOnButtonSearch() {
        act_change = (Button) findViewById(R.id.activity_search);
        act_change.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent toMap = new Intent(".ActivitySearch");
                        startActivity(toMap);
                    }
                }
        );
    }

    private void addListenerOnButtonMap() {
        act_change = (Button) findViewById(R.id.activity_map);
        act_change.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent toMap = new Intent(".MapActivity");
                        startActivity(toMap);
                    }
                }
        );
    }
}
