package com.bandoleros.geoalarms.activity;

import java.util.List;

import android.os.Bundle;
import android.view.View;

import com.bandoleros.geoalarms.R;
import com.bandoleros.geoalarms.model.MapOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;



public class Map extends MapActivity {

	private MapView mapView = null;
	private List<Overlay> layers = null;
	
	public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        
        mapView = (MapView) this.findViewById(R.id.mapView);
        layers = mapView.getOverlays();        
    }

	@Override
	protected boolean isRouteDisplayed() {
		return true;
	}
	
	public void markCenter(View v){
		MapOverlay om = new MapOverlay(mapView.getMapCenter());
		mapView.invalidate();
		layers.add(om);
		
	}
}
