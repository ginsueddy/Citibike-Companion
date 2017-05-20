package com.example.android.citibikecompanion;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.citibikecompanion.jsonutilities.JsonDataContainer;

public class BikeStationDetail extends AppCompatActivity {

    private String mBikeStationData;
    private double mStationLatitude;
    private double mStationLongitude;
    private TextView mBikeStationDetailDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_station_detail);

        mBikeStationDetailDisplay = (TextView) findViewById(R.id.tv_station_detail_display);

        Intent intentThatStartedThisActivity = getIntent();
        Bundle extras = intentThatStartedThisActivity.getExtras();

        if(intentThatStartedThisActivity != null) {
            mBikeStationData = extras.getString("EXTRA_STATION_DETIALS");
            mBikeStationDetailDisplay.setText(mBikeStationData);
            mBikeStationDetailDisplay.setTextColor(Color.BLACK);

            mStationLatitude = extras.getDouble("EXTRA_LATITUDE");
            mStationLongitude = extras.getDouble("EXTRA_LONGITUDE");
        }
    }

    private void openMapIntent () {
        Uri geoLocation = Uri.parse("google.navigation:q=" + mStationLatitude + "," + mStationLongitude);
        Intent openMap = new Intent(Intent.ACTION_VIEW);
        openMap.setData(geoLocation);
        if (openMap.resolveActivity(getPackageManager()) != null) {
            startActivity(openMap);
        }
        else {
            Toast.makeText(this, "You have no apps that can open up a map! Please install one and try again later.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bike_station_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_maps) {
            openMapIntent();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
