package com.csci448.goldenrush.networkingpal;

import android.util.Log;

import java.util.Date;
import java.util.UUID;

/**
 * Created by ddunmire on 2/27/2017.
 */

public class Application {
    private static final String TAG = "Application";
    private String mJobTitle;
    private String mCompanyContact;
    private Date mDateDue;
    private String mCompanyName;
    private UUID mID;
    private boolean mCoverLetter;
    private boolean mResume;
    private boolean mSubmitted;

    public Application(UUID id){
        mID = id;
        mDateDue = new Date();
        mResume = false;
        mCoverLetter = false;
        mSubmitted = false;
        mDateDue = new Date();
    }

    public Application(){
        this(UUID.randomUUID());
    }

    public Application(String jobTitle, String companyName, String companyContact){
        mJobTitle = jobTitle;
        mCompanyName = companyName;
        mCompanyContact = companyContact;
        mID = UUID.randomUUID();
        mDateDue = new Date();
        mResume = false;
        mCoverLetter = false;
        mSubmitted = false;
        mDateDue = new Date();
    }

    public void setJobTitle(String jobTitle) {
        Log.d(TAG, "setJobTitle(" + jobTitle + ")");
        this.mJobTitle = jobTitle;
    }

    public void setCompanyContact(String mCompanyContact) {
        this.mCompanyContact = mCompanyContact;
    }

    public void setDateDue(Date mDateDue) {
        this.mDateDue = mDateDue;
    }

    public void setCompanyName(String companyName) {
        this.mCompanyName = companyName;
    }

    public boolean hasCoverLetter() {
        return mCoverLetter;
    }

    public void setCoverLetter(boolean mCoverLetter) {
        this.mCoverLetter = mCoverLetter;
    }

    public boolean hasResume() {
        return mResume;
    }

    public void setResume(boolean mResume) {
        this.mResume = mResume;
    }

    public boolean isSubmitted() {
        return mSubmitted;
    }

    public void setSubmitted(boolean mSubmitted) {
        this.mSubmitted = mSubmitted;
    }

    public String getJobTitle(){
        return mJobTitle;
    }

    public String getCompanyContact(){
        return mCompanyContact;
    }

    public Date getDateDue(){
        return mDateDue;
    }

    public String  getCompanyName() {return mCompanyName;}

    public UUID getId(){return mID;}

    public void setID(UUID ID) {
        mID = ID;
    }
}