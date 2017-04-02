package com.csci448.goldenrush.networkingpal.database;

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
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
