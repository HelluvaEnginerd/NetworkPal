package com.csci448.goldenrush.networkingpal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.csci448.goldenrush.networkingpal.Application;
import com.csci448.goldenrush.networkingpal.database.ApplicationDbSchema.ApplicationTable;

/**
 * Created by ddunmire on 3/20/2017.
 */

public class ApplicationBaseHelper extends SQLiteOpenHelper{
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "applicationBase.db";

    public ApplicationBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ApplicationTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                ApplicationTable.Cols.TITLE + ", " +
                ApplicationTable.Cols.CONTACT + ", " +
                ApplicationTable.Cols.DATE + ", " +
                ApplicationTable.Cols.COMPANYNAME + ", " +
                ApplicationTable.Cols.UUID + ", " +
                ApplicationTable.Cols.COVER + ", " +
                ApplicationTable.Cols.RESUME + ", " +
                ApplicationTable.Cols.SUBMITTED +
                ")"
        );

        /**
         * Sample Applications below
         */

        Application lockheedApplication = new Application("Software Engineer", "Lockheed", "Jordan");
        ContentValues lockheedValues = new ContentValues();
        lockheedValues.put(ApplicationTable.Cols.TITLE, lockheedApplication.getJobTitle());
        lockheedValues.put(ApplicationTable.Cols.COMPANYNAME, lockheedApplication.getCompanyName());
        lockheedValues.put(ApplicationTable.Cols.CONTACT, lockheedApplication.getCompanyContact());
        db.insert(ApplicationTable.NAME, null, lockheedValues);

        Application googleApplication = new Application("Slave", "Google", "Mark");
        ContentValues googleValues = new ContentValues();
        googleValues.put(ApplicationTable.Cols.TITLE, googleApplication.getJobTitle());
        googleValues.put(ApplicationTable.Cols.COMPANYNAME, googleApplication.getCompanyName());
        googleValues.put(ApplicationTable.Cols.CONTACT, googleApplication.getCompanyContact());
        db.insert(ApplicationTable.NAME, null, googleValues);

        Application appleApplication = new Application("Salesperson", "Apple", "Tony");
        ContentValues appleValues = new ContentValues();
        appleValues.put(ApplicationTable.Cols.TITLE, appleApplication.getJobTitle());
        appleValues.put(ApplicationTable.Cols.COMPANYNAME, appleApplication.getCompanyName());
        appleValues.put(ApplicationTable.Cols.CONTACT, appleApplication.getCompanyContact());
        db.insert(ApplicationTable.NAME, null, appleValues);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
