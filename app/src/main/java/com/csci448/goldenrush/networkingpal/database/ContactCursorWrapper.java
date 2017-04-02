package com.csci448.goldenrush.networkingpal.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.csci448.goldenrush.networkingpal.Contact;
import com.csci448.goldenrush.networkingpal.database.ContactDbSchema.ContactTable;

import java.util.UUID;

/**
 * Created by Nick on 4/1/17.
 */

public class ContactCursorWrapper extends CursorWrapper {

    public ContactCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Contact getContact() {
        String name = getString(getColumnIndex(ContactTable.Cols.NAME));
        String company = getString(getColumnIndex(ContactTable.Cols.COMPANY));
        String email = getString(getColumnIndex(ContactTable.Cols.EMAIL));
        String phone = getString(getColumnIndex(ContactTable.Cols.PHONE));
        String title = getString(getColumnIndex(ContactTable.Cols.TITLE));
        long photoid = getLong(getColumnIndex(ContactTable.Cols.PHOTOID));
        String uuid = getString(getColumnIndex(ContactTable.Cols.UUID));

        // We'll parse as needed later

        Contact contact = new Contact(UUID.fromString(uuid));
        contact.setContactName(name);
        contact.setCompanyName(company);
        contact.setEmail(email);
        contact.setPhone(phone);
        contact.setTitle(title);
        contact.setPHOTOID(photoid);

        return contact;
    }

}
