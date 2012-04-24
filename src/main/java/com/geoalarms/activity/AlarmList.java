package com.geoalarms.activity;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.geoalarms.GeoAlarms;
import com.geoalarms.R;
import com.geoalarms.model.Alarm;
import com.geoalarms.model.Coordinates;

public class AlarmList extends Activity {

	private ListView alarmlist;
	private ArrayAdapter<String> adapter;
	private LinkedList<String> alarms_names;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listalarms);

		List<Alarm> alarms = GeoAlarms.alarmManager.getAllAlarms();

		alarms_names = new LinkedList<String>();

		for (Alarm alarm : alarms) {
			alarms_names.addFirst(alarm.name);
		}

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, alarms_names);

		this.alarmlist = (ListView) this.findViewById(R.id.alarmlist);
		alarmlist.setAdapter(adapter);
		alarmlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		    public void onItemClick(AdapterView parent, View v, int position, long id){
		        // Start your Activity according to the item just clicked.

				Intent in = new Intent(AlarmList.this, AlarmEditor.class);
				in.putExtra("activity_type", "edit");
                in.putExtra("alarm_name", alarms_names.get(position));
				startActivityForResult(in, GeoAlarms.NEW_ALARM_ACTIVITY);
		    }
		});
	}

	public void addAlarm(View v) {
		Intent in = new Intent(AlarmList.this, AlarmEditor.class);
		in.putExtra("activity_type", "add");
		this.startActivityForResult(in, GeoAlarms.NEW_ALARM_ACTIVITY);
	}
	

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		// data has been returned from `NewAlarm`
		case GeoAlarms.NEW_ALARM_ACTIVITY:
			if (data != null) {
				// radius
				int radius = data.getIntExtra("radius", 100);

				// coordinates
				double latitude = data.getDoubleExtra("latitude", 0);
				double longitude = data.getDoubleExtra("longitude", 0);
				Coordinates coords = new Coordinates(latitude, longitude);

				// name and description
				String name = data.getStringExtra("name");
				String description = data.getStringExtra("description");

				Alarm alarm = new Alarm(radius, coords, name, description);
				
				if (resultCode == GeoAlarms.NEWALARM && data != null) {
					// save the alarm object into DB
					GeoAlarms.alarmManager.add(alarm);

					// add the alarm to the listview elements
					alarms_names.addFirst(name);
					adapter.notifyDataSetChanged();
					
				} else if (resultCode == GeoAlarms.UPDATEALARM && data != null) {
					// check if already exist a item in listview with the same name
					if (!alarms_names.contains(name)){
						String old_name = data.getStringExtra("old_name");
						// remove the old alarm into DB and in ListView
						Alarm old_alarm = GeoAlarms.alarmManager.getAlarm(old_name);
						GeoAlarms.alarmManager.delete(old_alarm);
						alarms_names.remove(old_name);
						// insert a new alarm with the new name into DB
						GeoAlarms.alarmManager.add(alarm);
						alarms_names.addFirst(name);
						adapter.notifyDataSetChanged();
					}else{
//						// update the actual alarm into DB
//						GeoAlarms.alarmManager.update(alarm);
						// possible path to update:
						Alarm actual_alarm = GeoAlarms.alarmManager.getAlarm(name);
						GeoAlarms.alarmManager.delete(actual_alarm);
						GeoAlarms.alarmManager.add(alarm);
					}
				} else if (resultCode == GeoAlarms.REMOVEALARM && data != null) {
					String to_remove_name = data.getStringExtra("old_name");
					Alarm to_remove = GeoAlarms.alarmManager.getAlarm(to_remove_name); 
					GeoAlarms.alarmManager.delete(to_remove);
					alarms_names.remove(to_remove_name);
					adapter.notifyDataSetChanged();
				}
			}

			break;
		}
	}
}
