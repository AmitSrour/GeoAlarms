package com.geoalarms.model;

import android.location.Location;


public class Coordinates {
    public double latitude;
    public double longitude;

    public Coordinates(double lat, double lon) {
        this.latitude = lat;
        this.longitude = lon;
    }

    public Coordinates(Location location) {
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }
}
