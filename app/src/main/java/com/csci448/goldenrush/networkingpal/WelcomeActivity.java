package com.csci448.goldenrush.networkingpal;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class WelcomeActivity extends AppCompatActivity{


    /**
     * TODO plus button w/list of new things
     * TODO put everything else in the menu_main (lists and such)
     */

    private static String TAG = WelcomeActivity.class.getSimpleName();
    private static int mPosition;
    private static final String  EXTRA_POSITION = "position";
    private FloatingActionButton mFABAddButton;
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private Button mDiggernetButton;


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
        mToolbar.setTitle("Welcome to My Networking Pal!");
        mFABAddButton = (FloatingActionButton) findViewById(R.id.fab_add_contacts);

        mDiggernetButton = (Button) findViewById(R.id.diggernet_button);
        mDiggernetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = DiggernetActivity.newIntent(WelcomeActivity.this);
                startActivity(intent);
            }
        });

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mTabLayout.addTab(mTabLayout.newTab().setText("Home"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Apps"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Events"));
        mTabLayout.addTab(mTabLayout.newTab().setText("People"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Companies"));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        viewPager.setCurrentItem(mPosition);
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                mPosition = tab.getPosition();
                /**
                 * TODO fill switch statement below w/stats
                 */
                switch (mPosition) {
                    case 0:
                        mToolbar.setTitle("Welcome to My Networking Pal!");
                        mFABAddButton.setVisibility(View.GONE);
                        break;
                    case 1:
                        mToolbar.setTitle("Applications: " + ApplicationLab.get(getApplicationContext()).getNumberApps());
                        mFABAddButton.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        mToolbar.setTitle("Events: " + EventLab.get(getApplicationContext()).getNumberEvents());
                        mFABAddButton.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        mToolbar.setTitle("Contacts: " + ContactLab.get(getApplicationContext()).getNumberContacts());
                        mFABAddButton.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        mToolbar.setTitle("Companies: " + CompanyLab.get(getApplicationContext()).getNumberCompanies());
                        mFABAddButton.setVisibility(View.VISIBLE);
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

        /**
         * to initially make the FAB disappear.
         * After this the switch statement above takes care of it
         */
        if (mTabLayout.getSelectedTabPosition() == 0){
            mFABAddButton.setVisibility(View.GONE);
        }
        mFABAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "FAB add thing");
                if (mTabLayout.getSelectedTabPosition() == 1){
                    Intent i = WelcomeActivity.newIntent(WelcomeActivity.this, 1);
                    Log.d(TAG, "start newApplicationActivity");
                    Intent intent = NewApplicationActivity.newIntent(WelcomeActivity.this, null, i);
                    startActivity(intent);
                } else if (mTabLayout.getSelectedTabPosition() == 2){
                    Intent i = WelcomeActivity.newIntent(WelcomeActivity.this, 2);
                    Log.d(TAG, "start newEventActivity");
                    Intent intent = NewEventActivity.newIntent(WelcomeActivity.this, null, i);
                    startActivity(intent);
                } else if (mTabLayout.getSelectedTabPosition() == 3) {
                    Intent i = WelcomeActivity.newIntent(WelcomeActivity.this, 3);
                    Log.d(TAG, "start newContactActivity");
                    Intent intent = NewContactActivity.newIntent(WelcomeActivity.this, null, i);
                    startActivity(intent);
                } else if (mTabLayout.getSelectedTabPosition() == 4){
                    Intent i = WelcomeActivity.newIntent(WelcomeActivity.this, 4);
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

    public static void setTabPosition(int pos){
        Log.d(TAG, "setTabPosition");
        mPosition = pos;
    }

    @Override
    public void onResume(){
        Log.d(TAG, "onResume");
        super.onResume();
        TabLayout.Tab tab = mTabLayout.getTabAt(mPosition);
        tab.select();
    }

}
