package com.csci448.goldenrush.networkingpal;

import java.util.Date;
import java.util.UUID;

/**
 * Created by ddunmire on 2/27/2017.
 */

public class Application {
    private static String TAG = "Application";
    private String mJobTitle;
    private String mCompanyContact;
    private Date mDateDue;
    private String mCompany;
    private UUID mID;

    public void setJobTitle(String mJobTitle) {
        this.mJobTitle = mJobTitle;
    }

    public void setCompanyContact(String mCompanyContact) {
        this.mCompanyContact = mCompanyContact;
    }

    public void setDateDue(Date mDateDue) {
        this.mDateDue = mDateDue;
    }

    public void setCompany(String mCompany) {
        this.mCompany = mCompany;
    }

    private boolean mCoverLetter;
    private boolean mResume;
    private boolean mSubmitted;

    public boolean isCoverLetter() {
        return mCoverLetter;
    }

    public void setCoverLetter(boolean mCoverLetter) {
        this.mCoverLetter = mCoverLetter;
    }

    public boolean isResume() {
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

    public Application(){
        this(UUID.randomUUID());
    }

    public Application(UUID id){
        mID = id;
        mDateDue = new Date();
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

    public String getCompany() {return mCompany;}

    public UUID getId(){return mID;}
}