package com.example.hshack.circadian;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by erwang01 on 3/25/17.
 */

public class SleepDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Sleep.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + SleepReaderContract.SleepEntry.TABLE_NAME + " (" +
                    SleepReaderContract.SleepEntry._ID + " INTEGER PRIMARY KEY," +
                    SleepReaderContract.SleepEntry.COLUMN_NAME_TIMESTAMP + " TEXT," +
                    SleepReaderContract.SleepEntry.COLUMN_NAME_DURATION + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + SleepReaderContract.SleepEntry.TABLE_NAME;

    public SleepDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    //Adding new entry
    public void addEntry(SleepTime sleepTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SleepReaderContract.SleepEntry.COLUMN_NAME_TIMESTAMP, sleepTime.getTimeStamp());
        values.put(SleepReaderContract.SleepEntry.COLUMN_NAME_DURATION, sleepTime.getDuration());

        long newRowId = db.insert(SleepReaderContract.SleepEntry.TABLE_NAME, null, values);
        db.close();
    }

    //get single entry
    public SleepTime getEntry(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(SleepReaderContract.SleepEntry.TABLE_NAME,
                new String[] {SleepReaderContract.SleepEntry._ID,
                        SleepReaderContract.SleepEntry.COLUMN_NAME_TIMESTAMP,
                        SleepReaderContract.SleepEntry.COLUMN_NAME_DURATION},
                SleepReaderContract.SleepEntry._ID + "=?",
                new String[] {String.valueOf(id)}, null, null, null, null);
        if (cursor!=null)
            cursor.moveToFirst();
        else
            return null;

        SleepTime sleepTime =  new SleepTime(Integer.parseInt(cursor.getString(0)),
                Long.parseLong(cursor.getString(1)), Long.parseLong(cursor.getString(2)));

        cursor.close();
        db.close();
        return sleepTime;
    }

    // Updating single sleepTime
    public int updateEntry(SleepTime sleepTime) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SleepReaderContract.SleepEntry.COLUMN_NAME_TIMESTAMP, sleepTime.getTimeStamp());
        values.put(SleepReaderContract.SleepEntry.COLUMN_NAME_DURATION, sleepTime.getDuration());

        // updating row
        return db.update(SleepReaderContract.SleepEntry.TABLE_NAME, values, SleepReaderContract.SleepEntry._ID + " = ?",
                new String[] { String.valueOf(sleepTime.getID()) });
    }

    // Deleting single sleepTime entry
    public void deleteEntry(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SleepReaderContract.SleepEntry.TABLE_NAME, SleepReaderContract.SleepEntry._ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }

    // Getting entries Count
    public int getEntryCount() {
        String countQuery = "SELECT  * FROM " + SleepReaderContract.SleepEntry.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

}
