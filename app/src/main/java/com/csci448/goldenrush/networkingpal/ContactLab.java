package com.csci448.goldenrush.networkingpal;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 3/3/17.
 */

public class ContactLab {
    private static final String TAG = "ContactLab";

    private static ContactLab sContactLab;
    private List<Contact> mContacts;
    private Context mContext;

    public static ContactLab get(Context context){
        Log.d(TAG, "get()");
        if (sContactLab == null){
            sContactLab = new ContactLab(context);
        }
        return sContactLab;
    }

    private ContactLab(Context context) {
        Log.d(TAG, "ContactLab()");
        mContext = context.getApplicationContext();
        mContacts = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            Contact contact = new Contact("Contact " + String.valueOf(i),
                    "Company " + String.valueOf(i),
                    "contact" + String.valueOf(i) + "@mail.com",
                    "867-5309",
                    "Title " + String.valueOf(i));
            mContacts.add(contact);
        }
    }

    public List<Contact> getContacts() {
        return mContacts;
    }

}
