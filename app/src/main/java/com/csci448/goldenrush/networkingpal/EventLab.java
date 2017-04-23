package com.csci448.goldenrush.networkingpal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.csci448.goldenrush.networkingpal.database.EventBaseHelper;
import com.csci448.goldenrush.networkingpal.database.EventCursorWrapper;
import com.csci448.goldenrush.networkingpal.database.EventDbSchema;
import com.csci448.goldenrush.networkingpal.database.EventDbSchema.EventTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Sarah on 3/29/2017.
 */

public class EventLab {
    private static EventLab sEventLab;
    private List<Event> mEvents;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static EventLab get(Context context){
        if(sEventLab == null){
            sEventLab = new EventLab(context);
        }
        return sEventLab;

    }

    private EventLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new EventBaseHelper(mContext).getWritableDatabase();

        mEvents = new ArrayList<>();

    }

    public void addEvent(Event c){
        mEvents.add(c);
        //ContentValues values = getContentValues(c);
        //mDatabase.insert(EventTable.NAME, null, values);
    }

    /*
    public void updateEvent(Event event){
        String uuidString  = event.getId().toString();
        ContentValues values = getContentValues(event);
        mDatabase.update(EventTable.NAME,values,EventTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }
    */

    public List<Event> getEvents(){
        return mEvents;
        /*
        List<Event> events = new ArrayList<>();

        EventCursorWrapper cursor = queryEvents(null, null);

        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                events.add(cursor.getEvent());
                cursor.moveToNext();
            }
        } finally{
            cursor.close();
        }

        return events;
        */
    }

    public Event getEvent(UUID id){
        //search for event matching the given id
        for(Event event : mEvents){
            if(event.getId().equals(id)){
                return event;
            }
        }
        return null; //if event not found return null

        /*
        EventCursorWrapper cursor = queryEvents(
                EventTable.Cols.UUID + " = ?",
                new String[] {id.toString()}
        );

        try{
            if(cursor.getCount()==0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getEvent();

        }finally{
            cursor.close();
        }
        */

    }


    private static ContentValues getContentValues(Event event){
        ContentValues values = new ContentValues();
        values.put(EventTable.Cols.UUID,event.getId().toString() );
        values.put(EventTable.Cols.TITLE, event.getEventName().toString());
        values.put(EventTable.Cols.DATE, event.getmEventDate().getTime());
        values.put(EventTable.Cols.TIME, event.getmTime().toString());
        values.put(EventTable.Cols.DETAILS, event.getmEventDetails().toString());
        return values;
    }


    private EventCursorWrapper queryEvents(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(EventTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new EventCursorWrapper(cursor);
    }
}
