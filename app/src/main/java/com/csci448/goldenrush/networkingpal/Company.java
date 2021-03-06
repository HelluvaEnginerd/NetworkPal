package com.csci448.goldenrush.networkingpal;

import android.util.Log;

import java.util.UUID;

/**
 * Created by Hayden on 3/21/17.
 */

public class Company {
    private static String TAG = "Company";
    private String mCompanyName;
    private UUID mID;
    private String mPhoneNumber;
    private String mAddress;
    private String mContact;

    public String getContact() {
        return mContact;
    }

    public void setContact(String contact) {
        mContact = contact;
    }

    public Company(String companyName, String phoneNumber, String address){
        mID = UUID.randomUUID();
        mCompanyName = companyName;
        mPhoneNumber = phoneNumber;
        mAddress = address;
    }

    public Company(UUID uuid){
        mID = uuid;
        Log.d(TAG, "new Company UUID: " + uuid.toString());
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

    public void setID(UUID ID) {
        mID = ID;
    }
}
