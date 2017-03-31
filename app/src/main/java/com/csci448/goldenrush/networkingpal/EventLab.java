package com.csci448.goldenrush.networkingpal;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Sarah on 3/29/2017.
 */

public class EventLab {
    private static EventLab sEventLab;
    private List<Event> mEvents;
    public static EventLab get(Context context){
        if(sEventLab == null){
            sEventLab = new EventLab(context);
        }
        return sEventLab;

    }

    private EventLab(Context context){
        mEvents = new ArrayList<>();
        for(int i = 0; i<50; i++){
            Event event = new Event();
            event.setEventName("Event #"+i);
            //add incrementing dates eventually....
            mEvents.add(event);
        }
    }

    public List<Event> getEvents(){
        return mEvents;
    }

    public Event getEvent(UUID id){
        for (Event event: mEvents){
            if(event.getId().equals(id))
            {
                return event;
            }
        }
        return null;
    }
}
