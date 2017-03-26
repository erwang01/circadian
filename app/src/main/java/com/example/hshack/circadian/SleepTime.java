package com.example.hshack.circadian;

import android.os.Build;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by hshack on 3/26/17.
 */

class SleepTime {
    private static final int HOURS_PER_QUALITY_LEVEL = 3;
    private int _id;
    private long _timeStamp;
    private long _duration;

    public SleepTime() {
    }

    public SleepTime(int id, long _timeStamp, long _duration) {
        this._id = id;
        this._timeStamp = _timeStamp;
        this._duration = _duration;
    }

    public SleepTime(long _timeStamp, long _duration) {
        this._timeStamp = _timeStamp;
        this._duration = _duration;
    }

    public SleepTime(SleepTime copy) {
        this._id = copy._id;
        this._timeStamp = copy._timeStamp;
        this._duration = copy._duration;
    }

    public int getID() {
        return this._id;
    }

    public void setID(int id) {
        this._id = id;
    }

    public long getTimeStamp() {
        return _timeStamp;
    }

    public void setTimeStamp(long timestamp) {
        this._timeStamp = timestamp;
    }

    public long getDuration() {
        return _duration;
    }

    public void setDuration(long duration) {
        this._duration = duration;
    }

    public int getSleepQuality() {
        return Math.min(3,Math.max((int)(_duration/1000/60/HOURS_PER_QUALITY_LEVEL),0));
    }

    public static ArrayList<SleepTime> sortByDate(ArrayList<SleepTime> sleepTimes) {
        Collections.sort(sleepTimes, new Comparator<SleepTime>() {
            @Override
            public int compare(SleepTime o1, SleepTime o2) {
                long diff = (o1._timeStamp - o2._timeStamp);
                if (diff < Integer.MAX_VALUE && diff > Integer.MIN_VALUE) {
                    return (int) diff;
                }
                else {
                    return (int) (diff / Math.abs(diff) * Integer.MAX_VALUE);
                }
            }
        });
        return sleepTimes;
    }

    public static ArrayList<SleepTime> sortByDuration(ArrayList<SleepTime> sleepTimes) {
        Collections.sort(sleepTimes, new Comparator<SleepTime>() {
            @Override
            public int compare(SleepTime o1, SleepTime o2) {
                long diff = (o1._duration - o2._duration);
                if (diff < Integer.MAX_VALUE && diff > Integer.MIN_VALUE) {
                    return (int) diff;
                }
                else {
                    return (int) (diff / Math.abs(diff) * Integer.MAX_VALUE);
                }
            }
        });
        return sleepTimes;
    }

}
