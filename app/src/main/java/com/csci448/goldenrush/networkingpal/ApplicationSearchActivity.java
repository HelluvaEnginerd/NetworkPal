package com.csci448.goldenrush.networkingpal;

import android.content.Context;
import android.content.Intent;
import android.icu.text.LocaleDisplayNames;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

/**
 * Created by Hayden on 2/28/17.
 */

public class ApplicationSearchActivity extends FragmentActivity{

    private static String TAG = ApplicationSearchActivity.class.getSimpleName();

    public static Intent newIntent(Context packageContext){
        Intent intent = new Intent(packageContext, ApplicationSearchActivity.class);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        Log.d(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_search);

        /**
         * TODO wire up spinner
         * TODO wire up search field
         */

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_list_host);

        if (fragment == null){
            fragment = new ApplicationListFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_list_host, fragment)
                    .commit();
        }
    }
    /**
     * TODO add application UUIDs and make the list go into the newApplicationView
     */
/*
    @Override
    public void onAppSelected(Application application) {
        Log.d(TAG, "onAppSelected()");
        Log.d(TAG, "onCrimeSelected()");
        if (findViewById(R.id.detail_fragment_container) == null) {
            Intent intent = CrimePagerActivity.newIntent(this, crime.getID());
            startActivity(intent);
        } else {
            Fragment newDetail = CrimeFragment.newInstance(crime.getID());

            getSupportFragmentManager().beginTransaction().replace(R.id.detail_fragment_container, newDetail)
                    .commit();
        }
    }
    */
}
