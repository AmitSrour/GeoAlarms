package com.geoalarms.model;

import com.geoalarms.model.Coordinates;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Alarm {
	public int radius;
	public Coordinates coordinates;
	public String name;
	public String description;

	public Alarm(int radius, 
	             Coordinates coordinates, 
	             String name, 
	             String description) {
		this.radius = radius;
		this.coordinates = coordinates;
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
