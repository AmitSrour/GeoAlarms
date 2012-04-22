package com.bandoleros.geoalarms.model;

import android.location.Location;

public class Alarm {
	public int radio;
	public Location point;
	public String name;
	public String description;
	
	public Alarm(int radio, Location point, String name, String description) {
		this.radio = radio;
		this.point = point;
		this.name = name;
		this.description = description;
	}
}
