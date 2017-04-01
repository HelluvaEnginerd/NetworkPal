package com.csci448.goldenrush.networkingpal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import android.support.design.widget.FloatingActionButton;

/**
 * Created by Hayden on 2/28/17.
 */

public class ApplicationSearchActivity extends AppCompatActivity{

    private static String TAG = ApplicationSearchActivity.class.getSimpleName();
    private FloatingActionButton mAddApplication;
    SearchView mSearchView;

    public static final String EXTRA_APPLICATION_ID = "com.csci448.goldenrush.networkingpal.application_id";



    public static Intent newIntent(Context packageContext){
        Log.d(TAG, "newIntent()");
        Intent intent = new Intent(packageContext, ApplicationSearchActivity.class);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        Log.d(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_search);

        mAddApplication = (FloatingActionButton) findViewById(R.id.fab_add_application);
        mAddApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "FAB add application");
                Intent intent = NewApplicationActivity.newIntent(ApplicationSearchActivity.this, null);
                startActivity(intent);
            }
        });
        /**
         * TODO wire up spinner
         * TODO wire up search field


        mSearchView = (SearchView) findViewById(R.id.search_view);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {

                query = query.toString().toLowerCase();

                final List<Application> filteredList = new ArrayList<>();
                List<Application> list = ApplicationLab.get(getApplicationContext()).getApps();

                for (int i = 0; i < list.size(); i++) {

                    final String text = list.get(i).getJobTitle().toLowerCase();
                    if (text.contains(query)) {
                        filteredList.add(list.get(i));
                    }
                }


                return false;
            }
        });
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

}
