package com.geoalarms.activity;

import java.util.List;

import android.os.Bundle;
import android.view.View;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import com.geoalarms.R;
import com.geoalarms.GeoAlarms;
import com.geoalarms.map.PointOverlay;
import com.geoalarms.map.AlarmOverlay;
import com.geoalarms.model.Alarm;
import com.geoalarms.model.Coordinates;



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
        this.layers = mapView.getOverlays(); 

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
        this.alarms = GeoAlarms.alarmManager.getAllAlarms();
        for (Alarm alarm: alarms) {
            AlarmOverlay alarmOverlay = new AlarmOverlay(alarm);
            // force view to refresh
            mapView.invalidate();
            this.layers.add(alarmOverlay);
        }
    }
}
