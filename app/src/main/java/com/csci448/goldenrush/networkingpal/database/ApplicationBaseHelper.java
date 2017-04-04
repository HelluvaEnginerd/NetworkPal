package com.csci448.goldenrush.networkingpal.database;

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
                ApplicationTable.Cols.COMPANYUUID + ", " +
                ApplicationTable.Cols.UUID + ", " +
                ApplicationTable.Cols.COVER + ", " +
                ApplicationTable.Cols.RESUME + ", " +
                ApplicationTable.Cols.SUBMITTED +
                ")"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
