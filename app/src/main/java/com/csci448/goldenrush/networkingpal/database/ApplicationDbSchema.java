package com.csci448.goldenrush.networkingpal.database;

/**
 * Created by ddunmire on 3/20/2017.
 */

public class ApplicationDbSchema {
    public static final class ApplicationTable {
        public static final String NAME = "applications";

        public static final class Cols {
            public static final String TITLE = "title";
            public static final String CONTACT = "contact";
            public static final String DATE = "date";
            public static final String COMPANY = "company";
            public static final String UUID = "uuid";
            public static final String COVER = "cover letter";
            public static final String RESUME = "resume";
            public static final String SUBMITTED = "submitted";
        }
    }
}
