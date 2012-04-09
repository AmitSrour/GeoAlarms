package com.bandoleros.geoalarms;

import android.os.Bundle;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;



public class MapActivityView extends MapActivity{

	public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        
        /*MapView map = (MapView) findViewById(R.id.mapView);
        
        map.setBuiltInZoomControls(true);*/
        
    }
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
