package com.example.android.citibikecompanion;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.citibikecompanion.jsonutilities.JsonDataContainer;

import java.util.Scanner;

/**
 * Created by Ginsu on 3/12/2017.
 */

public class BikeStationAdapter extends RecyclerView.Adapter<BikeStationAdapter.BikeStationAdapterViewHolder>{

    private String [] mBikeStationName;
    
    private String [] mBikeStationDetails;

    private double [] mStationLatitude;
    
    private double [] mStationLongitude;

    private int [] mStationBikes;

    private  String [] mServiceStatus;

    private final BikeStationAdapterOnClickHandler mClickHandler;

    public interface BikeStationAdapterOnClickHandler {
        void onClick(String singleBikeStationData, double stationLatitude, double stationLongitude);
    }

    public BikeStationAdapter (BikeStationAdapterOnClickHandler bikeStationAdapterOnClickHandler) {
        mClickHandler = bikeStationAdapterOnClickHandler;
    }

    public class BikeStationAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mBikeStationTextView;

        public BikeStationAdapterViewHolder (View view) {
            super(view);
            mBikeStationTextView = (TextView) view.findViewById(R.id.tv_bike_station_data);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPostion = getAdapterPosition();
            String singleBikeStationData = mBikeStationDetails[adapterPostion];
            double stationLatitude = mStationLatitude[adapterPostion];
            double stationLongitude = mStationLongitude[adapterPostion];
            mClickHandler.onClick(singleBikeStationData, stationLatitude, stationLongitude);
        }
    }

    @Override
    public BikeStationAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layouIdForListItem = R.layout.bike_station_list;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layouIdForListItem,viewGroup,shouldAttachToParentImmediately);
        return new BikeStationAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BikeStationAdapterViewHolder bikeStationAdapterViewHolder, int position) {
        String nameForSingleBikeStation = mBikeStationName[position];
        int bikeNumberForSingleBikeStaiton = mStationBikes [position];
        String statusForSingleBikeStation = mServiceStatus [position];
        if (bikeNumberForSingleBikeStaiton == 0) {
            bikeStationAdapterViewHolder.mBikeStationTextView.setTextColor(Color.GRAY);
        }
        else if (statusForSingleBikeStation.equals("Not In Service")){
            bikeStationAdapterViewHolder.mBikeStationTextView.setTextColor(Color.RED);
        }
        else {
            bikeStationAdapterViewHolder.mBikeStationTextView.setTextColor(Color.BLACK);
        }
        bikeStationAdapterViewHolder.mBikeStationTextView.setText(nameForSingleBikeStation);
    }

    @Override
    public int getItemCount() {
        if (mBikeStationName == null){
            return 0;
        }
        return mBikeStationName.length;
    }

    public void setmBikeStationDataRaw (JsonDataContainer jsonDataContainer) {
        mBikeStationName = jsonDataContainer.getmBikeStaitonName();
        mBikeStationDetails = jsonDataContainer.getmBikeStationDetails();
        mStationLatitude = jsonDataContainer.getmLatitude();
        mStationLongitude = jsonDataContainer.getmLongitude();
        mStationBikes = jsonDataContainer.getmBikesAvailable();
        mServiceStatus = jsonDataContainer.getmBikeStationStatus();
        notifyDataSetChanged();
    }


}
