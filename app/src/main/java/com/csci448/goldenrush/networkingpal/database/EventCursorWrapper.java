package com.csci448.goldenrush.networkingpal.database;

import android.database.CursorWrapper;
import android.database.Cursor;
import android.util.Log;

import com.csci448.goldenrush.networkingpal.Event;
import com.csci448.goldenrush.networkingpal.database.EventDbSchema.EventTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Sarah on 4/3/2017.
 */

public class EventCursorWrapper extends CursorWrapper {
    private static final String TAG = "EventCursorWrapper";
    public EventCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Event getEvent(){
        String uuidString = getString(getColumnIndex(EventTable.Cols.UUID));
        String title = getString(getColumnIndex(EventTable.Cols.TITLE));
        long date = getLong(getColumnIndex(EventTable.Cols.DATE));
        String time = getString(getColumnIndex(EventTable.Cols.TIME));
        String details = getString(getColumnIndex(EventTable.Cols.DETAILS));

        Log.d(TAG, "Event gotten - "+ title);
        Log.d(TAG, "Event date - "+ new Date(date).toString());
        Log.d(TAG, "Event time - "+ time);

        Event event = new Event (UUID.fromString(uuidString));
        event.setEventName(title);
        event.setmEventDate(new Date(date));
        event.setmTime(time);
        event.setmEventDetails(details);

        return event;

    }
}
