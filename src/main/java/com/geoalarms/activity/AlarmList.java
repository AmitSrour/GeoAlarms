package com.geoalarms.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.geoalarms.GeoAlarms;
import com.geoalarms.R;
import com.geoalarms.model.Alarm;
import com.geoalarms.model.Coordinates;

public class AlarmList extends Activity {

	private LinearLayout alarmlist = null;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listalarms);

		alarmlist = (LinearLayout) this.findViewById(R.id.alarmlist);
	}

	public void addAlarm(View v) {
		Intent intent = new Intent(AlarmList.this, NewAlarm.class);
		this.startActivityForResult(intent, GeoAlarms.NEWALARMACTIVITY);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case GeoAlarms.NEWALARMACTIVITY:
			if (resultCode == RESULT_OK && data != null) {
				int radioReturned = data.getIntExtra("radio", -1);
				String nameReturned = data.getStringExtra("name");
				String descriptionReturned = data.getStringExtra("description");
				int latitudeReturned = data.getIntExtra("latitude", -1);
				int longitudeReturned = data.getIntExtra("longitude", -1);
				Alarm newalarm = new Alarm(radioReturned, new Coordinates(
						longitudeReturned, latitudeReturned), nameReturned,
						descriptionReturned);
				alarmlist.addView(newalarm.alarmView(this
						.getApplicationContext()));
			}
			break;
		}
	}
}
