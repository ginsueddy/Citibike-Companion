package com.example.android.citibikecompanion.jsonutilities;

import java.util.Scanner;

/**
 * Created by Ginsu on 3/12/2017.
 */

public class JsonDataContainer {

    private String [] mBikeStaitonName;
    private String [] mBikeStationStatus;
    private int [] mBikesAvailable;
    private int [] mDocksAvailable;
    private double [] mLatitude;
    private double [] mLongitude;
    private String [] mBikeStationDetails;

    public void parseRawBikeStationData (String [] mBikeStaitonDataRaw) {
        String [] mName = new String [mBikeStaitonDataRaw.length];
        String [] mStatus = new String[mBikeStaitonDataRaw.length];
        int [] mBikes =  new int[mBikeStaitonDataRaw.length];
        int [] mDocks = new int[mBikeStaitonDataRaw.length];
        double [] mLat = new double[mBikeStaitonDataRaw.length];
        double [] mLon = new double[mBikeStaitonDataRaw.length];
        String [] mDetails = new String[mBikeStaitonDataRaw.length];

        for (int i = 0; i < mBikeStaitonDataRaw.length; i++) {
            Scanner sc = new Scanner(mBikeStaitonDataRaw[i]);
            mName[i] = sc.nextLine();
            mStatus[i] = sc.nextLine();
            mBikes [i]= sc.nextInt();
            mDocks[i] = sc.nextInt();
            mLat[i] = sc.nextDouble();
            mLon[i] = sc.nextDouble();
            mDetails [i] = ("The Bike Station at " + mName[i] + " is " + mStatus[i] + " and has " + mBikes[i] +
                    " bikes and " + mDocks[i]+ " parking docks.");
        }

        mBikeStaitonName = mName;
        mBikeStationStatus = mStatus;
        mBikesAvailable = mBikes;
        mDocksAvailable = mDocks;
        mLatitude = mLat;
        mLongitude = mLon;
        mBikeStationDetails = mDetails;

    }
    
    public String [] getmBikeStaitonName () {
        return mBikeStaitonName;
    }
    
    public String [] getmBikeStationStatus () {
        return mBikeStationStatus;
    }

    public int [] getmBikesAvailable () {
        return mBikesAvailable;
    }

    public int [] getmDocksAvailable () {
        return mDocksAvailable;
    }

    public double [] getmLatitude () {
        return mLatitude;
    }

    public double [] getmLongitude () {
        return mLongitude;
    }

    public String [] getmBikeStationDetails () {
        return mBikeStationDetails;
    }
}
