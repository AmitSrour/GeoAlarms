package com.bandoleros.geoalarms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends Activity {
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
		Intent intent = new Intent(HomeActivity.this,MapActivityView.class);
		this.startActivityForResult(intent, MAPACTIVITY);
	}
	
	public void toAlarmsList(View v) {
		Intent intent = new Intent(HomeActivity.this,AlarmListActivity.class);
		this.startActivityForResult(intent, ALARMLISTACTIVITY);
	}
	
	public void toPreferences(View v) {
		Intent intent = new Intent(HomeActivity.this,PreferencesActivity.class);
		this.startActivityForResult(intent, PREFERENCESACTIVITY);
	}
	
	public void toHelp(View v) {
		Intent intent = new Intent(HomeActivity.this, HelpActivity.class);
		this.startActivityForResult(intent, HELPACTIVITY);
	}
}
