package com.geoalarms.activity;

import com.geoalarms.R;
import android.os.Bundle;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;



public class Map extends MapActivity {

	public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
    }

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}
