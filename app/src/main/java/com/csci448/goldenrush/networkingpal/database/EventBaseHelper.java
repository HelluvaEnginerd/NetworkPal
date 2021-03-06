package com.csci448.goldenrush.networkingpal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.csci448.goldenrush.networkingpal.Contact;
import com.csci448.goldenrush.networkingpal.Event;
import com.csci448.goldenrush.networkingpal.database.EventDbSchema.EventTable;

import java.util.Date;

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
        EventTable.Cols.DETAILS+","+
        EventTable.Cols.HOUR+","+
        EventTable.Cols.MIN+")"
        );

        Event lockheedInterview = new Event("testEvent1", "Skype Smellovision Interview", "1:00pm", new Date(1494280699000l));
        lockheedInterview.setmEventDate(new Date(1494280699000l));
        ContentValues lockheedValues = new ContentValues();
        lockheedValues.put(EventTable.Cols.UUID, lockheedInterview.getId().toString());
        lockheedValues.put(EventTable.Cols.TITLE, lockheedInterview.getEventName());
        lockheedValues.put(EventTable.Cols.DATE, lockheedInterview.getmEventDate().toString());
        lockheedValues.put(EventTable.Cols.DETAILS, lockheedInterview.getmEventDetails());
        lockheedValues.put(EventTable.Cols.TIME, lockheedInterview.getmTime());
        db.insert(EventTable.NAME, null, lockheedValues);

        Event dunderMifflinMixer = new Event("testEvent2", "Military Coop", "11:00am", new Date(1494550422));
        ContentValues dunderValues = new ContentValues();
        dunderValues.put(EventTable.Cols.UUID, dunderMifflinMixer.getId().toString());
        dunderValues.put(EventTable.Cols.TITLE, dunderMifflinMixer.getEventName());
        dunderValues.put(EventTable.Cols.DATE, dunderMifflinMixer.getmEventDate().toString());
        dunderValues.put(EventTable.Cols.DETAILS, dunderMifflinMixer.getmEventDetails());
        dunderValues.put(EventTable.Cols.TIME, dunderMifflinMixer.getmTime());
        db.insert(EventTable.NAME, null, dunderValues);

        Event michaelScottPaperCompany = new Event("testEvent3", "$1,000,000 buyout", "5:00pm", new Date(1495241622));
        ContentValues michaelValues = new ContentValues();
        michaelValues.put(EventTable.Cols.UUID, michaelScottPaperCompany.getId().toString());
        michaelValues.put(EventTable.Cols.TITLE, michaelScottPaperCompany.getEventName());
        michaelValues.put(EventTable.Cols.DATE, michaelScottPaperCompany.getmEventDate().toString());
        michaelValues.put(EventTable.Cols.DETAILS, michaelScottPaperCompany.getmEventDetails());
        michaelValues.put(EventTable.Cols.TIME, michaelScottPaperCompany.getmTime());
        db.insert(EventTable.NAME, null, michaelValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

}
