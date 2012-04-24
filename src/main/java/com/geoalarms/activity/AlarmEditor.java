package com.geoalarms.activity;

import java.util.Arrays;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.MyLocationOverlay;


import com.geoalarms.GeoAlarms;
import com.geoalarms.R;
import com.geoalarms.map.PointOverlay;
import com.geoalarms.map.AlarmOverlay;
import com.geoalarms.model.Alarm;
import com.geoalarms.model.Coordinates;


public class AlarmEditor extends MapActivity {

	private Intent submitIntent;
	private boolean edit;
	private MapView mapView = null;
	private List<Overlay> layers = null;
	private Spinner radioSpinner = null;
	private TextView alarmName = null;
	private TextView alarmDescription = null;
	private List<Integer> numericalItems = null;
	private Coordinates point = null;
	private String old_name = null;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newalarm);
		
		// Prepare intent
		this.submitIntent = new Intent();

		// mapView
		mapView = (MapView) this.findViewById(R.id.mapView);
		layers = mapView.getOverlays();

		// add user's location
		MyLocationOverlay myLocation = new MyLocationOverlay(GeoAlarms.context,
		                                                     mapView);
		myLocation.enableMyLocation();
		layers.add(myLocation);

		// Radio Spinner
		radioSpinner = (Spinner) this.findViewById(R.id.spinner1);
		String[] items = new String[] { "10", "100", "1000", "10000", "100000", "1000000" };
		numericalItems = Arrays.asList(10, 100, 1000, 10000, 100000, 1000000);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, items);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		radioSpinner.setAdapter(adapter);

		// Alarm name
		alarmName = (TextView) this.findViewById(R.id.nameAlarmTextView);

		// Alarm description
		alarmDescription = (TextView) this
				.findViewById(R.id.descriptionAlarmTextView);

		// Determinate if editing or adding a new alarm
        this.edit = false;
		Intent intent = this.getIntent();
		String name = intent.getStringExtra("name");
		Alarm alarmToEdit = GeoAlarms.alarmManager.getAlarm(name);

        if (alarmToEdit != null) {
            // existing alarm, edit
            this.submitIntent.putExtra("oldName", name);

			Button remove = (Button) this.findViewById(R.id.button2);
			remove.setVisibility(Button.VISIBLE);

            // Populate view with name and description
            alarmName.setText(alarmToEdit.name);
            alarmDescription.setText(alarmToEdit.description);

            // Selected radio in radio spinner
            int pos = numericalItems.indexOf(alarmToEdit.radius);
            radioSpinner.setSelection(pos);

            // Actual map point
            this.point = alarmToEdit.coordinates;
            AlarmOverlay om = new AlarmOverlay(alarmToEdit);
    		mapView.invalidate();
    		layers.add(om);
		}
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	public void markCenter(View v) {
		if (this.point != null) 
			// clear the old point Alarm in the map
			layers.clear();
		GeoPoint gp = mapView.getMapCenter();
		this.point = new Coordinates(gp);
		PointOverlay om = new PointOverlay(gp);
		mapView.invalidate();
		layers.add(om);
	}
	
    public void removeAlarm(View v){
        this.setResult(GeoAlarms.REMOVEALARM, this.submitIntent);
        finish();
    }

	public void onSubmit(View v) {
		if (this.point != null && !alarmName.getText().toString().equals("")) {
			// radius
			int pos = radioSpinner.getSelectedItemPosition();
			int radius = numericalItems.get(pos);

			// name and description
			String name = alarmName.getText().toString();
			String description = alarmDescription.getText().toString();

			// send data back
			this.submitIntent.putExtra("radius", radius);
			this.submitIntent.putExtra("latitude", point.latitude);
			this.submitIntent.putExtra("longitude", point.longitude);
			this.submitIntent.putExtra("name", name);
			this.submitIntent.putExtra("description", description);

			if (this.edit) {
				// this activity come from edit mode
				this.setResult(GeoAlarms.UPDATEALARM, this.submitIntent);
			} else {
				this.setResult(GeoAlarms.NEWALARM, this.submitIntent);
			}

			finish();
		} else {
			Toast toast = Toast.makeText(getApplicationContext(),
					"Pin a point and Introduce name", Toast.LENGTH_SHORT);
			toast.show();
		}
	}
}
