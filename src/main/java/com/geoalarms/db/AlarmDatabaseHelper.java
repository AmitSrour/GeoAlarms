package com.geoalarms.db;

import com.geoalarms.GeoAlarms;

import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AlarmDatabaseHelper extends SQLiteOpenHelper {

    // database info
    private static final String DATABASE_NAME = "alarms";
    private static final int DATABASE_VERSION = 1;

    // keys
    private static final String KEY_RADIUS = "radius";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";

    private static final String[] KEYS = {KEY_RADIUS, 
                                          KEY_LATITUDE,
                                          KEY_LONGITUDE,
                                          KEY_NAME,
                                          KEY_DESCRIPTION};
    
    // SQL query to create database
    private static final String ALARMS_TABLE_CREATE =
                "CREATE TABLE "     + DATABASE_NAME + " (" +
                    KEY_RADIUS      + " INTEGER NOT NULL,"   +
                    KEY_LATITUDE    + " REAL NOT NULL,"      +
                    KEY_LONGITUDE   + " REAL NOT NULL, "     +
                    KEY_NAME        + " TEXT UNIQUE, "     +
                    KEY_DESCRIPTION + " TEXT);";

    public AlarmDatabaseHelper () {
        super(GeoAlarms.getAppContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ALARMS_TABLE_CREATE);
    }

	@Override
	public void onUpgrade(SQLiteDatabase db, 
	                      int oldVersion, 
	                      int newVersion) {
		// TODO Auto-generated method stub
	}

	public void insert(int radius, 
	                   double latitude, 
	                   double longitude, 
	                   String name, 
	                   String description) {

	    // get database
	    SQLiteDatabase db = this.getWritableDatabase();

	    if (db == null) {
	        // TODO: throw an exception 
	        return;
        } else {
            
            // surround strings with `'`
            String nameField = "'" + name + "'";
            String descriptionField = "'" + description + "'";

            // insert SQL statement
            db.execSQL("INSERT INTO " + DATABASE_NAME + " (" + 
                            KEY_RADIUS + ", " +
                            KEY_LATITUDE + ", " + 
                            KEY_LONGITUDE + ", " + 
                            KEY_NAME + ", " + 
                            KEY_DESCRIPTION + ") " +
                            "VALUES (" + 
                                radius + ", " +
                                latitude + ", " + 
                                longitude + ", " + 
                                nameField + ", " +
                                descriptionField + ")");

            db.close();
        }
    }

	public void update(int radius, 
	                   double latitude, 
	                   double longitude, 
	                   String name, 
	                   String description) {

	    // get database
	    SQLiteDatabase db = this.getWritableDatabase();

	    if (db == null) {
	        // TODO: throw an exception 
	        return;
        } else {
            
            // surround strings with `'`
            String nameField = "'" + name + "'";
            String descriptionField = "'" + description + "'";

            // update SQL statement
            db.execSQL("UPDATE " + DATABASE_NAME + " SET " + 
                            KEY_RADIUS + "=" + radius + 
                            KEY_LATITUDE + "=" + latitude +  
                            KEY_LONGITUDE + "=" + longitude + 
                            KEY_NAME + "=" + nameField +  
                            KEY_DESCRIPTION + "=" + descriptionField  +
                                " WHERE " + KEY_NAME + "=" + nameField);

            db.close();
        }
    }

	public void delete(String name) {
	    // get database
	    SQLiteDatabase db = this.getWritableDatabase();

        if (db == null) {
            // TODO: throw an exception 
            return;
        } else {
            // surround strings with `'`
            String nameField = "'" + name + "'";

            // delete SQL statement
            db.execSQL("DELETE FROM " + DATABASE_NAME + 
                        " WHERE" + 
                            KEY_NAME + "=" + nameField);

            db.close();
        }
    }
}
