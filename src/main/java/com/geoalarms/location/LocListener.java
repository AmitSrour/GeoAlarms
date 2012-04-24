package com.geoalarms.location;

import java.util.List;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.geoalarms.GeoAlarms;
import com.geoalarms.model.Alarm;

public class LocListener implements LocationListener {
	private LocationManager locManager;
	private String PROX_ALERT_INTENT = "com.geoalarms.location.Proximity";

	public LocListener() {
		locManager = (LocationManager) GeoAlarms.context
				.getSystemService(GeoAlarms.context.LOCATION_SERVICE);
		List<Alarm> alarms = GeoAlarms.alarmManager.getAllAlarms();
		
		// Provider location by GPS, minimum time 1000ms, minimumdistance 1 foot
		locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1f, this);
		// Open the alarmlist activity when a alert has trigger
		

		// Set a proximity alert to every alarm in the system
		int NoExpiration = -1;
		int RADIUS = 2000000000;
		for (Alarm alarm : alarms) {
			addProximityAlert(alarm.coordinates.latitude,
					alarm.coordinates.longitude, RADIUS, NoExpiration,
					PROX_ALERT_INTENT);
		}
	}

	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		// Intent in = new Intent(GeoAlarms.context,Map.class);
		// GeoAlarms.context.startActivity(in);
		Toast.makeText(
				GeoAlarms.context,
				"Location Changed" + location.getLongitude() + ","
						+ location.getLatitude(), Toast.LENGTH_SHORT);
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	private void addProximityAlert(double latitude, double longitude,
			int point_radius, int alert_expiration, String PROX_ALERT_INTENT) {

		Intent intent = new Intent(PROX_ALERT_INTENT);
		PendingIntent proximityIntent = PendingIntent.getBroadcast(
				GeoAlarms.context, 0, intent, 0);

		locManager.addProximityAlert(latitude, // the latitude of the central
												// point of the alert region
				longitude, // the longitude of the central point of the alert
							// region
				point_radius, // the radius of the central point of the alert
								// region, in meters
				alert_expiration, // time for this proximity alert, in
									// milliseconds, or -1 to indicate no
									// expiration
				proximityIntent // will be used to generate an Intent to fire
								// when entry to or exit from the alert region
								// is detected
				);

		IntentFilter filter = new IntentFilter(PROX_ALERT_INTENT);
		GeoAlarms.context.registerReceiver(new ProximityIntentReceiver(), filter);
	}
}
