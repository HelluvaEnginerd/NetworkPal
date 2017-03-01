package com.csci448.goldenrush.networkingpal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class WelcomeActivity extends AppCompatActivity {

    private static String TAG = WelcomeActivity.class.getSimpleName();

    private Button  mEventsButton;
    private Button  mApplicationsButton;
    private Button  mContactsButton;
    private Button  mDiggernetButton;
    private RelativeLayout mApplicationLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /**
         * TODO make this a recycler view that shows recent activities from an arraylist
         */

        mEventsButton = (Button) findViewById(R.id.events_button);
        mEventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to events
                Intent i = NewEventActivity.newIntent(WelcomeActivity.this);
                startActivity(i);
            }
        });

        mApplicationsButton = (Button) findViewById(R.id.apps_button);
        mApplicationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ApplicationSearchActivity.newIntent(WelcomeActivity.this);
                startActivity(intent);
            }
        });

        mContactsButton = (Button) findViewById(R.id.contacts_button);
        mContactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to contacts
            }
        });

        mDiggernetButton = (Button) findViewById(R.id.diggernet_button);
        mDiggernetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to diggernet
            }
        });

        mApplicationLayout = (RelativeLayout) findViewById(R.id.relative_application_view);
        mApplicationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * TODO open application activity
                 */
                /*Intent intent = NewApplicationActivity.newIntent(WelcomeActivity.this);
                startActivity(intent);*/
            }
        });


    }
}
