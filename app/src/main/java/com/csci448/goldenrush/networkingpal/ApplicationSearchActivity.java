package com.csci448.goldenrush.networkingpal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by Hayden on 2/28/17.
 */

public class ApplicationSearchActivity extends FragmentActivity{

    public static Intent newIntent(Context packageContext){
        Intent intent = new Intent(packageContext, ApplicationSearchActivity.class);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
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
}
