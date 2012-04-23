package com.geoalarms;

import android.app.Application;
import android.content.Context;

public class GeoAlarms extends Application {

    private static Context context;

	public static final int MAP_ACTIVITY = 1;
	public static final int ALARM_LIST_ACTIVITY = 2;
	public static final int PREFERENCES_ACTIVITY = 3;
	public static final int HELP_ACTIVITY = 4;
	public static final int NEW_ALARM_ACTIVITY = 5;
	
    @Override
    public void onCreate(){
        super.onCreate();
        GeoAlarms.context = this.getApplicationContext();
    }

    public static Context getAppContext() {
        return GeoAlarms.context;
    }
}
