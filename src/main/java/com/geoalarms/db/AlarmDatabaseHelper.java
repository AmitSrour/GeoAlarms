package com.geoalarms.db;

import com.geoalarms.GeoAlarms;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AlarmDatabaseHelper extends SQLiteOpenHelper {

    // use database version 0 for development
    private static final String DATABASE_NAME = "alarms";
    private static final int DATABASE_VERSION = 0;

    // keys
    private static final String KEY_RADIUS = "radius";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    
    // SQL query to create database
    private static final String ALARMS_TABLE_CREATE =
                "CREATE TABLE "     + DATABASE_NAME + " (" +
                    KEY_RADIUS      + " INTEGER,"   +
                    KEY_LATITUDE    + " REAL,"      +
                    KEY_LONGITUDE   + " REAL, "     +
                    KEY_NAME        + " TEXT, "     +
                    KEY_DESCRIPTION + " TEXT);";

    public AlarmDatabaseHelper () 
    {
        super(GeoAlarms.getAppContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) 
    {
        db.execSQL(ALARMS_TABLE_CREATE);
    }

	@Override
	public void onUpgrade(SQLiteDatabase db, 
	                      int oldVersion, 
	                      int newVersion) 
	{
		// TODO Auto-generated method stub
	}

	public void insert(int radius, 
	                   float latitude, 
	                   float longitude, 
	                   String name, 
	                   String description) 
	{
	    SQLiteDatabase db = this.getWritableDatabase();

	    if (db == null) {
	        // TODO: throw an exception 
	        return;
        } else {
            
            // database connection succesfully opened
            db.execSQL("INSERT INTO " + DATABASE_NAME + "(" + 
                            KEY_RADIUS + ", " +
                            KEY_LATITUDE + ", " + 
                            KEY_LONGITUDE + ", " + 
                            KEY_NAME + ", " + 
                            KEY_DESCRIPTION + ") " +
                            "VALUES (" + 
                                radius + ", " +
                                latitude + ", " + 
                                longitude + ", " + 
                                name + ", " +
                                description + ")");
            db.close();
        }

    }
}
