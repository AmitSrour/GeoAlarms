package com.geoalarms;

import android.app.Application;
import android.content.Context;

public class GeoAlarms extends Application {

    private static Context context;

	public static final int MAPACTIVITY = 1;
	public static final int ALARMLISTACTIVITY = 2;
	public static final int PREFERENCESACTIVITY = 3;
	public static final int HELPACTIVITY = 4;
	public static final int NEWALARMACTIVITY = 5;
	
    @Override
    public void onCreate(){
        super.onCreate();
        GeoAlarms.context = this.getApplicationContext();
    }

    public static Context getAppContext() {
        return GeoAlarms.context;
    }
}
