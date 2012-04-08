package alarm;

import android.location.Location;

public class Alarm {
	public int Radio;
	public Location Point;
	public String Name;
	public String Description;
	
	public Alarm(int radio, Location point, String name, String description){
		this.Radio = radio;
		this.Point = point;
		this.Name = name;
		this.Description = description;
	}
}
