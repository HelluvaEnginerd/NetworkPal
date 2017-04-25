package com.csci448.goldenrush.networkingpal;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class WelcomeActivity extends AppCompatActivity{


    /**
     * TODO plus button w/list of new things
     * TODO put everything else in the menu_main (lists and such)
     */

    private static String TAG = WelcomeActivity.class.getSimpleName();
    private int mPosition;
    private static final String  EXTRA_POSITION = "position";
    private FloatingActionButton mFABAddThing;
    private Toolbar mToolbar;


    public static Intent newIntent(Context packageContext, int position) {
        Intent intent = new Intent(packageContext, WelcomeActivity.class);
        intent.putExtra(EXTRA_POSITION, position);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_welcome_drawer);
        mPosition = getIntent().getIntExtra(EXTRA_POSITION, 0);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Applications: " + ApplicationLab.get(getApplicationContext()).getNumberApps());

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Apps"));
        tabLayout.addTab(tabLayout.newTab().setText("Events"));
        tabLayout.addTab(tabLayout.newTab().setText("People"));
        tabLayout.addTab(tabLayout.newTab().setText("Companies"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setCurrentItem(mPosition);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                mPosition = tab.getPosition();
                /**
                 * TODO fill switch statement below w/stats
                 */
                switch (mPosition) {
                    case 0:
                        mToolbar.setTitle("Applications: " + ApplicationLab.get(getApplicationContext()).getNumberApps());
                        break;
                    case 1:
                        mToolbar.setTitle("Events stats go here");
                        break;
                    case 2:
                        mToolbar.setTitle("People stats go here");
                        break;
                    case 3:
                        mToolbar.setTitle("Companies stats go here");
                        break;
                    default:
                        mToolbar.setTitle("How did this happen?");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mFABAddThing = (FloatingActionButton) findViewById(R.id.fab_add_contacts);
        mFABAddThing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "FAB add thing");
                if (tabLayout.getSelectedTabPosition() == 0){
                    Intent i = WelcomeActivity.newIntent(WelcomeActivity.this, 0);
                    Log.d(TAG, "start newApplicationActivity");
                    Intent intent = NewApplicationActivity.newIntent(WelcomeActivity.this, null, i);
                    startActivity(intent);
                } else if (tabLayout.getSelectedTabPosition() == 1){
                    Intent i = WelcomeActivity.newIntent(WelcomeActivity.this, 1);
                    Log.d(TAG, "start newEventActivity");
                    Intent intent = NewEventActivity.newIntent(WelcomeActivity.this, null, i);
                    startActivity(intent);
                } else if (tabLayout.getSelectedTabPosition() == 2) {
                    Intent i = WelcomeActivity.newIntent(WelcomeActivity.this, 2);
                    Log.d(TAG, "start newContactActivity");
                    Intent intent = NewContactActivity.newIntent(WelcomeActivity.this, null, i);
                    startActivity(intent);
                } else if (tabLayout.getSelectedTabPosition() == 3){
                    Intent i = WelcomeActivity.newIntent(WelcomeActivity.this, 3);
                    Log.d(TAG, "Start newCompanyActivity");
                    Intent intent = NewCompanyActivity.newIntent(WelcomeActivity.this, null, i);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(), "Currently no settings :)", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
