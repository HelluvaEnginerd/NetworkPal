package com.csci448.goldenrush.networkingpal;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Hayden on 3/2/17.
 */

public class RecentActivity{
    private String mCategory;
    private String mName;
    private Date mDate;
    private String mCompany;
    private UUID mUUID;


    RecentActivity(String category, String name, Date date, String company){
        mUUID = UUID.randomUUID();
        mName = name;
        mCategory = category;
        mDate = date;
        mCompany = company;
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
}
