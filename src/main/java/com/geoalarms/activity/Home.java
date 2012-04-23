package com.geoalarms.activity;
import com.geoalarms.GeoAlarms;
import com.geoalarms.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends Activity {
	/** Called when the activity is first created. */
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);
	}

	public void toMap(View v) {
		Intent intent = new Intent(Home.this, Map.class);
		this.startActivityForResult(intent, GeoAlarms.MAPACTIVITY);
	}
	
	public void toAlarmsList(View v) {
		Intent intent = new Intent(Home.this, AlarmList.class);
		this.startActivityForResult(intent, GeoAlarms.ALARMLISTACTIVITY);
	}
	
	public void toPreferences(View v) {
		Intent intent = new Intent(Home.this, Preferences.class);
		this.startActivityForResult(intent, GeoAlarms.PREFERENCESACTIVITY);
	}
	
	public void toHelp(View v) {
		Intent intent = new Intent(Home.this, Help.class);
		this.startActivityForResult(intent, GeoAlarms.HELPACTIVITY);
	}
}
