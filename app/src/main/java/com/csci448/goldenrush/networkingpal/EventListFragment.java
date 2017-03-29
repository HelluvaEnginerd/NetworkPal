package com.csci448.goldenrush.networkingpal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sarah on 3/29/2017.
 */

public class EventListFragment extends Fragment {
    private RecyclerView mEventRecyclerView;
    private static String TAG="EventListFragment";
    private EventAdapter mAdapter;

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

    private void updateUI(){
        EventLab eventLab = EventLab.get(getActivity());
        List<Event> events = eventLab.getEvents();

        mAdapter = new EventAdapter(events);
        mEventRecyclerView.setAdapter(mAdapter);
    }
    private class EventHolder extends RecyclerView.ViewHolder{
        public TextView mNameTextView;

        public EventHolder(View itemView){
            super(itemView);

            mNameTextView = (TextView) itemView;
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
            View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            return new EventHolder(view);
        }


        @Override
        public void onBindViewHolder(EventHolder holder, int position){
            Event event = mEvents.get(position);
            holder.mNameTextView.setText(event.getEventName());
        }

        @Override
        public int getItemCount(){
            return mEvents.size();
        }
    }
}
