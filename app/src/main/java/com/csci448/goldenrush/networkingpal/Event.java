package com.csci448.goldenrush.networkingpal;

import java.util.UUID;

/**
 * Created by Sarah on 3/29/2017.
 */

public class Event {
    private static String EMPTY_FIELD = "BLANK";
    //instance variables
    private UUID mId;
    private String mEventName;
    private String mEventDetails;

    public Event(){
        mId =UUID.randomUUID();
        mEventDetails= EMPTY_FIELD;
        mEventName = EMPTY_FIELD;
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

    public String getmEventDetails() {
        return mEventDetails;
    }

    public void setmEventDetails(String mEventDetails) {
        this.mEventDetails = mEventDetails;
    }
}
