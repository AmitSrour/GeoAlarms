package com.geoalarms.model;

import android.location.Location;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Alarm {
	public int radius;
	public Location location;
	public String name;
	public String description;
	
	public Alarm(int radius, Location location, String name, String description) {
		this.radius = radius;
		this.location = location;
		this.name = name;
		this.description = description;
	}
	
	public View alarmView(){
		LinearLayout pack = new LinearLayout(null);
		
		TextView radio = new TextView(null);
		radio.setText(this.radius);
		
		TextView name = new TextView(null);
		name.setText(this.name);
		
		TextView description = new TextView(null);
		description.setText(this.description);

		pack.addView(radio);
		pack.addView(name);
		pack.addView(description);
		
		return pack;
	}
}
