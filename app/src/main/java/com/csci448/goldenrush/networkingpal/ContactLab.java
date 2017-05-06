package com.csci448.goldenrush.networkingpal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.csci448.goldenrush.networkingpal.database.ContactBaseHelper;
import com.csci448.goldenrush.networkingpal.database.ContactCursorWrapper;
import com.csci448.goldenrush.networkingpal.database.ContactDbSchema;
import com.csci448.goldenrush.networkingpal.database.ContactDbSchema.ContactTable;
import com.csci448.goldenrush.networkingpal.database.EventDbSchema;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

/**
 * Created by Nick on 3/3/17.
 */

public class ContactLab {
    private static final String TAG = "ContactLab";

    private static ContactLab sContactLab;
    private List<Contact> mContacts;
    private Context mContext;
    private SQLiteDatabase mDatabase;

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
        mDatabase = new ContactBaseHelper(mContext).getWritableDatabase();
    }

    public void addContact(Contact contact) {
        Log.d(TAG, "addContact()");
        ContentValues values = getContentValues(contact);
        mDatabase.insert(ContactTable.NAME, null, values);
    }

    public int getNumberContacts(){
        return getContacts().size();
    }

    public Contact getContact(UUID uuid) {
        Log.d(TAG, "getContact()");
        ContactCursorWrapper cursor = queryContacts(
                ContactTable.Cols.UUID + " = ?",
                new String[] {uuid.toString()}
        );

        try {
            if(cursor.getCount()==0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getContact();
        } finally {
            cursor.close();
        }
    }

    public void updateContact(Contact contact) {
        Log.d(TAG, "updateContact()");
        String uuidString = contact.getUUID().toString();
        Log.d(TAG, "UUID = " + contact.getUUID().toString());
        ContentValues values = getContentValues(contact);

        mDatabase.update(ContactTable.NAME, values, ContactTable.Cols.UUID + " = ?", new String[] {uuidString});
    }

    public void deleteContact(UUID id){
        Log.d(TAG,"Record to delete: "+ id.toString());
        mDatabase.delete(ContactDbSchema.ContactTable.NAME, ContactDbSchema.ContactTable.Cols.UUID + " = ?",
                new String[]{id.toString()});
    }

    public List<Contact> getContacts() {
        Log.d(TAG, "getContacts()");
        final List<Contact> contacts = new ArrayList<Contact>();

        ContactCursorWrapper cursor = queryContacts(null, null);

        try {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                Log.d(TAG, "contact: " + cursor.getContact().getFirstName() + " added");
                contacts.add(cursor.getContact());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return contacts;
    }

    private static ContentValues getContentValues(Contact contact){
        Log.d(TAG, "getContentValues()");
        ContentValues values = new ContentValues();
        values.put(ContactTable.Cols.NAME, contact.getContactName());
        values.put(ContactTable.Cols.COMPANY, contact.getCompanyName());
        values.put(ContactTable.Cols.EMAIL, contact.getEmail());
        values.put(ContactTable.Cols.PHONE, contact.getPhone());
        values.put(ContactTable.Cols.UUID, contact.getUUID().toString());
        values.put(ContactTable.Cols.TITLE, contact.getTitle());
        values.put(ContactTable.Cols.PHOTOID, contact.getPHOTOID());

        return values;
    }

    private ContactCursorWrapper queryContacts(String whereClause, String[] whereArgs){
        Log.d(TAG, "queryCrimes()");
        Cursor cursor = mDatabase.query(
                ContactTable.NAME,
                null, //Columns - null selects all columns
                whereClause,
                whereArgs,
                null,  // groupBy
                null,  // having
                null   // orderBy
        );
        return new ContactCursorWrapper(cursor);
    }

    public File getPhotoFile(Contact contact){
        File externalFilesDir = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        if(externalFilesDir == null){
            return null;
        }
        return new File(externalFilesDir, contact.getPhotoFilename());
    }

}
