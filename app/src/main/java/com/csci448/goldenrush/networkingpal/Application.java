package com.csci448.goldenrush.networkingpal;

import java.util.Date;
import java.util.UUID;

/**
 * Created by ddunmire on 2/27/2017.
 */

public class Application {
    private String mJobTitle;
    private String mCompanyContact;
    private Date mDateDue;
    private String mCompany;
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

    public String getCompany() {return mCompany;}

    public UUID getId(){return mID;}
}