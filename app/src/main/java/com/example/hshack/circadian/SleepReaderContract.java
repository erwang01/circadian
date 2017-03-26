package com.example.hshack.circadian;

import android.provider.BaseColumns;

/**
 * Created by erwang01 on 3/26/17.
 */

public final class SleepReaderContract {
    private SleepReaderContract() {}

    public static class SleepEntry implements BaseColumns {
        public static final String TABLE_NAME = "sleep_times";
        public static final String COLUMN_NAME_TIMESTAMP = "timestamp";
        public static final String COLUMN_NAME_DURATION = "duration";
    }
}
