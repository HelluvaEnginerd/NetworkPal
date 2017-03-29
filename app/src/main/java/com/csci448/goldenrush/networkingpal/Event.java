package com.csci448.goldenrush.networkingpal;

import java.util.UUID;

/**
 * Created by Sarah on 3/29/2017.
 */

public class Event {
    //instance variables
    private UUID mId;
    private String mEventName;

    public Event(){
        mId =UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public String getEventName() {
        return mEventName;
    }

    public void setEventName(String mEventName) {
        this.mEventName = mEventName;
    }
}
