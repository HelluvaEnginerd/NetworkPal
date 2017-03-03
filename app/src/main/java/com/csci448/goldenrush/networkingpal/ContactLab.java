package com.csci448.goldenrush.networkingpal;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 3/3/17.
 */

public class ContactLab {
    private static ContactLab sContactLab;
    private List<Contact> mContacts;
    private Context mContext;

    public static ContactLab get(Context context){
        if (sContactLab == null){
            sContactLab = new ContactLab(context);
        }
        return sContactLab;
    }

    private ContactLab(Context context) {
        mContext = context.getApplicationContext();
        mContacts = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            Contact contact = new Contact("Contact " + String.valueOf(i),
                    "Company " + String.valueOf(i),
                    "contact" + String.valueOf(i) + "@mail.com",
                    "858-335-5555",
                    "Title " + String.valueOf(i));
            mContacts.add(contact);
        }
    }

    public List<Contact> getContacts() {
        return mContacts;
    }

}
