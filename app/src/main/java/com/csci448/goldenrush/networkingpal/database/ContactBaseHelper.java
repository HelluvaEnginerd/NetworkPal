package com.csci448.goldenrush.networkingpal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.csci448.goldenrush.networkingpal.Contact;
import com.csci448.goldenrush.networkingpal.database.ContactDbSchema.ContactTable;

/**
 * Created by Nick on 4/1/17.
 */

public class ContactBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "contactBase.db";

    public ContactBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ContactTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                ContactTable.Cols.NAME + ", " +
                ContactTable.Cols.COMPANY + ", " +
                ContactTable.Cols.EMAIL + ", " +
                ContactTable.Cols.PHONE + ", " +
                ContactTable.Cols.TITLE + ", " +
                ContactTable.Cols.PHOTOID + ", " +
                ContactTable.Cols.UUID +
                ")"
        );

        /**
         * Default Contacts Below
         */
        Contact mark = new Contact("Mark", "Lockheed", "mark@lockheed.com", "8675309", "Manager");
        ContentValues markValues = new ContentValues();
        markValues.put(ContactTable.Cols.NAME, mark.getContactName());
        markValues.put(ContactTable.Cols.COMPANY, mark.getCompanyName());
        markValues.put(ContactTable.Cols.EMAIL, mark.getEmail());
        markValues.put(ContactTable.Cols.PHONE, mark.getPhone());
        markValues.put(ContactTable.Cols.UUID, mark.getUUID().toString());
        db.insert(ContactTable.NAME, null, markValues);

        Contact mancy = new Contact("Mancy", "Wunderlic-Malec", "mancy@wm.com", "7149123", "HR Manager");
        ContentValues mancyValues = new ContentValues();
        mancyValues.put(ContactTable.Cols.NAME, mancy.getContactName());
        mancyValues.put(ContactTable.Cols.COMPANY, mancy.getCompanyName());
        mancyValues.put(ContactTable.Cols.EMAIL, mancy.getEmail());
        mancyValues.put(ContactTable.Cols.PHONE, mancy.getPhone());
        mancyValues.put(ContactTable.Cols.UUID, mancy.getUUID().toString());
        db.insert(ContactTable.NAME, null, mancyValues);

        Contact aaron = new Contact("Aaron", "Tyler Tech", "aaron@tt.com", "7891234", "Software Engineer");
        ContentValues aaronValue = new ContentValues();
        aaronValue.put(ContactTable.Cols.NAME, aaron.getContactName());
        aaronValue.put(ContactTable.Cols.COMPANY, aaron.getCompanyName());
        aaronValue.put(ContactTable.Cols.EMAIL, aaron.getEmail());
        aaronValue.put(ContactTable.Cols.PHONE, aaron.getPhone());
        aaronValue.put(ContactTable.Cols.UUID, aaron.getUUID().toString());
        db.insert(ContactTable.NAME, null, aaronValue);

        Contact joe = new Contact("Joe", "Unemployed", "joe@unemployed.com", "1234567", "Job Searcher");
        ContentValues joeValues = new ContentValues();
        joeValues.put(ContactTable.Cols.NAME, joe.getContactName());
        joeValues.put(ContactTable.Cols.COMPANY, joe.getCompanyName());
        joeValues.put(ContactTable.Cols.EMAIL, joe.getEmail());
        joeValues.put(ContactTable.Cols.PHONE, joe.getPhone());
        joeValues.put(ContactTable.Cols.UUID, joe.getUUID().toString());
        db.insert(ContactTable.NAME, null, joeValues);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
