package com.csci448.goldenrush.networkingpal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.UUID;

/**
 * Created by Sarah on 2/28/2017.
 * Activity created when creating a new event from the calendar
 */

public class NewEventActivity extends AppCompatActivity {
    private static final String TAG = "NewEventActivity";

    //all widgets
    private EditText name;
    private Button date;
    private EditText company;
    private EditText details;
    private Button create;
    private Event mEvent;

    //Keys for save instance state
    private static final String EVENT_NAME = "EventNameString";
    private static final String DATE = "DateString";
    private static final String COMPANY_NAME = "CompanyString";
    private static final String DETAILS ="DetailsString";
    private static final String DIALOG_DATE = "DialogDate";

    //keys for adding extras to intent
    public static final String EXTRA_EVENT_ID = "EventUUID";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        Log.d(TAG,"onCreate Called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_event_activity);

        setUpWidgets();

        UUID eventId = (UUID) getIntent().getSerializableExtra(EXTRA_EVENT_ID);

        if(eventId!=null){
            EventLab eventLab = EventLab.get(NewEventActivity.this);
            mEvent = eventLab.getEvent(eventId);
            //set all text things here
            name.setText(mEvent.getEventName());
            details.setText(mEvent.getmEventDetails());
        }



        //reload if there is a saved state
        if(savedInstanceState!=null)
        {
            name.setText(savedInstanceState.getString(EVENT_NAME));
            date.setText(savedInstanceState.getString(DATE));
            company.setText(savedInstanceState.getString(COMPANY_NAME));
            details.setText(savedInstanceState.getString(DETAILS));
        }
    }

    private void setUpWidgets(){
        Log.d(TAG, "setUpWidgets()");
        name = (EditText) findViewById(R.id.event_box);
        name.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                name.setText(R.string.empty_text);
            }
        });

        date= (Button) findViewById(R.id.date_box);
        date.setText("test");
        date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                /*
                try to do the same date picker
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mApp.getDateDue());
                //NO CLUE WHAT TO DO HERE SINCE THIS IS AN ACTIVITY - THIS IS THE PROBLEM WITH THE DATE
                //dialog.setTargetFragment(ApplicationSearchActivity.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
                //date.setText(R.string.empty_text);
                */
            }
        });

        company = (EditText) findViewById(R.id.time_box);
        company.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                company.setText(R.string.empty_text);
            }
        });
        details = (EditText) findViewById(R.id.details_box);
        details.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                details.setText(R.string.empty_text);
            }
        });

        create = (Button) findViewById(R.id.create_button);
        create.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //TODO: make the activity
                finish(); //exit out of activity
            }
        });
        //Todo: Add all listeners
        /**
         * TODO add Recent activity to okay button
         */

    }
    public static Intent newIntent(Context packageContext, UUID eventId){
        Log.d(TAG, "newIntent()");
        Intent i = new Intent(packageContext, NewEventActivity.class);
        i.putExtra(EXTRA_EVENT_ID, eventId);

        return i ;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onSaveInstanceStateCalled");
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(EVENT_NAME, name.getText().toString());
        savedInstanceState.putString(DATE, date.getText().toString());
        savedInstanceState.putString(COMPANY_NAME, company.getText().toString());
        savedInstanceState.putString(DETAILS, details.getText().toString());
    }
}
