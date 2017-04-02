package com.csci448.goldenrush.networkingpal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.csci448.goldenrush.networkingpal.Company;
import com.csci448.goldenrush.networkingpal.CompanyLab;

import static com.csci448.goldenrush.networkingpal.database.CompanyDbSchema.*;

/**
 * Created by Hayden on 3/21/17.
 */

public class CompanyBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "companies";

    public CompanyBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + CompanyDbSchema.CompanyTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                CompanyTable.Cols.NUMBER + ", " +
                CompanyTable.Cols.COMPANYNAME + ", " +
                CompanyTable.Cols.UUID +  ", " +
                CompanyTable.Cols.ADDRESS + ")"
        );

        /**
         * Sample Companies
         * Maybe there is a better way? lol
         */

        Company lockheed = new Company("Lockheed", "8675309", "1234 Lane");
        ContentValues lockheedValues = new ContentValues();
        lockheedValues.put(CompanyTable.Cols.UUID, lockheed.getID().toString());
        lockheedValues.put(CompanyTable.Cols.NUMBER, lockheed.getPhoneNumber());
        lockheedValues.put(CompanyTable.Cols.COMPANYNAME, lockheed.getCompanyName());
        lockheedValues.put(CompanyTable.Cols.ADDRESS, lockheed.getAddress());
        db.insert(CompanyTable.NAME, null, lockheedValues);

        Company apple = new Company("Apple", "5550555", "1111 Apple Circle");
        ContentValues appleValues = new ContentValues();
        appleValues.put(CompanyTable.Cols.UUID, apple.getID().toString());
        appleValues.put(CompanyTable.Cols.NUMBER, apple.getPhoneNumber());
        appleValues.put(CompanyTable.Cols.COMPANYNAME, apple.getCompanyName());
        appleValues.put(CompanyTable.Cols.ADDRESS, apple.getAddress());
        db.insert(CompanyTable.NAME, null, appleValues);

        Company google = new Company("Google", "9998887777", "0909 Google Campus");
        ContentValues googleValues = new ContentValues();
        googleValues.put(CompanyTable.Cols.UUID, google.getID().toString());
        googleValues.put(CompanyTable.Cols.NUMBER, google.getPhoneNumber());
        googleValues.put(CompanyTable.Cols.COMPANYNAME, google.getCompanyName());
        googleValues.put(CompanyTable.Cols.ADDRESS, google.getAddress());
        db.insert(CompanyTable.NAME, null, googleValues);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
