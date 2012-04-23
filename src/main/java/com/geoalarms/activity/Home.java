package com.geoalarms.activity;

import com.geoalarms.GeoAlarms;

import com.geoalarms.model.AlarmManager;

import com.geoalarms.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends Activity {
	
	private AlarmManager manager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);

		this.manager = new AlarmManager();
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
