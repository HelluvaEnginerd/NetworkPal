package com.csci448.goldenrush.networkingpal.database;

/**
 * Created by Hayden on 3/21/17.
 */

public class RecentActionDbSchema {
    public static final class RecentActionTable {
        public static final String NAME = "recentactions";

        public static final class Cols {
            public static final String CATEGORY = "category";
            public static final String NAME = "name";
            public static final String DATE = "date";
            public static final String INTENT = "intent";
            public static final String COMPANY = "company";
            public static final String UUID = "uuid";
        }
    }
}
