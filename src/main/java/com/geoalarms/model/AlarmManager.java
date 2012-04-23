package com.geoalarms.model;

import java.util.List;
import java.util.ArrayList;

import com.geoalarms.db.AlarmDatabaseHelper;

public class AlarmManager {

    private AlarmDatabaseHelper databaseHelper;

    public AlarmManager() {
        this.databaseHelper = new AlarmDatabaseHelper();    
    }

    public void add(Alarm... alarms) {
        for(Alarm alarm: alarms) {
            this.databaseHelper.insert(alarm.radius,
                                       alarm.coordinates.latitude,
                                       alarm.coordinates.longitude,
                                       alarm.name,
                                       alarm.description);
        }
    }

    public void update(Alarm... alarms) {
        for (Alarm alarm: alarms) {
            this.databaseHelper.update(alarm.radius,
                                       alarm.coordinates.latitude,
                                       alarm.coordinates.longitude,
                                       alarm.name,
                                       alarm.description);
        }
    }

    public void delete(Alarm... alarms) {
        for (Alarm alarm: alarms) {
            this.databaseHelper.delete(alarm.name);
        }
    }

    public List<Alarm> getAlarmsWithinRadius(Coordinates coordinates, int radius) {
        List<Alarm> alarms =  new ArrayList<Alarm>();
        return alarms;
    }

}
