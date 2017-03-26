package com.example.hshack.circadian;

import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import android.os.SystemClock;


public class BackgroundStopwatch extends Service {
    public Context context = this;
    public Handler handler = null;
    public static Runnable runnable = null;
    private long initial_time = 0;
    long timeInMilliseconds = 0L;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        Toast.makeText(this, "Service created!", Toast.LENGTH_LONG).show();

        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                Toast toast = Toast.makeText(context, "Service is still running", Toast.LENGTH_LONG);
                toast.show();
                handler.postDelayed(runnable, 10000);
            }
        };

        handler.postDelayed(runnable, 15000);
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacks(runnable);
        timeInMilliseconds = System.currentTimeMillis() - initial_time;

        SleepDbHelper mDbHelper = new SleepDbHelper(getContext());

        // Create a new map of values, where column names are the keys
        mDbHelper.addEntry(new SleepTime(initial_time, timeInMilliseconds));
        SleepTime sleepTime= mDbHelper.getEntry(mDbHelper.getEntryCount()-1);
        Log.d("entry", String.valueOf(sleepTime.getDuration()));
        mDbHelper.close();
        Toast.makeText(this, "Service stopped", Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        initial_time = System.currentTimeMillis();
        return START_STICKY;
    }

    public Context getContext() {
        return context;
    }
}
