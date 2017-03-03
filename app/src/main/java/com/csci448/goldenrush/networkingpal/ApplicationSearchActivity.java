package com.csci448.goldenrush.networkingpal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.UUID;

/**
 * Created by Hayden on 2/28/17.
 */

public class ApplicationSearchActivity extends FragmentActivity implements ApplicationListFragment.Callbacks{

    private static String TAG = ApplicationSearchActivity.class.getSimpleName();
    private ImageButton mAddApplication;

    public static final String EXTRA_APPLICATION_ID = "com.csci448.goldenrush.networkingpal.application_id";



    public static Intent newIntent(Context packageContext, UUID applicationId){
        Intent intent = new Intent(packageContext, ApplicationSearchActivity.class);
        intent.putExtra(EXTRA_APPLICATION_ID, applicationId);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        Log.d(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_search);


        mAddApplication = (ImageButton) findViewById(R.id.add_newApp_Button);

        mAddApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = NewApplicationActivity.newIntent(ApplicationSearchActivity.this, null);
                startActivity(intent);
            }
        });
        /**
         * TODO wire up spinner
         * TODO wire up search field
         */

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_app_search_list_host);

        if (fragment == null){
            fragment = new ApplicationListFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_app_search_list_host, fragment)
                    .commit();
        }
    }
    /**
     * TODO make the apps a PagerView
     */

    @Override
    public void onAppSelected(Application application) {
        Log.d(TAG, "onAppSelected()");
        Intent intent = NewApplicationActivity.newIntent(this, application.getId());
        startActivity(intent);
    }
}
