package com.csci448.goldenrush.networkingpal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * Created by Nick on 3/2/17.
 */

public class ContactsActivity extends FragmentActivity{

    private static String TAG = "ContactsActivity";
    private FloatingActionButton mFABaddContact;
    private int mPosition;

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, ContactsActivity.class);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_search);
        mPosition = 0;


        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("People"));
        tabLayout.addTab(tabLayout.newTab().setText("Companies"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                mPosition = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        /**
         * TODO make FAB go to person or company contact depending on selected tab
         * I think I did it.
         */
        mFABaddContact = (FloatingActionButton) findViewById(R.id.fab_add_contacts);
        mFABaddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "FAB add application");
                /**
                 * position 0 is people
                 * position 1 is companies
                 */
                if (tabLayout.getSelectedTabPosition() == 0) {
                    Log.d(TAG, "start newContactActivity");
                    Intent intent = NewContactActivity.newIntent(ContactsActivity.this, null);
                    startActivity(intent);
                }else {
                    Log.d(TAG, "Start newCompanyActivity");
                    Intent intent = NewCompanyActivity.newIntent(ContactsActivity.this, null);
                    startActivity(intent);
                }
            }
        });
    }
}

