package com.geoalarms.model;

import com.geoalarms.model.Coordinates;

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
}
