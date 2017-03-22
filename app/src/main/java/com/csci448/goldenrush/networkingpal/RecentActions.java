package com.csci448.goldenrush.networkingpal;

import android.content.Intent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Hayden on 3/2/17.
 */

public class RecentActions {
    private String mCategory;
    private String mName;
    private Date mDate;
    private String mCompany;
    private UUID mUUID;
    private Intent mIntent;


    RecentActions(String category, String name, Date date, String company, Intent intent){
        mUUID = UUID.randomUUID();
        mName = name;
        mCategory = category;
        mDate = date;
        mCompany = company;
        mIntent = intent;
    }

    public String getCategory() {
        return mCategory;
    }

    public String getName() {
        return mName;
    }

    public Date getDate() {
        return mDate;
    }

    public String getCompany() {
        return mCompany;
    }

    public UUID getUUID() {
        return mUUID;
    }

    public Intent getIntent(){ return mIntent; }
}
