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
    private int mHour;
    private int mMin;


    public Event(){
        this(UUID.randomUUID());
    }

    public Event(String eventName, String eventDetails, String time, Date eventDate){
        mId = UUID.randomUUID();
        mEventName = eventName;
        mEventDetails = eventDetails;
        mTime = time;
        mEventDate = eventDate;
        mHour = 8;
        mMin=0;

    }

    public Event(UUID id){
        mId =id;
        mEventName="Event #";
        mTime = "12:00 pm";
        mEventDate = new Date();
        mEventDetails = "Enter Details";
        mHour =8 ;
        mMin=0;

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

    public int getmHour() {
        return mHour;
    }

    public void setmHour(int mHour) {
        this.mHour = mHour;
    }

    public int getmMin() {
        return mMin;
    }

    public void setmMin(int mMin) {
        this.mMin = mMin;
    }


}
