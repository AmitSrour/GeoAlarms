package com.geoalarms.activity;

import java.util.List;

import android.os.Bundle;
import android.view.View;

import com.geoalarms.R;
import com.geoalarms.model.MapOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;



public class NewAlarm extends MapActivity {

	private MapView mapView = null;
	private List<Overlay> layers = null;
	
	public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newalarm);
        
        mapView = (MapView) this.findViewById(R.id.mapView);
        layers = mapView.getOverlays();        
        
    }

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	public void markCenter(View v){
		MapOverlay om = new MapOverlay(mapView.getMapCenter());
		mapView.invalidate();
		layers.add(om);
	}
}
