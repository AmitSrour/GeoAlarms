package com.geoalarms.model;

import java.util.List;
import java.util.ArrayList;

import com.geoalarms.model.Coordinates;
import com.geoalarms.db.AlarmDatabaseHelper;

import android.database.Cursor;

import android.database.sqlite.SQLiteException;

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
            } catch (SQLiteException e) {
                // TODO: error handling
            }
        }
    }

    public void update(Alarm... alarms) {
        for (Alarm alarm: alarms) {
            try {
                this.databaseHelper.update(alarm.radius,
                                           alarm.coordinates.latitude,
                                           alarm.coordinates.longitude,
                                           alarm.name,
                                           alarm.description);
            } catch (SQLiteException e) {
                // TODO: error handling
            }
        }
    }

    public void delete(Alarm... alarms) {
        try {
            for (Alarm alarm: alarms) {
                this.databaseHelper.delete(alarm.name);
            }
            } catch (SQLiteException e) {
                // TODO: error handling
            }
    }

    public List<Alarm> getAllAlarms() {
        List<Alarm> alarms =  new ArrayList<Alarm>();
        Cursor cursor = this.databaseHelper.all();

        // return empty list
        if (!cursor.moveToFirst()) {
            return alarms;
        }

        while (cursor.moveToNext()) {
            Alarm alarm = this.alarmFromCursor(cursor);
            alarms.add(alarm);
        }

        return alarms;
    }

    public List<Alarm> getAlarmsWithinRadius(Coordinates coordinates, int radius) {
        List<Alarm> alarms =  new ArrayList<Alarm>();
        return alarms;
    }

    private Alarm alarmFromCursor(Cursor cursor) {
        // convert to `com.geoalarms.model.Alarm`
        int radius = cursor.getInt(0);

        double latitude = cursor.getDouble(1);
        double longitude = cursor.getDouble(2);
        Coordinates coords = new Coordinates(latitude, longitude);

        String name = cursor.getString(3);
        String description = cursor.getString(4);

        Alarm alarm = new Alarm(radius,
                                coords,
                                name,
                                description);

        return alarm;
    } 

}
