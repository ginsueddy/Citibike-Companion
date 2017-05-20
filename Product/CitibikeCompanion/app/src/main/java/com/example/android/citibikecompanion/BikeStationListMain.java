package com.example.android.citibikecompanion;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.audiofx.BassBoost;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.citibikecompanion.jsonutilities.EstabilshConnectionToNetwork;
import com.example.android.citibikecompanion.jsonutilities.JsonDataContainer;
import com.example.android.citibikecompanion.jsonutilities.ParseJsonLiveDataFeed;

import java.net.URL;

public class BikeStationListMain extends AppCompatActivity implements BikeStationAdapter.BikeStationAdapterOnClickHandler, SwipeRefreshLayout.OnRefreshListener{

    //private LocationListener mLocationListner;

   // private LocationManager mLocationManager;

    private RecyclerView mRecyclerView;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private BikeStationAdapter mBikeStationAdapter;

    private TextView mErrorMessage;

    private ProgressBar mLoadingIndicator;

    private String citibikeJSONDataUrl = "https://feeds.citibikenyc.com/stations/stations.json";

    private  static  final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_bikeStation);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh_bikeStation);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mErrorMessage = (TextView) findViewById(R.id.tv_error_message);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mBikeStationAdapter = new BikeStationAdapter(this);
        mRecyclerView.setAdapter(mBikeStationAdapter);

        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

       // mLocationManager = (LocationManager) getSystemService (LOCATION_SERVICE);
        mLoadingIndicator.setVisibility(View.VISIBLE);

        loadBikeStaionData();

    }

    private void loadBikeStaionData () {
        showBikeSationData();
        new FetchBikeStaionData().execute(citibikeJSONDataUrl);
    }

    @Override
    public void onClick(String singleBikeStationData, double stationLatitude, double stationLongitude) {
        Class destinationClass = BikeStationDetail.class;
        Intent intentToDetail = new Intent(this,destinationClass);
        Bundle extras = new Bundle();
        extras.putString("EXTRA_STATION_DETIALS", singleBikeStationData);
        extras.putDouble("EXTRA_LATITUDE", stationLatitude);
        extras.putDouble("EXTRA_LONGITUDE", stationLongitude);
        intentToDetail.putExtras(extras);
        startActivity(intentToDetail);
    }

    private void showBikeSationData () {
        mErrorMessage.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage () {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRefresh() {
        loadBikeStaionData();
    }

    public class FetchBikeStaionData extends AsyncTask<String, Void, JsonDataContainer> {

        @Override
        protected JsonDataContainer doInBackground(String... params) {
            if (params.length == 0) {
                return  null;
            }

            String citiBikeUrlString = params[0];
            URL bikeStaitonResponseUrl = EstabilshConnectionToNetwork.urlBuilder(citiBikeUrlString);

            try {
                String jsonBikeStationResponse = EstabilshConnectionToNetwork.getResponseFromUrlConnection(bikeStaitonResponseUrl);
                String [] simpleJsonBikeData = ParseJsonLiveDataFeed
                        .getBikeStaionDataFromJson(jsonBikeStationResponse);
                JsonDataContainer jsonDataContainer = new JsonDataContainer();
                jsonDataContainer.parseRawBikeStationData(simpleJsonBikeData);
                return jsonDataContainer;
            } catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }


        @Override
        protected void onPostExecute(final JsonDataContainer rawBikeStationData) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (mSwipeRefreshLayout.isRefreshing()){
                mSwipeRefreshLayout.setRefreshing(false);
            }
            if (rawBikeStationData != null ) {
                showBikeSationData();
                mBikeStationAdapter.setmBikeStationDataRaw(rawBikeStationData);
            }
            else {
                showErrorMessage();
            }
        }
    }
}
