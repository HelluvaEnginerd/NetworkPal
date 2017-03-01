package com.csci448.goldenrush.networkingpal;

import java.util.Date;

/**
 * Created by ddunmire on 2/27/2017.
 */

public class Application {
    private static String TAG = "Application";
    private String mName;
    private String mJobTitle;
    private String mCompanyContact;
    private Date mDateDue;

    public Application(String name, String jobTitle, String companyContact, Date dateDue){
        mName = name;
        mJobTitle = jobTitle;
        mCompanyContact = companyContact;
        mDateDue = dateDue;
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
}