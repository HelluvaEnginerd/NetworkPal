package com.csci448.goldenrush.networkingpal.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.csci448.goldenrush.networkingpal.Application;
import com.csci448.goldenrush.networkingpal.database.ApplicationDbSchema.ApplicationTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by ddunmire on 3/21/2017.
 */

public class ApplicationCursorWrapper extends CursorWrapper {

    public ApplicationCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Application getApplication(){
        String uuidString = getString(getColumnIndex(ApplicationTable.Cols.UUID));
        String title = getString(getColumnIndex(ApplicationTable.Cols.TITLE));
        String company = getString(getColumnIndex(ApplicationTable.Cols.COMPANY));
        String contact = getString(getColumnIndex(ApplicationTable.Cols.CONTACT));
        long date = getLong(getColumnIndex(ApplicationTable.Cols.DATE));
        int cover = getInt(getColumnIndex(ApplicationTable.Cols.COVER));
        int resume = getInt(getColumnIndex(ApplicationTable.Cols.RESUME));
        int submitted = getInt(getColumnIndex(ApplicationTable.Cols.SUBMITTED));

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
