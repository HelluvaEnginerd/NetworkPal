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

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Hayden on 3/2/17.
 */

public class RecentActionFragment extends Fragment {
    private static final String TAG = "RecentActionFragment";

    private RecyclerView mRecentActivityRecyclerView;
    private RecentActionFragment.RecentActivityAdapter mAdapter;

    private SimpleDateFormat formatter;
    private String pattern = "EEE, MMM d, yyyy";

    private void updateUI() {
        RecentActionLab recentActionLab = RecentActionLab.get(getActivity());
        List<RecentActions> recentActivities = recentActionLab.getRecentActivities();

        mAdapter = new RecentActionFragment.RecentActivityAdapter(recentActivities);
        mRecentActivityRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView()");
        Log.d(TAG, "inflate fragment_list");
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        mRecentActivityRecyclerView = (RecyclerView) view.findViewById(R.id.list_recycler_view);
        mRecentActivityRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private class RecentActivityAdapter extends RecyclerView.Adapter<RecentActionFragment.RecentActivityHolder> {
        private static final String TAG = "RAF:RecentActAdapter";
        private List<RecentActions> mRecentActivities;

        public RecentActivityAdapter(List<RecentActions> recentActivities) {
            mRecentActivities = recentActivities;
        }

        @Override
        public RecentActionFragment.RecentActivityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.d(TAG, "onCreateViewHolder()");
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            Log.d(TAG, "inflate list_item_application");
            View view = layoutInflater.inflate(R.layout.list_item_recent, parent, false);
            return new RecentActionFragment.RecentActivityHolder(view);
        }

        @Override public void onBindViewHolder(RecentActionFragment.RecentActivityHolder holder, int position){
            Log.d(TAG, "onBindViewHolder()");
            RecentActions recentActions = mRecentActivities.get(position);
            holder.bindRecentActivity(recentActions);
        }

        @Override
        public int getItemCount() {
            Log.d(TAG, "getItemCount() = " + Integer.toString(mRecentActivities.size()));
            return mRecentActivities.size();
        }
    }

    private class RecentActivityHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private static final String TAG = "RAF:RecentActHolder";
        private RecentActions mRecentActions;
        private TextView mCategoryTextView;
        private TextView mDateTextView;
        private TextView mCompanyTextView;
        private TextView mNameTextView;

        public void bindRecentActivity(RecentActions recentActions) {
            Log.d(TAG, "bindRecentActivity()");
            formatter = new SimpleDateFormat(pattern);
            mRecentActions = recentActions;
            mNameTextView.setText(mRecentActions.getName());
            mDateTextView.setText(formatter.format(mRecentActions.getDate()));
            mCompanyTextView.setText(mRecentActions.getCompany());
            mCategoryTextView.setText(mRecentActions.getCategory());
        }

        public RecentActivityHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "ApplicationHolder()");
            itemView.setOnClickListener(this);
            mNameTextView = (TextView) itemView.findViewById(R.id.list_item_name_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_company_text_view);
            mCompanyTextView = (TextView) itemView.findViewById(R.id.list_item_company_text_view);
            mCategoryTextView = (TextView) itemView.findViewById(R.id.list_item_category_text_view);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick()");
            startActivity(mRecentActions.getIntent());
        }
    }
}
