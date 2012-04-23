package com.geoalarms.activity;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.geoalarms.R;
import com.geoalarms.model.Alarm;



public class AlarmList extends Activity {

	final public int NEWALARMACTIVITY = 5;
	
	private LinearLayout alarmlist = null;
	
	public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listalarms);
        
        alarmlist = (LinearLayout) this.findViewById(R.id.alarmlist); 
     }
	
	public void addAlarm(View v){
		Intent intent = new Intent(AlarmList.this,NewAlarm.class);
		this.startActivityForResult(intent, NEWALARMACTIVITY);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case NEWALARMACTIVITY:
			if (resultCode == RESULT_OK && data != null) {
				int radioReturned = data.getIntExtra("radio", -1);
				String nameReturned = data.getStringExtra("name");
				String descriptionReturned = data.getStringExtra("description");
				Alarm newalarm = new Alarm(radioReturned, null, nameReturned, descriptionReturned);
				alarmlist.addView(newalarm.alarmView(this.getApplicationContext()));
				alarmlist.addView(newalarm.alarmView(this.getApplicationContext()));
				alarmlist.addView(newalarm.alarmView(this.getApplicationContext()));
			}
			break;
		}
	}

}
