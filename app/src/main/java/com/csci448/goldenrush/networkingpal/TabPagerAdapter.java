package com.csci448.goldenrush.networkingpal;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Hayden on 3/25/17.
 */

public class TabPagerAdapter extends FragmentStatePagerAdapter{

    int mNumOfTabs;

    public TabPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ContactListFragment contactsTab = new ContactListFragment();
                return contactsTab;
            case 1:
                CompanyListFragment companiesTab = new CompanyListFragment();
                return companiesTab;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}