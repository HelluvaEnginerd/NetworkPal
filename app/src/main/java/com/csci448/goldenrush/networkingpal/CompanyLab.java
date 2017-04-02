package com.csci448.goldenrush.networkingpal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.csci448.goldenrush.networkingpal.database.CompanyBaseHelper;
import com.csci448.goldenrush.networkingpal.database.CompanyCursorWrapper;
import com.csci448.goldenrush.networkingpal.database.CompanyDbSchema.CompanyTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Hayden on 3/25/17.
 */

public class CompanyLab {
    private static final String TAG = "CompanyLab";
    private static CompanyLab sCompanyLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CompanyLab get(Context context){
        Log.d(TAG, "get()");
        if (sCompanyLab == null){
            sCompanyLab = new CompanyLab(context);
        }
        return sCompanyLab;
    }

    private CompanyLab(Context context) {
        Log.d(TAG, "CompanyLab()");
        mContext = context.getApplicationContext();
        mDatabase = new CompanyBaseHelper(mContext).getWritableDatabase();
    }

    public void addCompany(Company c){
        Log.d(TAG, "addCompany()");
        ContentValues values = getContentValues(c);
        mDatabase.insert(CompanyTable.NAME, null, values);
    }

    public Company getCompany(UUID id){
        Log.d(TAG, "getCompany()");
        CompanyCursorWrapper cursor = queryCompanies(CompanyTable.Cols.UUID + " = ?", new String[] {id.toString()});

        try {
            if (cursor.getCount() == 0)
                return null;
            cursor.moveToFirst();
            return cursor.getCompany();
            } finally {
            cursor.close();
        }
    }

    public List<Company> getCompanies() {
        Log.d(TAG, "getCompanies()");
        List<Company> companies = new ArrayList<>();

        CompanyCursorWrapper cursor = queryCompanies(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                companies.add(cursor.getCompany());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return companies;
    }

    private static ContentValues getContentValues(Company company){
        Log.d(TAG, "getContentValues()");
        ContentValues values = new ContentValues();
        values.put(CompanyTable.Cols.UUID, company.getID().toString());
        values.put(CompanyTable.Cols.ADDRESS, company.getAddress());
        values.put(CompanyTable.Cols.COMPANYNAME, company.getCompanyName());
        values.put(CompanyTable.Cols.NUMBER, company.getPhoneNumber());

        return values;
    }

    public void updateCompany(Company company){
        Log.d(TAG, "updateCompany()");
        String uuidString = company.getID().toString();
        ContentValues values = getContentValues(company);

        mDatabase.update(CompanyTable.NAME, values, CompanyTable.Cols.UUID + " = ?", new String[] {uuidString});
    }

    private CompanyCursorWrapper queryCompanies(String whereClause, String[] whereArgs){
        Log.d(TAG, "queryCompanies()");
        Cursor cursor = mDatabase.query(
                CompanyTable.NAME,
                null, //columns - null selects all
                whereClause,
                whereArgs,
                null, // groupby
                null, //having
                null //orderby
        );
        return new CompanyCursorWrapper(cursor);
    }
}
