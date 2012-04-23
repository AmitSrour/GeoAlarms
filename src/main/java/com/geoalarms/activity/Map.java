package com.geoalarms.activity;

import java.util.List;

import com.geoalarms.R;
import com.geoalarms.model.MapOverlay;

import android.os.Bundle;
import android.view.View;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;



public class Map extends MapActivity {
	private MapView mapView = null;
	private List<Overlay> layers = null;
	private GeoPoint point = null;
	
	public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        mapView = (MapView) this.findViewById(R.id.mapView);
        layers = mapView.getOverlays(); 
    }

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	public void markCenter(View v){
		this.point = mapView.getMapCenter();
		MapOverlay om = new MapOverlay(this.point);
		mapView.invalidate();
		layers.add(om);
	}
}
