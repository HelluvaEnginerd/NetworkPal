package com.csci448.goldenrush.networkingpal.database;

/**
 * Created by Hayden on 3/21/17.
 */

public class CompanyDbSchema {
    public static final class CompanyTable {
        public static final String NAME = "companies";

        public static final class Cols {
            public static final String COMPANY = "company";
            public static final String UUID = "uuid";
            public static final String NUMBER = "number";
        }
    }
}