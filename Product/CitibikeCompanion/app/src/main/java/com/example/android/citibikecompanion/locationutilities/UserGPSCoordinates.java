package com.example.android.citibikecompanion.locationutilities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.citibikecompanion.R;

/**
 * Created by Ginsu on 3/12/2017.
 */

public class UserGPSCoordinates {

    public static double getUserLatitudeCoordinate (LocationListener locationListener, LocationManager locationManager) {
        return 0;//
    }

    public static double getUserLongitudeCoordinate (LocationListener locationListener, LocationManager locationManager) {
        return 0;
    }

}
