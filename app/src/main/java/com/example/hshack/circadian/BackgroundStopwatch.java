package com.example.hshack.circadian;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

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
                //publishResult((int)SystemClock.elapsedRealtime() - (int)initial_time);
                //handler.postDelayed(runnable, 1000);
            }
        };

        handler.postDelayed(runnable, 1500);
    }

    /*public void publishResult(int result) {
        Intent intent = new Intent("MY_ACTION");
        intent.putExtra("result", result);
        sendBroadcast(intent);
    }*/

    @Override
    public void onDestroy() {
        handler.removeCallbacks(runnable);
        mNotificationManager.cancel(1);
        timeInMilliseconds = SystemClock.elapsedRealtime() - initial_time;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        initial_time = SystemClock.elapsedRealtime();
        return START_STICKY;
    }
}
