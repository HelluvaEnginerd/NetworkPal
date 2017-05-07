package com.csci448.goldenrush.networkingpal.database;

import android.hardware.SensorManager;

/**
 * Created by Sarah on 4/3/2017.
 */

public class EventDbSchema {
    public static final class EventTable{
        public static final String NAME = "events";

        public static final class Cols{
            public static final String UUID ="uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String TIME = "time";
            public static final String DETAILS = "details";
            public static final String HOUR = "hour";
            public static final String MIN = "min";
        }
    }

}
