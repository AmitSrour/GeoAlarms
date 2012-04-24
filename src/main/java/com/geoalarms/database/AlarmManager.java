package com.geoalarms.database;

import java.util.List;
import java.util.ArrayList;

import com.geoalarms.model.Alarm;
import com.geoalarms.model.Coordinates;

import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
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

    public void update(String oldName, Alarm alarm) {
        this.databaseHelper.update(oldName,
                                   alarm.radius,
                                   alarm.coordinates.latitude,
                                   alarm.coordinates.longitude,
                                   alarm.name,
                                   alarm.description);
    }

    public void delete(Alarm... alarms) {
        try {
            for (Alarm alarm: alarms) {
                // TODO: delete with ID ?
                this.databaseHelper.delete(alarm.name);
            }
            } catch (SQLiteException e) {
                // TODO: error handling
            }
    }

    public List<Alarm> getAllAlarms() {
        List<Alarm> alarms =  new ArrayList<Alarm>();

	    // get database
	    SQLiteDatabase db = this.databaseHelper.getReadableDatabase();
        
        Cursor cursor;
        try {
            cursor = db.query(this.databaseHelper.DATABASE_NAME, 
                              this.databaseHelper.KEYS,
                              null,
                              null,
                              null,
                              null,
                              null,
                              null);
                              
        } catch (SQLiteException e) {
            return alarms;
        }

        if (cursor.moveToFirst()) {
            do  {
                Alarm alarm = this.alarmFromCursor(cursor);
                alarms.add(alarm);
            } while (cursor.moveToNext());
        }

        db.close();

        return alarms;
    }

    public Alarm getAlarm(String name) {
        SQLiteDatabase db = this.databaseHelper.getReadableDatabase();
        String nameField = "'" + name + "'";
        Cursor cursor = db.query(this.databaseHelper.DATABASE_NAME,
                                 this.databaseHelper.KEYS,
                                 this.databaseHelper.KEY_NAME + "=" + nameField,
                                 null,
                                 null,
                                 null,
                                 null,
                                 null);

        if (cursor.moveToFirst()) {
            Alarm alarm = this.alarmFromCursor(cursor);
            return alarm;
        }

        return null;
    }

    public Alarm getAlarm(int id) {
        SQLiteDatabase db = this.databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid, radius, latitude, longitude, name, description " +
                                    " FROM alarms " +
                                    " WHERE rowid=" + id,
                                    null);

        if (cursor.moveToFirst()) {
            Alarm alarm = this.alarmFromCursor(cursor);
            return alarm;
        }

        return null;
    }

    private Alarm alarmFromCursor(Cursor cursor) {
        // convert to `com.geoalarms.model.Alarm`
        int id = cursor.getInt(0);
        int radius = cursor.getInt(1);

        double latitude = cursor.getDouble(2);
        double longitude = cursor.getDouble(3);
        Coordinates coords = new Coordinates(latitude, longitude);

        String name = cursor.getString(4);
        String description = cursor.getString(5);

        Alarm alarm = new Alarm(id,
                                radius,
                                coords,
                                name,
                                description);

        return alarm;
    } 
}
