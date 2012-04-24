package com.geoalarms.activity;

import java.lang.RuntimeException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.geoalarms.GeoAlarms;
import com.geoalarms.R;
import com.geoalarms.model.Alarm;
import com.geoalarms.model.AlarmManager;
import com.geoalarms.model.Coordinates;

import java.util.LinkedList;

public class AlarmList extends Activity {

	private ListView alarmlist;
	private AlarmManager manager;
	private ArrayAdapter<String> adapter;
	private LinkedList<String> values;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listalarms);

		values = new LinkedList<String>();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, values);

		this.alarmlist = (ListView) this.findViewById(R.id.alarmlist);
		alarmlist.setAdapter(adapter);

		this.manager = new AlarmManager();
	}

	public void addAlarm(View v) {
		Intent intent = new Intent(AlarmList.this, NewAlarm.class);
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
				int latitude = data.getIntExtra("latitude", 0);
				int longitude = data.getIntExtra("longitude", 0);
				Coordinates coords = new Coordinates(latitude, longitude);

				// name and description
				String name = data.getStringExtra("name");
				String description = data.getStringExtra("description");

				// save the alarm object into DB
				Alarm alarm = new Alarm(radius, coords, name, description);
				this.manager.add(alarm);

				// update the listview elements
				values.add(name);
				adapter.notifyDataSetChanged();
			}
			break;
		}
	}
}
