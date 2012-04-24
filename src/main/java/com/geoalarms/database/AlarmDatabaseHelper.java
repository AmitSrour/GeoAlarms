package com.geoalarms.database;

import com.geoalarms.GeoAlarms;

import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AlarmDatabaseHelper extends SQLiteOpenHelper {

    // database info
    protected static final String DATABASE_NAME = "alarms";
    protected static final int DATABASE_VERSION = 1;

    // keys
    protected static final String KEY_ROWID = "rowid";
    protected static final String KEY_RADIUS = "radius";
    protected static final String KEY_LATITUDE = "latitude";
    protected static final String KEY_LONGITUDE = "longitude";
    protected static final String KEY_NAME = "name";
    protected static final String KEY_DESCRIPTION = "description";

    protected static final String[] KEYS = {KEY_ROWID,
                                            KEY_RADIUS, 
                                            KEY_LATITUDE,
                                            KEY_LONGITUDE,
                                            KEY_NAME,
                                            KEY_DESCRIPTION};
    
    // SQL query to create database
    private static final String ALARMS_TABLE_CREATE =
                "CREATE TABLE "     + DATABASE_NAME + " (" +
                    KEY_RADIUS      + " INTEGER NOT NULL,"   +
                    KEY_LATITUDE    + " INTEGER NOT NULL,"      +
                    KEY_LONGITUDE   + " INTEGER NOT NULL, "     +
                    KEY_NAME        + " TEXT UNIQUE, "     +
                    KEY_DESCRIPTION + " TEXT);";

    public AlarmDatabaseHelper () {
        super(GeoAlarms.context, DATABASE_NAME, null, DATABASE_VERSION);
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
	                   int latitude, 
	                   int longitude, 
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
                                descriptionField + ");");

            db.close();
        }
    }

	public void update(String oldName,
	                   int radius, 
	                   int latitude, 
	                   int longitude, 
	                   String name, 
	                   String description) {

	    // get database
	    SQLiteDatabase db = this.getWritableDatabase();

	    if (db == null) {
	        // TODO: throw an exception 
	        return;
        } else {
            
            // surround strings with `'`
            String oldNameField = "'" + oldName + "'";
            String nameField = "'" + name + "'";
            String descriptionField = "'" + description + "'";

            // update SQL statement
            db.execSQL("UPDATE " + DATABASE_NAME + " SET " + 
                            KEY_RADIUS + "=" + radius + ", " +
                            KEY_LATITUDE + "=" + latitude + ", " +  
                            KEY_LONGITUDE + "=" + longitude + ", " + 
                            KEY_NAME + "=" + nameField + ", " +  
                            KEY_DESCRIPTION + "=" + descriptionField  +
                                " WHERE " + KEY_NAME + "==" + oldNameField);

            db.close();
        }
    }

	public void delete(String name) {
	    // get database
	    SQLiteDatabase db = this.getWritableDatabase();

        // surround strings with `'`
        String nameField = "'" + name + "'";

        // delete SQL statement
        db.execSQL("DELETE FROM " + DATABASE_NAME + 
                    " WHERE " + KEY_NAME + "==" + nameField);

        db.close();
    }
}
