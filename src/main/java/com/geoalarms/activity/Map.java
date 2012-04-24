package com.geoalarms.activity;

import java.util.List;

import android.os.Bundle;
import android.view.View;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.MyLocationOverlay;

import com.geoalarms.R;
import com.geoalarms.GeoAlarms;
import com.geoalarms.map.PointOverlay;
import com.geoalarms.map.AlarmOverlay;
import com.geoalarms.model.Alarm;



public class Map extends MapActivity {

	private MapView mapView;
	private List<Overlay> layers;
	private List<Alarm> alarms;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        this.mapView = (MapView) this.findViewById(R.id.mapView);
        this.mapView.setBuiltInZoomControls(true);

        this.layers = this.mapView.getOverlays();

        // add user's location
        this.addMyLocation();
    }

	@Override
	public void onStart()
    {
        super.onStart();

        this.drawAlarms();
    }

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	public void markCenter(View v){
        GeoPoint point = mapView.getMapCenter();
		this.drawPoint(point);
	}

	public void drawPoint(GeoPoint geoPoint) {
		PointOverlay om = new PointOverlay(geoPoint);
        // force view to refresh
		mapView.invalidate();
		this.layers.add(om);
    }

    public void drawAlarms() {
        this.layers.clear();
        this.addMyLocation();
        this.alarms = GeoAlarms.alarmManager.getAllAlarms();

        for (Alarm alarm: alarms) {
            AlarmOverlay alarmOverlay = new AlarmOverlay(alarm);
            // force view to refresh
            mapView.invalidate();
            this.layers.add(alarmOverlay);
        }
    }

    public void addMyLocation() {
        MyLocationOverlay myLocation = new MyLocationOverlay(GeoAlarms.context,
                                                             this.mapView);
        myLocation.enableMyLocation();
        this.layers.add(myLocation); 
    }
}
