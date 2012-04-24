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

    List<Alarm> alarms;
	private ListView alarmlist;
	private ArrayAdapter<String> adapter;
	private LinkedList<String> alarmNames;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listalarms);

		// init alarmlist
		this.alarmlist = (ListView) this.findViewById(R.id.alarmlist);
		alarmlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		    public void onItemClick(AdapterView parent, View v, int position, long id){
		        
		        // Start your Activity according to the item just clicked.
                String alarmName = alarmNames.get(position);
                Alarm alarm = GeoAlarms.alarmManager.getAlarm(alarmName);

				Intent in = new Intent(AlarmList.this, AlarmEditor.class);
				in.putExtra("name", alarm.name);
				startActivityForResult(in, GeoAlarms.NEW_ALARM_ACTIVITY);
		    }
		});
	}

    @Override
    public void onStart() {
        super.onStart();

        this.alarms = GeoAlarms.alarmManager.getAllAlarms();
        this.drawAlarmList();
    }

	public void drawAlarmList() {
		alarmNames = new LinkedList<String>();

		for (Alarm alarm : alarms) {
			alarmNames.addFirst(alarm.name);
		}

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, alarmNames);

		alarmlist.setAdapter(adapter);
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
				int latitude = data.getIntExtra("latitude", 0);
				int longitude = data.getIntExtra("longitude", 0);
				Coordinates coords = new Coordinates(latitude, longitude);

				// name and description
				String name = data.getStringExtra("name");
				String description = data.getStringExtra("description");

				Alarm alarm;

				// add alarm
				if (resultCode == GeoAlarms.NEWALARM) {
				    alarm = new Alarm(radius, coords, name, description);
				    GeoAlarms.alarmManager.add(alarm);
				    return;
                }

				// edit or remove
                String oldName = data.getStringExtra("oldName");
                alarm = GeoAlarms.alarmManager.getAlarm(oldName);

                if (resultCode == GeoAlarms.REMOVEALARM) {
					// delete
					GeoAlarms.alarmManager.delete(alarm);
                } else { 
                    // update
                    alarm.update(radius,
                                 coords,
                                 name,
                                 description);
					GeoAlarms.alarmManager.update(oldName, alarm);
				}
			}

			break;
		}
	}
}
