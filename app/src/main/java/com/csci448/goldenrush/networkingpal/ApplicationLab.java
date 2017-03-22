package com.csci448.goldenrush.networkingpal;

/**
 * Created by Hayden on 2/28/17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.csci448.goldenrush.networkingpal.database.ApplicationBaseHelper;
import com.csci448.goldenrush.networkingpal.database.ApplicationDbSchema;

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

    private ApplicationLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new ApplicationBaseHelper(mContext).getWritableDatabase();
        /**
         * TODO remove fake jobs
         */

       /* Date date = new Date();
        for (int i = 0; i < 50; i++) {
            String jobString = "Job: " + Integer.toString(i);
            Application app = new Application("App " + Integer.toString(i), jobString, "Contact", date, "Company " + Integer.toString(i));
            mApps.add(app);
        }*/
    }
    public void addApplication(Application a){
        ContentValues values = getContentValues(a);
        mDatabase.insert(ApplicationDbSchema.ApplicationTable.NAME, null, values);
    }

    public Application getApplication(UUID id){
        return null;
    }

    public void updateApplication(Application a){
        String uuidString = a.getId().toString();
        ContentValues values = getContentValues(a);

        mDatabase.update(ApplicationDbSchema.ApplicationTable.NAME, values, ApplicationDbSchema.ApplicationTable.Cols.UUID + " = ?", new String[] {uuidString});
    }

    public List<Application> getApps() {
        return new ArrayList<>();
    }

    private static ContentValues getContentValues(Application application){
        ContentValues values = new ContentValues();
        values.put(ApplicationDbSchema.ApplicationTable.Cols.NAME, application.getName());
        values.put(ApplicationDbSchema.ApplicationTable.Cols.TITLE, application.getJobTitle());
        values.put(ApplicationDbSchema.ApplicationTable.Cols.CONTACT, application.getCompanyContact());
        values.put(ApplicationDbSchema.ApplicationTable.Cols.DATE, application.getDateDue().getTime());
        values.put(ApplicationDbSchema.ApplicationTable.Cols.COMPANY, application.getCompany());
        values.put(ApplicationDbSchema.ApplicationTable.Cols.UUID, application.getId().toString());

        return values;
    }
}

