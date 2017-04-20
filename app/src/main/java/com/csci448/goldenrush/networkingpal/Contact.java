package com.csci448.goldenrush.networkingpal;

import java.util.UUID;

/**
 * Created by ddunmire on 2/27/2017.
 */

public class Contact {
    private static String TAG = "Contact";
    private String mContactName;
    private String mCompanyName;
    private String mEmail;
    private String mPhone;
    private String mTitle;
    private UUID mUUID;
    private long mPHOTOID;

    public Contact(String contactName, String companyName, String email, String phone, String title){
        mUUID = UUID.randomUUID();
        mCompanyName = companyName;
        mContactName = contactName;
        mEmail = email;
        mPhone = phone;
        mTitle = title;
    }

    public Contact(UUID id){
        mUUID = id;
        mContactName = "John Doe";
        mCompanyName = "Some Company";
        mEmail = "jdoe@example.com";
        mPhone = "555-555-5555";
        mTitle = "Some Title";
        mPHOTOID = Long.MIN_VALUE;
    }

    public String getFirstName(){
        if(mContactName.contains(" ")){
            String firstName= mContactName.substring(0, mContactName.indexOf(" "));
            return firstName;
        }
        return mContactName;
    }

    public Contact(){
        this(UUID.randomUUID());
    }

    public String getContactName() {
        return mContactName;
    }

    public void setContactName(String contactName) {
        mContactName = contactName;
    }

    public String getCompanyName() {
        return mCompanyName;
    }

    public void setCompanyName(String companyName) {
        mCompanyName = companyName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public long getPHOTOID() {
        return mPHOTOID;
    }

    public void setPHOTOID(long id) {
        mPHOTOID = id;
    }

    public UUID getUUID() {
        return mUUID;
    }

    public void setUUID(UUID UUID) {
        mUUID = UUID;
    }
}