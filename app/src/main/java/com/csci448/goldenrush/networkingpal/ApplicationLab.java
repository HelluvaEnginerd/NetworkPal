package com.csci448.goldenrush.networkingpal;

/**
 * Created by Hayden on 2/28/17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.csci448.goldenrush.networkingpal.database.ApplicationBaseHelper;
import com.csci448.goldenrush.networkingpal.database.ApplicationCursorWrapper;
import com.csci448.goldenrush.networkingpal.database.ApplicationDbSchema;
import com.csci448.goldenrush.networkingpal.database.ApplicationDbSchema.ApplicationTable;
import com.csci448.goldenrush.networkingpal.database.EventDbSchema;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * v much in the style of 'CrimeLab'
 * basically a model for the application data.
 */
public class ApplicationLab {
    private static String TAG = "ApplicationLab";

    private static ApplicationLab sAppLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static ApplicationLab get(Context context){
        if (sAppLab == null){
            sAppLab = new ApplicationLab(context);
        }
        return sAppLab;
    }

    public int getNumberApps(){
        return getApps().size();
    }

    private ApplicationLab(Context context){
        Log.d(TAG, "ApplicationLab()");
        mContext = context.getApplicationContext();
        mDatabase = new ApplicationBaseHelper(mContext).getWritableDatabase();
    }

    public void addApplication(Application a){
        Log.d(TAG, "addApplication()");
        ContentValues values = getContentValues(a);
        mDatabase.insert(ApplicationTable.NAME, null, values);
    }

    /**
     * This function needs some work....
     * @return
     */
    public Application getNextApp(){
        Date date = new Date();
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDay();
        Application b = new Application();
        List<Application> apps = getApps();
        for(Application a: apps){
            Log.d(TAG, "next app:" + a.getCompanyName());
            int y = a.getDateDue().getYear();
            int m = a.getDateDue().getMonth();
            int d = a.getDateDue().getDate();
            if(y > year && m > month && d > day){
                b = a;
            }
        }
        Log.d(TAG, "getNextApp: " + b.getCompanyName());
        if (b.getCompanyName() == null)
            return apps.get(0);
        return b;
    }

    public void deleteApplication(UUID id){
        Log.d(TAG,"Record to delete: "+ id.toString());
        mDatabase.delete(ApplicationDbSchema.ApplicationTable.NAME, ApplicationDbSchema.ApplicationTable.Cols.UUID + " = ?",
                new String[]{id.toString()});
    }

    public Application getApplication(UUID id){
        Log.d(TAG, "getApplication()");
        ApplicationCursorWrapper cursor = queryCrimes(
                ApplicationTable.Cols.UUID + " = ?",
                new String[] {id.toString()}
        );

        try {
            if(cursor.getCount()==0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getApplication();
        } finally {
            cursor.close();
        }
    }

    public void updateApplication(Application a){
        Log.d(TAG, "updateApplication()");
        String uuidString = a.getId().toString();
        Log.d(TAG, "UUID = " + a.getId().toString());
        ContentValues values = getContentValues(a);
        Log.d(TAG, "job title: " + a.getJobTitle());

        mDatabase.update(ApplicationTable.NAME, values, ApplicationTable.Cols.UUID + " = ?", new String[] {uuidString});
    }

    public List<Application> getApps() {
        Log.d(TAG, "getApps()");
        List<Application> applications = new ArrayList<>();

        ApplicationCursorWrapper cursor = queryCrimes(null, null);

        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                applications.add(cursor.getApplication());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return applications;
    }

    private static ContentValues getContentValues(Application application){
        Log.d(TAG, "getContentValues()");
        ContentValues values = new ContentValues();
        values.put(ApplicationTable.Cols.TITLE, application.getJobTitle());
        values.put(ApplicationTable.Cols.CONTACT, application.getCompanyContact());
        values.put(ApplicationTable.Cols.DATE, application.getDateDue().getTime());
        values.put(ApplicationTable.Cols.COMPANYNAME, application.getCompanyName());
        values.put(ApplicationTable.Cols.UUID, application.getId().toString());
        values.put(ApplicationTable.Cols.COVER, application.hasCoverLetter());
        values.put(ApplicationTable.Cols.RESUME, application.hasResume());
        values.put(ApplicationTable.Cols.SUBMITTED, application.isSubmitted());

        return values;
    }

    private ApplicationCursorWrapper queryCrimes(String whereClause, String[] whereArgs){
        Log.d(TAG, "queryCrimes()");
        Cursor cursor = mDatabase.query(
                ApplicationTable.NAME,
                null, //Columns - null selects all columns
                whereClause,
                whereArgs,
                null, //groupBy
                null, //having
                null //orderBy
        );

        return new ApplicationCursorWrapper(cursor);
    }
}

