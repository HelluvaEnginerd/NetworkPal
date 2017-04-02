package com.csci448.goldenrush.networkingpal.database;

/**
 * Created by Nick on 4/1/17.
 */

public class ContactDbSchema {
    public static final class ContactTable {
        public static final String NAME = "contacts";

        public static final class Cols {
            public static final String NAME = "name";
            public static final String COMPANY = "company";
            public static final String EMAIL = "email";
            public static final String PHONE = "phone";
            public static final String TITLE = "title";
            public static final String PHOTOID = "photo";
            public static final String UUID = "uuid";
        }
    }
}
