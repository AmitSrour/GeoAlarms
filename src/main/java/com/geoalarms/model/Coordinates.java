package com.geoalarms.model;

import com.google.android.maps.GeoPoint;

import android.location.Location;

public class Coordinates {
    public double latitude;
    public double longitude;

    private static final int E6 = 1000000;

    public Coordinates(double lat, double lon) {
        this.latitude = lat;
        this.longitude = lon;
    }

    public Coordinates(Location location) {
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }
    
    public Coordinates(GeoPoint point) {
        this.latitude = point.getLatitudeE6() / Coordinates.E6;
        this.longitude = point.getLongitudeE6() / Coordinates.E6;
    }
}
