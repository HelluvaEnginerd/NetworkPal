package com.csci448.goldenrush.networkingpal;

import java.util.UUID;

/**
 * Created by Hayden on 3/21/17.
 */

public class Company {
    private static String TAG = "Company";
    public static String EMPTY_FIELD = "BLANK";
    private String mCompanyName;
    private UUID mID;
    private String mPhoneNumber;
    private String mAddress;

    public Company(String companyName, String phoneNumber, String address){
        mID = UUID.randomUUID();
        mCompanyName = companyName;
        mPhoneNumber = phoneNumber;
        mAddress = address;
    }

    public Company(UUID uuid){
        mID = uuid;
        mCompanyName = EMPTY_FIELD;
        mPhoneNumber = EMPTY_FIELD;
        mAddress = EMPTY_FIELD;
    }

    public Company(){this(UUID.randomUUID());}

    public String getCompanyName() {
        return mCompanyName;
    }

    public UUID getID() {
        return mID;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setCompanyName(String company) {mCompanyName = company;}

    public void setPhoneNumber(String phoneNumber) {mPhoneNumber = phoneNumber;}

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }
}
