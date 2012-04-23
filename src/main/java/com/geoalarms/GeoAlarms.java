package com.geoalarms;

import android.app.Application;
import android.content.Context;

public class GeoAlarms extends Application {

    private static Context context;

    @Override
    public void onCreate(){
        super.onCreate();
        GeoAlarms.context = this.getApplicationContext();
    }

    public static Context getAppContext() {
        return GeoAlarms.context;
    }
}
