package com.example.hshack.circadian;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import android.os.SystemClock;
import android.util.Log;
import android.support.v4.app.NotificationCompat;


public class BackgroundStopwatch extends Service {
    public Context context = this;
    public Handler handler = null;
    public static Runnable runnable = null;
    public NotificationCompat.Builder mBuilder;
    public NotificationManager mNotificationManager;
    private long initial_time = 0;
    long timeInMilliseconds = 0L;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle("Circadian")
                .setContentText("Sleep timer started.");
        mBuilder.setOngoing(true);

        mNotificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());

        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
            }
        };

        handler.postDelayed(runnable, 1500);
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacks(runnable);
        timeInMilliseconds = System.currentTimeMillis() - initial_time;

        SleepDbHelper mDbHelper = new SleepDbHelper(getContext());

        // Create a new map of values, where column names are the keys
        mDbHelper.addEntry(new SleepTime(initial_time, timeInMilliseconds));
        mDbHelper.close();
        mNotificationManager.cancel(1);
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
