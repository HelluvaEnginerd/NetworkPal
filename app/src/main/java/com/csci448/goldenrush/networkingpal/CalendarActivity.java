package com.csci448.goldenrush.networkingpal;

import android.content.Context;
import android.content.Intent;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Sarah on 2/28/2017.
 * Displays calender to hold all event activities
 */

public class CalendarActivity extends AppCompatActivity {
    private static final String TAG = "Calendar Activity";
    private static final String EXTRA_DATE = "extra_date";
    FloatingActionButton newEventButton;
    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    //CalendarView calendarWidget;
    CompactCalendarView mCompactCalendarView;
    private TextView currentDateText;
    private String currentDateString;

    //Strings for save instance state
    private static final String DATE = "Date";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        Log.d(TAG, "onCreate Called");
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.calendar_activity);
        setContentView(R.layout.compact_calendar_view);

        setUpWidgets();
        if(savedInstanceState!=null)
        {
            //calendarWidget.setDate(savedInstanceState.getLong(DATE));
        }
        //create the list of event fragments
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_events_container);

        if (fragment == null){
            fragment = new EventListFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_events_container, fragment)
                    .commit();
        }
    }

    private void setUpWidgets(){
        Log.d(TAG, "setupWidgets()");
        newEventButton = (FloatingActionButton) findViewById(R.id.fab_add_event);
        mCompactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        mCompactCalendarView.setCalendarBackgroundColor(Color.DKGRAY);
        mCompactCalendarView.setFirstDayOfWeek(Calendar.MONDAY);
        List<Event> events = EventLab.get(getApplicationContext()).getEvents();
        for (Event e: events){
            mCompactCalendarView.addEvent(new com.github.sundeepk.compactcalendarview.domain.Event(Color.GREEN, e.getmEventDate().getTime()));
        }
        // calendarWidget = (CalendarView) findViewById(R.id.calendar_widget);
        newEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent last = CalendarActivity.newIntent(CalendarActivity.this);
                Intent i = NewEventActivity.newIntent(CalendarActivity.this, null, last);
                startActivity(i);
            }
        });

        currentDateText= (TextView) findViewById(R.id.events_on_date);
        //currentDateString = new SimpleDateFormat("MM/dd/yyyy").format(new Date(calendarWidget.getDate()));
        currentDateText.setText(currentDateString);

        /*
        //change the date showing
        calendarWidget.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //create new string for new selected date
                currentDateString = new SimpleDateFormat("MM/dd/yyyy").format(new Date(calendarWidget.getDate()));
                currentDateText.setText(currentDateString);

                //update the list of items based off of date
            }
        });
        */
        //set title on calendar scroll
        mCompactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<com.github.sundeepk.compactcalendarview.domain.Event> bookingsFromMap = mCompactCalendarView.getEvents(dateClicked);
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
            }
        });

    }

    public static Intent newIntent(Context packageContext){
        Log.d(TAG, "newIntent()");
        Intent i = new Intent(packageContext, CalendarActivity.class);
        /**
         * TODO: Add any extras you may need when creating an event
         */

        return i ;
    }

    /**
     * so when an event is changed it is added back correctly
     */
    @Override
    public void onResume(){
        super.onResume();
        mCompactCalendarView.removeAllEvents();
        List<Event> events = EventLab.get(getApplicationContext()).getEvents();
        for (Event e: events){
            mCompactCalendarView.addEvent(new com.github.sundeepk.compactcalendarview.domain.Event(Color.GREEN, e.getmEventDate().getTime()));
        }
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onSaveInstanceStateCalled");
        super.onSaveInstanceState(savedInstanceState);
        //savedInstanceState.putLong(DATE, calendarWidget.getDate());
    }
}
