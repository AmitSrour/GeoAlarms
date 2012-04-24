package com.geoalarms.location;

import java.util.List;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.geoalarms.GeoAlarms;
import com.geoalarms.activity.Map;
import com.geoalarms.model.Alarm;

public class LocListener implements LocationListener {

	private LocationManager locManager;
	private PendingIntent pendingIntent;
	private final int NOEXPIRATION = -1;

	public LocListener() {
		locManager = (LocationManager) GeoAlarms.context
				.getSystemService(Context.LOCATION_SERVICE);

		// Initialize the pending intent
		Intent intent = new Intent(GeoAlarms.PROX_ALERT_INTENT);
		pendingIntent = PendingIntent.getBroadcast(GeoAlarms.context, 
		                                           0,
				                                   intent, 
				                                   0);

		// Provider location by GPS, minimum time 1000ms, minimum distance 1
		// foot
		locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 
		                                  1000,
				                          1f, 
				                          this);
		locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
				100, 1f, this);

		// Set a proximity alert to every alarm in the system
		addAllProximityAlerts();
	}

	public void onLocationChanged(Location location) {
		Log.v("LocListener", "Location Changed");
		Log.v("LocListener", "Lat: " + location.getLatitude() + "," + "Long: "
				+ location.getLongitude());
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

	public void addProximityAlert(double latitude, double longitude,
			int point_radius, int alert_expiration,
			PendingIntent proximityIntent) {

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

		IntentFilter filter = new IntentFilter(GeoAlarms.PROX_ALERT_INTENT);
		GeoAlarms.context.registerReceiver(new ProximityIntentReceiver(),
				filter);
	}

	public void resetProximityAlerts() {
		locManager.removeProximityAlert(pendingIntent);
		addAllProximityAlerts();
	}

	private void addAllProximityAlerts() {
		List<Alarm> alarms = GeoAlarms.alarmManager.getAllAlarms();

		for (Alarm alarm : alarms) {
			addProximityAlert(alarm.coordinates.getLatitude(),
					          alarm.coordinates.getLongitude(), 
					          alarm.radius,
					          NOEXPIRATION, 
					          this.pendingIntent);
		}
	}
}
