package com.geoalarms.model;

import com.google.android.maps.GeoPoint;

import android.location.Location;

public class Coordinates {
    // in microdegrees
    public int latitude;
    public int longitude;

    private static final int E6 = 1000000;

    public Coordinates(int lat, int lon) {
        this.latitude = lat;
        this.longitude = lon;
    }

    public Coordinates(Location location) {
        this.latitude = (int) (location.getLatitude() * Coordinates.E6);
        this.longitude = (int) (location.getLongitude() * Coordinates.E6);
    }
    
    public Coordinates(GeoPoint point) {
        this.latitude = point.getLatitudeE6();
        this.longitude = point.getLongitudeE6();
    }

    public GeoPoint toGeoPoint() {
        return new GeoPoint(this.latitude, this.longitude);
    }
}
