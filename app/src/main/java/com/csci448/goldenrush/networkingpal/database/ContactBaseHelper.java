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
        Contact mark = new Contact("Mark", "Lockheed", "@@@", "1234", "Manager");
        ContentValues markValues = new ContentValues();
        markValues.put(ContactTable.Cols.NAME, mark.getContactName());
        markValues.put(ContactTable.Cols.COMPANY, mark.getCompanyName());
        markValues.put(ContactTable.Cols.EMAIL, mark.getEmail());
        markValues.put(ContactTable.Cols.PHONE, mark.getPhone());
        markValues.put(ContactTable.Cols.UUID, mark.getUUID().toString());
        db.insert(ContactTable.NAME, null, markValues);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
