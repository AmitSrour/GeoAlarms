package com.geoalarms.activity;

import java.lang.RuntimeException;

import java.util.List;

import com.geoalarms.R;
import com.geoalarms.GeoAlarms;
import com.geoalarms.model.Alarm;
import com.geoalarms.model.Coordinates;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class AlarmList extends Activity {
	
	private LinearLayout alarmlist;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listalarms);
        
        this.alarmlist = (LinearLayout) this.findViewById(R.id.alarmlist); 

        List<Alarm> alarms = GeoAlarms.alarmManager.getAllAlarms();
        for (Alarm alarm: alarms) {
            this.alarmlist.addView(alarm.alarmView(GeoAlarms.context));
        }
    }
	
	public void addAlarm(View v){
		Intent intent = new Intent(AlarmList.this,NewAlarm.class);
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

                Alarm alarm = new Alarm(radius, coords, name, description);
                GeoAlarms.alarmManager.add(alarm);

                this.alarmlist.addView(alarm.alarmView(GeoAlarms.context));
			}
			break;
		}
	}
}
