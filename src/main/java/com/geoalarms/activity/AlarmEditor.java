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

import com.geoalarms.GeoAlarms;
import com.geoalarms.R;
import com.geoalarms.map.PointOverlay;
import com.geoalarms.model.Alarm;
import com.geoalarms.model.Coordinates;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class AlarmEditor extends MapActivity {

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

		// mapView
		mapView = (MapView) this.findViewById(R.id.mapView);
		layers = mapView.getOverlays();

		// Radio Spinner
		radioSpinner = (Spinner) this.findViewById(R.id.spinner1);
		String[] items = new String[] { "1", "10", "20", "30", "40", "50" };
		numericalItems = Arrays.asList(1, 10, 20, 30, 40, 50);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, items);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		radioSpinner.setAdapter(adapter);

		// Alarm name
		alarmName = (TextView) this.findViewById(R.id.nameAlarmTextView);

		// Alarm description
		alarmDescription = (TextView) this
				.findViewById(R.id.descriptionAlarmTextView);

		// Determinate Activity type, Edit or new alarm
		Intent type = this.getIntent();
		String ActivityType = type.getStringExtra("activity_type");
		if (ActivityType.equals("add")) {
			// add new Alarm

		} else if (ActivityType.equals("edit")) {
			// edit alarm
			Button remove = (Button) this.findViewById(R.id.button2);
			remove.setVisibility(Button.VISIBLE);
			
            old_name = type.getStringExtra("alarm_name");
            Alarm alarm_to_edit = GeoAlarms.alarmManager.getAlarm(old_name);

            alarmName.setText(alarm_to_edit.name);
            // Alarm description
            alarmDescription.setText(alarm_to_edit.description);

            // Selected radio in radio spinner
            int pos = numericalItems.indexOf(alarm_to_edit.radius);
            radioSpinner.setSelection(pos);

            // Actual map point
            this.point = alarm_to_edit.coordinates;
            PointOverlay om = new PointOverlay(point.toGeoPoint());
    		mapView.invalidate();
    		layers.add(om);
			//add a remove Alarm button
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
		Intent in = new Intent();
		in.putExtra("old_name", old_name);
		this.setResult(GeoAlarms.REMOVEALARM,in);
		finish();
	}

	public void onSubmit(View v) {
		if (this.point != null && !alarmName.getText().toString().equals("")) {
			Intent in = new Intent();

			// radius
			int pos = radioSpinner.getSelectedItemPosition();
			int radius = numericalItems.get(pos);

			// name and description
			String name = alarmName.getText().toString();
			String description = alarmDescription.getText().toString();

			// send data back
			in.putExtra("radius", radius);
			in.putExtra("latitude", point.latitude);
			in.putExtra("longitude", point.longitude);
			in.putExtra("name", name);
			in.putExtra("description", description);
			if (old_name != null){
				// this activity come from edit mode
				in.putExtra("old_name", old_name);
				this.setResult(GeoAlarms.UPDATEALARM, in);
			}else{
				this.setResult(GeoAlarms.NEWALARM, in);
			}
			finish();
		} else {
			Toast toast = Toast.makeText(getApplicationContext(),
					"Pin a point and Introduce name", Toast.LENGTH_SHORT);
			toast.show();
		}
	}
}
