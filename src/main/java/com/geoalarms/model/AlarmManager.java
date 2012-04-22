package com.geoalarms.model;

import android.content.Context;

import android.location.Location;

import java.util.List;
import java.util.ArrayList;

import com.geoalarms.db.AlarmDatabaseHelper;

public class AlarmManager {

    private Context context;
    private AlarmDatabaseHelper databaseHelper;

    public AlarmManager(Context context) {
        this.context = context;
        this.databaseHelper = new AlarmDatabaseHelper(context);    
    }

    public void add(Alarm... alarms) {
        for(Alarm alarm: alarms) {
            try {
                this.databaseHelper.insert(alarm.radius,
                                           (float) alarm.location.getLatitude(),
                                           (float) alarm.location.getLongitude(),
                                           alarm.name,
                                           alarm.description);
            } catch (Exception e) {
                // TODO
            }
        }
    }

    public void update(Alarm... alarm) {

    }

    public void delete(Alarm... alarm) {

    }

    public List<Alarm> getAlarmsWithinRadius(Location location, int radius) {
        List<Alarm> alarms =  new ArrayList<Alarm>();
        return alarms;
    }

}
