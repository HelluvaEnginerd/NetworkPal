package com.csci448.goldenrush.networkingpal.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.csci448.goldenrush.networkingpal.database.RecentActionDbSchema.RecentActionTable;

/**
 * Created by Hayden on 3/21/17.
 */

public class RecentActionBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "companybase.db";

    public RecentActionBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + RecentActionTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                RecentActionTable.Cols.CATEGORY + ", " +
                RecentActionTable.Cols.DATE + ", " +
                RecentActionTable.Cols.INTENT + ", " +
                RecentActionTable.Cols.NAME + ", " +
                RecentActionTable.Cols.COMPANY + ", " +
                RecentActionTable.Cols.UUID + ")"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
