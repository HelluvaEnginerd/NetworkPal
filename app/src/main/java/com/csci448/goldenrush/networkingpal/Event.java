package com.csci448.goldenrush.networkingpal;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Sarah on 3/29/2017.
 */

public class Event {
    //instance variables
    private UUID mId;
    private String mEventName;
    private String mEventDetails;
    private Date mEventDate;
    private String mTime;

    public Event(){
        this(UUID.randomUUID());
    }

    public Event(String eventName, String eventDetails, String time, Date eventDate){
        mId = UUID.randomUUID();
        mEventName = eventName;
        mEventDetails = eventDetails;
        mTime = time;
        mEventDate = eventDate;
    }

    public Event(UUID id){
        mId =id;
        mEventName="Event #";
        mTime = "12:00 pm";
        mEventDate = new Date();
        mEventDetails = "Enter Details";
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

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public Date getmEventDate() {
        return mEventDate;
    }

    public void setmEventDate(Date mEventDate) {
        this.mEventDate = mEventDate;
    }
}
