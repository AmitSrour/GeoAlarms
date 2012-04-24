package com.geoalarms.activity;


import com.geoalarms.R;
import com.geoalarms.GeoAlarms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);
	}

	public void toMap(View v) {
		Intent intent = new Intent(Home.this, Map.class);
		this.startActivityForResult(intent, GeoAlarms.MAP_ACTIVITY);
	}
	
	public void toAlarmsList(View v) {
		Intent intent = new Intent(Home.this, AlarmList.class);
		this.startActivityForResult(intent, GeoAlarms.ALARM_LIST_ACTIVITY);
	}
	
	public void toPreferences(View v) {
		Intent intent = new Intent(Home.this, Preferences.class);
		this.startActivityForResult(intent, GeoAlarms.PREFERENCES_ACTIVITY);
	}
	
	public void toHelp(View v) {
		Intent intent = new Intent(Home.this, Help.class);
		this.startActivityForResult(intent, GeoAlarms.HELP_ACTIVITY);
	}
}
