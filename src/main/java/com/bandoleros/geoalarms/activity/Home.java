package com.bandoleros.geoalarms.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends Activity {
	/** Called when the activity is first created. */
	private final int MAPACTIVITY = 1;
	private final int ALARMLISTACTIVITY = 2;
	private final int PREFERENCESACTIVITY = 3;
	private final int HELPACTIVITY = 4;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);
	}

	public void toMap(View v) {
		Intent intent = new Intent(Home.this, Map.class);
		this.startActivityForResult(intent, MAPACTIVITY);
	}
	
	public void toAlarmsList(View v) {
		Intent intent = new Intent(Home.this, AlarmList.class);
		this.startActivityForResult(intent, ALARMLISTACTIVITY);
	}
	
	public void toPreferences(View v) {
		Intent intent = new Intent(Home.this, Preferences.class);
		this.startActivityForResult(intent, PREFERENCESACTIVITY);
	}
	
	public void toHelp(View v) {
		Intent intent = new Intent(Home.this, Help.class);
		this.startActivityForResult(intent, HELPACTIVITY);
	}
}
