package com.geoalarms.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
<<<<<<< HEAD

import com.geoalarms.GeoAlarms;
import com.geoalarms.R;
import com.geoalarms.model.Alarm;
=======
>>>>>>> ae579da... Added all Id Activities to GeoAlarms to make them commons

import com.geoalarms.GeoAlarms;
import com.geoalarms.R;
import com.geoalarms.model.Alarm;



<<<<<<< HEAD
=======
public class AlarmList extends Activity {
>>>>>>> ae579da... Added all Id Activities to GeoAlarms to make them commons
	
	private LinearLayout alarmlist = null;
	
	public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listalarms);
        
        alarmlist = (LinearLayout) this.findViewById(R.id.alarmlist); 
     }
	
	public void addAlarm(View v){
		Intent intent = new Intent(AlarmList.this,NewAlarm.class);
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
				Alarm newalarm = new Alarm(radioReturned, null, nameReturned, descriptionReturned);
				alarmlist.addView(newalarm.alarmView(this.getApplicationContext()));
			}
			break;
		}
	}
<<<<<<< HEAD

=======
>>>>>>> ae579da... Added all Id Activities to GeoAlarms to make them commons
}
