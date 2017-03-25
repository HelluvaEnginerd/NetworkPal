package com.csci448.goldenrush.networkingpal;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hayden on 3/25/17.
 */

public class CompanyLab {
    private static CompanyLab sCompanyLab;

    private List<Company> mCompanies;
    private Context mContext;

    public static CompanyLab get(Context context){
        if (sCompanyLab == null){
            sCompanyLab = new CompanyLab(context);
        }
        return sCompanyLab;
    }

    private CompanyLab(Context context) {
        mContext = context.getApplicationContext();
        mCompanies = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            Company company = new Company();
            company.setCompanyName("Company: " + i);
            company.setPhoneNumber("Number: " + i);
            mCompanies.add(company);
        }
    }

    public List<Company> getCompanies() {
        return mCompanies;
    }
}
