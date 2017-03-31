package com.csci448.goldenrush.networkingpal.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
