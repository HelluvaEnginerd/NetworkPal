package com.csci448.goldenrush.networkingpal;

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

    public Contact(String contactName, String companyName, String email, String phone, String title){
        mCompanyName = companyName;
        mContactName = contactName;
        mEmail = email;
        mPhone = phone;
        mTitle = title;
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
}