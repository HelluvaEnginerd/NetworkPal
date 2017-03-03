package com.csci448.goldenrush.networkingpal;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.crashlytics.android.Crashlytics;

import java.util.UUID;

import io.fabric.sdk.android.Fabric;

public class WelcomeActivity extends AppCompatActivity{


    /**
     * TODO plus button w/list of new things
     * TODO put everything else in the menu (lists and such)
     */

    private static String TAG = WelcomeActivity.class.getSimpleName();

    private Button  mEventsButton;
    private Button  mApplicationsButton;
    private Button  mContactsButton;
    private Button  mDiggernetButton;

    public static Intent newIntent(Context packageContext){
        Intent intent = new Intent(packageContext, WelcomeActivity.class);
        return intent ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mEventsButton = (Button) findViewById(R.id.events_button);
        mEventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * goes to events
                 */
                Intent i = CalendarActivity.newIntent(WelcomeActivity.this);
                startActivity(i);
            }
        });

        mApplicationsButton = (Button) findViewById(R.id.apps_button);
        mApplicationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * goes to application list view
                 */
                Intent intent = ApplicationSearchActivity.newIntent(WelcomeActivity.this, new UUID(1,1));
                startActivity(intent);
            }
        });

        mContactsButton = (Button) findViewById(R.id.contacts_button);
        mContactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * go to contacts view,
                 * TODO decide if its companies or people shown first
                 */
            }
        });

        mDiggernetButton = (Button) findViewById(R.id.diggernet_button);
        mDiggernetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * goes to diggernet page
                 */
                Intent i = DiggernetActivity.newIntent(WelcomeActivity.this);
                startActivity(i);
            }
        });


        /**
         * Loads the recent activity recyclerview
         */
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_welcome_list_host);

        if (fragment == null){
            fragment = new RecentActivityFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_welcome_list_host, fragment)
                    .commit();
        }
    }

}
