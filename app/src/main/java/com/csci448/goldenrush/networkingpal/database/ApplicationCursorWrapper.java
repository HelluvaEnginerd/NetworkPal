package com.csci448.goldenrush.networkingpal.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.csci448.goldenrush.networkingpal.Application;

import java.sql.Date;
import java.util.UUID;

/**
 * Created by ddunmire on 3/21/2017.
 */

public class ApplicationCursorWrapper extends CursorWrapper {

    public ApplicationCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Application getApplication(){
        String uuidString = getString(getColumnIndex(ApplicationDbSchema.ApplicationTable.Cols.UUID));
        String title = getString(getColumnIndex(ApplicationDbSchema.ApplicationTable.Cols.TITLE));
        String company = getString(getColumnIndex(ApplicationDbSchema.ApplicationTable.Cols.COMPANY));
        String contact = getString(getColumnIndex(ApplicationDbSchema.ApplicationTable.Cols.CONTACT));
        long date = getLong(getColumnIndex(ApplicationDbSchema.ApplicationTable.Cols.DATE));
        int cover = getInt(getColumnIndex(ApplicationDbSchema.ApplicationTable.Cols.COVER));
        int resume = getInt(getColumnIndex(ApplicationDbSchema.ApplicationTable.Cols.RESUME));
        int submitted = getInt(getColumnIndex(ApplicationDbSchema.ApplicationTable.Cols.SUBMITTED));

        Application application = new Application(UUID.fromString(uuidString));
        application.setJobTitle(title);
        application.setCompany(company);
        application.setCompanyContact(contact);
        application.setDateDue(new Date(date));
        application.setCoverLetter(cover!=0);
        application.setResume(resume!=0);
        application.setSubmitted(submitted!=0);

        return application;
    }
}
