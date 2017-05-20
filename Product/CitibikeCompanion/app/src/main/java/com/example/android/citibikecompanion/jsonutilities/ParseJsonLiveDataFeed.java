package com.example.android.citibikecompanion.jsonutilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ginsu on 3/12/2017.
 */

public final class ParseJsonLiveDataFeed {

    private static final String TAG = "parseActivity";

    public static String [] getBikeStaionDataFromJson (String bikeStaionLiveJsonDataStr) throws JSONException {
        final String LBSD_LIST = "stationBeanList";
        final String LBSD_STATION_NAME = "stationName";
        final String LBSD_AVAILABLE_BIKES = "availableBikes";
        final String LBSD_AVAILABLE_DOCKS = "availableDocks";
        final String LBSD_LATITUDE = "latitude";
        final String LBSD_LONGITUDE = "longitude";
        final String LBSD_STATUS = "statusValue";

        JSONObject liveBikeJsonFeed = new JSONObject(bikeStaionLiveJsonDataStr);

        JSONArray bikeStaionArray = liveBikeJsonFeed.getJSONArray(LBSD_LIST);
        System.out.println(bikeStaionArray.length());

        String [] parsedBikeStationData = new String[bikeStaionArray.length()];

        for (int i = 0; i < bikeStaionArray.length(); i++) {
            String stationName;
            int availableDocks;
            int availableBikes;

            double latitude;
            double longitude;
            String status;

            JSONObject bikeStationObject = bikeStaionArray.getJSONObject(i);
            System.out.println(bikeStaionArray.getJSONObject(i));

            stationName = bikeStationObject.getString(LBSD_STATION_NAME);
            System.out.println(stationName);
            availableDocks = bikeStationObject.getInt(LBSD_AVAILABLE_DOCKS);
            availableBikes = bikeStationObject.getInt(LBSD_AVAILABLE_BIKES);
            latitude = bikeStationObject.getDouble(LBSD_LATITUDE);
            longitude = bikeStationObject.getDouble(LBSD_LONGITUDE);
            status = bikeStationObject.getString(LBSD_STATUS);

            parsedBikeStationData[i] = (stationName + "\n" + status + "\n" + availableBikes + "\n" + availableDocks
                    + "\n" + latitude + "\n" + longitude);
        }

        return parsedBikeStationData;
    }
}
