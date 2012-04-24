package com.geoalarms;

import android.app.Application;
import android.content.Context;

import com.geoalarms.database.AlarmManager;


public class GeoAlarms extends Application {

    // global objects
    public static Context context;
    public static AlarmManager alarmManager;

    // activity IDs
	public static final int MAP_ACTIVITY = 1;
	public static final int ALARM_LIST_ACTIVITY = 2;
	public static final int PREFERENCES_ACTIVITY = 3;
	public static final int HELP_ACTIVITY = 4;
	public static final int NEW_ALARM_ACTIVITY = 5;
	public static final int UPDATEALARM = 0;
	public static final int NEWALARM = 1;
	public static final int REMOVEALARM = 2;
	
    @Override
    public void onCreate(){
        super.onCreate();
        GeoAlarms.context = this.getApplicationContext();
        GeoAlarms.alarmManager = new AlarmManager();
    }
}
