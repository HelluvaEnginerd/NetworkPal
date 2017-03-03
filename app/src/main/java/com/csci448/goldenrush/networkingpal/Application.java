package com.csci448.goldenrush.networkingpal;

import java.util.Date;
import java.util.UUID;

/**
 * Created by ddunmire on 2/27/2017.
 */

public class Application {
    private static String TAG = "Application";
    private String mName;
    private String mJobTitle;
    private String mCompanyContact;
    private Date mDateDue;
    private String mCompany;
    private UUID mID;

    public Application(String name, String jobTitle, String companyContact, Date dateDue, String company){
        mID = UUID.randomUUID();
        mName = name;
        mJobTitle = jobTitle;
        mCompanyContact = companyContact;
        mDateDue = dateDue;
        mCompany = company;
    }

    public String getName(){
        return mName;
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