package com.csci448.goldenrush.networkingpal.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.csci448.goldenrush.networkingpal.Application;
import com.csci448.goldenrush.networkingpal.Company;
import com.csci448.goldenrush.networkingpal.database.CompanyDbSchema.CompanyTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Hayden on 3/25/17.
 */

public class CompanyCursorWrapper extends CursorWrapper {

    public CompanyCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Company getCompany(){
        String uuidString = getString(getColumnIndex(CompanyTable.Cols.UUID));
        String companyName = getString(getColumnIndex(CompanyTable.Cols.COMPANYNAME));
        String phoneNumber = getString(getColumnIndex(CompanyTable.Cols.NUMBER));
        String address = getString(getColumnIndex(CompanyTable.Cols.ADDRESS));

        Company company = new Company(UUID.fromString(uuidString));
        company.setCompanyName(companyName);
        company.setPhoneNumber(phoneNumber);
        company.setAddress(address);

        return company;
    }
}
