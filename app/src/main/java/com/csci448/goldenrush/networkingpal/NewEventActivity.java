package com.csci448.goldenrush.networkingpal;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;
import java.util.List;
/**
 * Created by Sarah on 2/28/2017.
 * Activity created when creating a new event from the calendar
 */

public class NewEventActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, DatePickerFragment.DateCallbacks {
    private static final String TAG = "NewEventActivity";

    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;

    //all widgets
    private EditText name;
    private Button date;
    private EditText time;
    private EditText details;
    private Button create;
    private Event mEvent;
    private Button back;
    private static Intent mLastIntent;
    private List<Event> mEvents;
    private UUID eventId;
    EventLab eventLab ;

    //Keys for save instance state
    private static final String EVENT_NAME = "EventNameString";
    private static final String DATE = "DateString";
    private static final String TIME = "TimeString";
    private static final String DETAILS ="DetailsString";


    //keys for adding extras to intent
    public static final String EXTRA_EVENT_ID = "EventUUID";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        Log.d(TAG,"onCreate Called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_event_activity);
        //make the event lab
        eventLab= EventLab.get(NewEventActivity.this);

        //if there is an intent get it
        eventId = (UUID) getIntent().getSerializableExtra(EXTRA_EVENT_ID);
        //if there is already an event get it
        if(eventId!=null){
            mEvent = eventLab.getEvent(eventId);
            Log.d(TAG, "Existing event grabbed");
        }else {
            Log.d(TAG, "No existing event - new one created");
            mEvent = new Event();
            EventLab.get(getApplicationContext()).addEvent(mEvent);
        }
        setUpWidgets();
        //reload if there is a saved state
        if(savedInstanceState!=null)
        {
            name.setText(savedInstanceState.getString(EVENT_NAME));
            date.setText(savedInstanceState.getString(DATE));
            time.setText(savedInstanceState.getString(TIME));
            details.setText(savedInstanceState.getString(DETAILS));
        }
    }

    private void setUpWidgets(){
        Log.d(TAG, "setUpWidgets()");
        name = (EditText) findViewById(R.id.event_box);
        name.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //name.setText(R.string.empty_text);
            }
        });

        date= (Button) findViewById(R.id.date_box);
        date.setText("test");
        date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mEvent.getmEventDate());
                dialog.show(manager, DIALOG_DATE);
            }
        });

        time = (EditText) findViewById(R.id.time_box);
        time.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                time.setText(R.string.empty_text);
            }
        });
        details = (EditText) findViewById(R.id.details_box);
        details.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //details.setText(R.string.empty_text);
            }
        });



        create = (Button) findViewById(R.id.done_company_button);
        create.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //TODO: I think this is making multiple events... fix
                Log.i(TAG, "done button pressed");
                Log.i(TAG, "event made with id "+ mEvent.getId()+" being edited?");
                mEvent.setEventName(name.getText().toString());
                mEvent.setmEventDetails(details.getText().toString());
                mEvent.setmTime(time.getText().toString());
                //check if it exists... then if not add it to the list
                //EventLab.get(getApplicationContext()).addEvent(mEvent);
                finish(); //exit out of activity
            }
        });

        back = (Button) findViewById(R.id.back_company_button);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                finish();
            }
        });


        if(eventId!=null){


            //set all text things here
            Log.i(TAG,mEvent.getEventName() );
            name.setText(mEvent.getEventName());
            time.setText(mEvent.getmTime());
            details.setText(mEvent.getmEventDetails());
        }

        //Todo: Add all listeners

    }
    public static Intent newIntent(Context packageContext, UUID eventId, Intent in){
        mLastIntent = in;
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
        savedInstanceState.putString(TIME, time.getText().toString());
        savedInstanceState.putString(DETAILS, details.getText().toString());
    }

    @Override public void onPause(){
        Log.d(TAG, "onPause() called");
        super.onPause();
        //EventLab.get(this).updateEvent(mEvent);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode != Activity.RESULT_OK){
            return;
        }

        if(requestCode == REQUEST_DATE){
            Date d = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            Log.d("arg", d.toString());
            mEvent.setmEventDate(d);
            date.setText(mEvent.getmEventDate().toString());
        }
    }

    @Override
    public void onDateSelected(Date d){
        mEvent.setmEventDate(d);
        date.setText(d.toString());
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day){
        Log.d(TAG, "Date picked");
        date.setText(Integer.toString(month) + "/" + Integer.toString(day) + Integer.toString(year));
    }
}
