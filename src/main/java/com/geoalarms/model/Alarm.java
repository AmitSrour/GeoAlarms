package com.geoalarms.model;

import android.content.Context;
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
	
	public LinearLayout alarmView(Context con){
		LinearLayout pack = new LinearLayout(con);
		pack.setOrientation(LinearLayout.VERTICAL);
		
		TextView radio = new TextView(con);
		radio.setText(radius+"");
		
		TextView name = new TextView(con);
		name.setText(this.name);
		
		TextView description = new TextView(con);
		description.setText(this.description);

		pack.addView(radio);
		pack.addView(name);
		pack.addView(description);
		
		return pack;
	}
}
