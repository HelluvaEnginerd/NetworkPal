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
    private String mCompany;

    public Application(String name, String jobTitle, String companyContact, Date dateDue, String company){
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
}