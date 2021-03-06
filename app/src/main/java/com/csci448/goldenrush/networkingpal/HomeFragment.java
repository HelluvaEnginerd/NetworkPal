package com.csci448.goldenrush.networkingpal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ddunmire on 4/24/2017.
 */

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    private TextView mNumberContactsTextView;
    private TextView mNextEvent;
    private TextView mNextApp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView()");
        Log.d(TAG, "inflate fragment_list");
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mNumberContactsTextView = (TextView) view.findViewById(R.id.number_contacts);
        mNumberContactsTextView.setText("You have " + ContactLab.get(getActivity().getApplicationContext()).getNumberContacts() + " contacts and " + CompanyLab.get(getActivity().getApplicationContext()).getNumberCompanies() + " companies!");

        mNextEvent= (TextView) view.findViewById(R.id.next_event);
        Event nextEvent = EventLab.get(getContext()).getNextEvent();
        if (nextEvent == null){
            mNextEvent.setText("You have no upcoming events!");
        } else {
            mNextEvent.setText("Your next event is: " + EventLab.get(getContext()).getNextEvent().getEventName() + " on " + DateFormat.format("MMM dd, yyyy", nextEvent.getmEventDate()));
        }
        mNextApp= (TextView) view.findViewById(R.id.next_application);
        if(ApplicationLab.get(getActivity().getApplicationContext()).getNextApp() == null) {
            mNextApp.setText("You have no upcoming applications!");
        } else{
            Application a = ApplicationLab.get(getActivity().getApplicationContext()).getNextApp();
            mNextApp.setText("Your next application is due " + DateFormat.format("MMM dd, yyyy",a.getDateDue()) + " for " + a.getCompanyName());
        }

        return view;
    }

    public void updateUI(){
        mNumberContactsTextView.setText("You have " + ContactLab.get(getActivity().getApplicationContext()).getNumberContacts() + " contacts and " + CompanyLab.get(getActivity().getApplicationContext()).getNumberCompanies() + " companies!");
        Event nextEvent = EventLab.get(getContext()).getNextEvent();
        mNextEvent.setText("Your next event is: " + EventLab.get(getContext()).getNextEvent().getEventName() + " on " + DateFormat.format("MMM dd, yyyy", nextEvent.getmEventDate()));
        if(ApplicationLab.get(getActivity().getApplicationContext()).getNextApp() == null) {
            mNextApp.setText("You have no upcoming applications!");
        } else{
            Application a = ApplicationLab.get(getActivity().getApplicationContext()).getNextApp();
            mNextApp.setText("Your next application is due " + DateFormat.format("MMM dd, yyyy",a.getDateDue()) + " for " + a.getCompanyName());
        }
    }
}