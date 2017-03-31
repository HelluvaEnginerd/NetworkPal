package com.csci448.goldenrush.networkingpal;

import android.content.Intent;

import java.io.IOError;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Hayden on 3/2/17.
 */

public class RecentAction {
    private String mCategory;
    private String mName;
    private Date mDate;
    private String mCompany;
    private UUID mUUID;

    RecentAction(String category, String name, Date date, String company){
        mUUID = UUID.randomUUID();
        mName = name;
        mCategory = category;
        mDate = date;
        mCompany = company;
    }

    public RecentAction(UUID uuid){
        mUUID = uuid;
    }

    public RecentAction(){this(UUID.randomUUID());}

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

    public void setCategory(String category) {
        switch (category.toUpperCase()) {
            case "CONTACT":
                mCategory = "Contact";
                break;
            case "COMPANY":
                mCategory = "Company";
                break;
            case "APPLICATION":
                mCategory = "Application";
                break;
            case "EVENT":
                mCategory = "Event";
                break;
            default:
                /**
                 * TODO throw and catch exception so we know what happened. Block entering the queue
                 */
                mCategory = "Default";
                break;
        }
    }

    public void setName(String name) {
        mName = name;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public void setDate(String strDate){
        /**
         * TODO parse string date for date
         */
    }

    public void setCompany(String company) {
        mCompany = company;
    }
}
