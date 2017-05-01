package com.csci448.goldenrush.networkingpal;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Sarah on 3/29/2017.
 */

public class EventListFragment extends Fragment {
    private RecyclerView mEventRecyclerView;
    private static String TAG="EventListFragment";
    private EventAdapter mAdapter;

    private SimpleDateFormat formatter;
    private String pattern = "EEE, MMM d, yyyy";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView()");
        Log.d(TAG, "inflate fragment_list");
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        if(savedInstanceState!=null) {
            /**
             * Set the things
             */
        }

        mEventRecyclerView = (RecyclerView) view.findViewById(R.id.list_recycler_view);
        mEventRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }
    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }
    private void updateUI(){
        EventLab eventLab = EventLab.get(getActivity());
        List<Event> events = eventLab.getEvents();
        if(mAdapter==null) {
            mAdapter = new EventAdapter(events);
            mEventRecyclerView.setAdapter(mAdapter);
        }
        else{
            mAdapter.setEvents(events);
            mAdapter.notifyDataSetChanged();
        }


    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {
            Activity a = getActivity();
            if(a != null) a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    private class EventHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mNameTextView;
        private TextView mTimeTextView;
        private TextView mDateTextView;

        private Event mEvent;




        public EventHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);

            mNameTextView = (TextView) itemView.findViewById(R.id.list_item_event_name);
            mTimeTextView = (TextView) itemView.findViewById(R.id.list_item_event_time);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_event_company);


        }

        public void bindEvent(Event event){
            formatter = new SimpleDateFormat(pattern);
            mEvent = event;

            mNameTextView.setText(mEvent.getEventName());
            mTimeTextView.setText(mEvent.getmTime());
            mDateTextView.setText(mEvent.getmEventDate().toString());
            //add other things
        }

        @Override
        public void onClick(View v){
            //go to the event details
            Intent last = CalendarActivity.newIntent(getContext());
            Intent i = NewEventActivity.newIntent(getActivity(), mEvent.getId(), last);
            //I think this is making a new event each time it is clicked
            Log.i(TAG, "Event with id "+mEvent.getId()+" created");
            startActivity(i);
        }

    }

    private class EventAdapter extends RecyclerView.Adapter<EventHolder>{
        private List<Event> mEvents;



        public EventAdapter(List<Event> events){
            mEvents = events;
        }

        @Override
        public EventHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_event, parent, false);
            return new EventHolder(view);
        }


        @Override
        public void onBindViewHolder(EventHolder holder, int position){
            Event event = mEvents.get(position);
            holder.bindEvent(event);
        }

        @Override
        public int getItemCount(){
            return mEvents.size();
        }

        public void setEvents(List<Event> events){
            mEvents= events;
        }
    }
}
