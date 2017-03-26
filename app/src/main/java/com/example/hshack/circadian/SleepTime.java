package com.example.hshack.circadian;

/**
 * Created by hshack on 3/26/17.
 */

class SleepTime {
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
}
