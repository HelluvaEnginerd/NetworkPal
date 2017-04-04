package com.csci448.goldenrush.networkingpal.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.csci448.goldenrush.networkingpal.database.EventDbSchema.EventTable;

/**
 * Created by Sarah on 4/3/2017.
 */

public class EventBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "eventBase.db";

    public EventBaseHelper(Context context){
        super (context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table "+ EventTable.NAME+ "(" + " _id integer primary key autoincrement, "+
        EventTable.Cols.UUID+", "+
        EventTable.Cols.TITLE+", "+
        EventTable.Cols.DATE+", "+
        EventTable.Cols.TIME+", "+
        EventTable.Cols.DETAILS+ ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

}
