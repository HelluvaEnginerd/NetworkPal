package com.csci448.goldenrush.networkingpal;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Hayden on 3/21/17.
 */

public class Company {
    private static String TAG = "Company";
    private String mCompany;
    private UUID mID;
    private String mPhoneNumber;

    public Company(String company, String phoneNumber){
        mID = UUID.randomUUID();
        mCompany = company;
        mPhoneNumber = phoneNumber;
    }

    public String getCompany() {
        return mCompany;
    }

    public UUID getID() {
        return mID;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }
}
