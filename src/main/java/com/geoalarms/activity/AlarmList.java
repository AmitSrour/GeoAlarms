package com.geoalarms.activity;

import java.util.LinkedList;
import java.util.List;

import com.geoalarms.R;
import com.geoalarms.GeoAlarms;
import com.geoalarms.model.Alarm;
import com.geoalarms.model.Coordinates;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AlarmList extends Activity {

	private ListView alarmlist;
	private ArrayAdapter<String> adapter;
	private LinkedList<String> values;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listalarms);

		List<Alarm> alarms = GeoAlarms.alarmManager.getAllAlarms();
		
		values = new LinkedList<String>();
		
		for (Alarm alarm : alarms) {
			values.addFirst(alarm.name);
		}
		
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, values);

		this.alarmlist = (ListView) this.findViewById(R.id.alarmlist);
		alarmlist.setAdapter(adapter);
	}

	public void addAlarm(View v) {
		Intent intent = new Intent(AlarmList.this, AlarmEditor.class);
		this.startActivityForResult(intent, GeoAlarms.NEW_ALARM_ACTIVITY);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		// data has been returned from `NewAlarm`
		case GeoAlarms.NEW_ALARM_ACTIVITY:
			if (resultCode == RESULT_OK && data != null) {
				// radius
				int radius = data.getIntExtra("radius", 100);

				// coordinates
				double latitude = data.getDoubleExtra("latitude", 0);
				double longitude = data.getDoubleExtra("longitude", 0);
				Coordinates coords = new Coordinates(latitude, longitude);

				// name and description
				String name = data.getStringExtra("name");
				String description = data.getStringExtra("description");

				// save the alarm object into DB
				Alarm alarm = new Alarm(radius, coords, name, description);
				GeoAlarms.alarmManager.add(alarm);

				// update the listview elements
				values.addFirst(name);
				adapter.notifyDataSetChanged();
			}
			break;
		}
	}
}
