package com.geoalarms.model;

import com.geoalarms.db.AlarmDatabaseHelper;

import java.util.List;
import java.util.ArrayList;


public class AlarmManager {

    private AlarmDatabaseHelper databaseHelper;

    public AlarmManager() {
        this.databaseHelper = new AlarmDatabaseHelper();    
    }

    public void add(Alarm... alarms) {
        for(Alarm alarm: alarms) {
            try {
                this.databaseHelper.insert(alarm.radius,
                                           alarm.coordinates.latitude,
                                           alarm.coordinates.longitude,
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

    public List<Alarm> getAlarmsWithinRadius(Coordinates coordinates, int radius) {
        List<Alarm> alarms =  new ArrayList<Alarm>();
        return alarms;
    }

}
