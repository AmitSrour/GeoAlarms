package com.geoalarms.model;

import android.location.Location;

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
}
