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

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Hayden on 2/28/17.
 */

public class ApplicationListFragment extends Fragment {

    private static final String TAG = "ApplicationListFragment";

    private RecyclerView mAppRecyclerView;
    private ApplicationAdapter mAdapter;

    private SimpleDateFormat formatter;
    private String pattern = "EEE, MMM d, yyyy";

    private void updateUI(){
        Log.d(TAG, "updateUI()");
        ApplicationLab applicationLab = ApplicationLab.get(getActivity());
        List<Application> apps = applicationLab.getApps();

        if(mAdapter==null){
            mAdapter = new ApplicationAdapter(apps);
            mAppRecyclerView.setAdapter(mAdapter);
        } else{
            mAdapter.setApplications(apps);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView()");
        Log.d(TAG, "inflate fragment_list");
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        mAppRecyclerView = (RecyclerView) view.findViewById(R.id.list_recycler_view);
        mAppRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private class ApplicationAdapter extends RecyclerView.Adapter<ApplicationHolder> {
        private static final String TAG = "ALF:ApplicationAdapter";
        private List<Application> mApps;

        public ApplicationAdapter(List<Application> apps) {
            mApps = apps;
        }

        @Override
        public ApplicationHolder onCreateViewHolder( ViewGroup parent, int viewType) {
            Log.d(TAG, "onCreateViewHolder()");
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            Log.d(TAG, "inflate list_item_application");
            View view = layoutInflater.inflate(R.layout.list_item_application, parent, false);
            return new ApplicationHolder(view);
        }

        @Override public void onBindViewHolder(ApplicationHolder holder, int position){
            Log.d(TAG, "onBindViewHolder()");
            Application application = mApps.get(position);
            holder.bindApp(application);
        }

        @Override
        public int getItemCount() {
            Log.d(TAG, "getItemCount() = " + Integer.toString(mApps.size()));
            return mApps.size();
        }

        public void setApplications(List<Application> apps){
            mApps = apps;
        }
    }

    private class ApplicationHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private static final String TAG = "ALF:ApplicationHolder";
        private Application mApp;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private TextView mCompanyTextView;
        private TextView mContactTextView;

        public void bindApp(Application app) {
            Log.d(TAG, "bindApp()");
            formatter = new SimpleDateFormat(pattern);
            mApp = app;
            mTitleTextView.setText(mApp.getJobTitle());
            mDateTextView.setText(formatter.format(mApp.getDateDue()));
            mCompanyTextView.setText(mApp.getCompanyName());
            mContactTextView.setText(mApp.getCompanyContact());
        }

        public ApplicationHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "ApplicationHolder()");
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_application_title_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_application_date_text_view);
            mCompanyTextView = (TextView) itemView.findViewById(R.id.list_item_application_company_text_view);
            mContactTextView = (TextView) itemView.findViewById(R.id.list_item_application_contact_text_view);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClickView");
            Intent i = ApplicationSearchActivity.newIntent(getContext());
            Intent intent = NewApplicationActivity.newIntent(getActivity(), mApp.getId(), i);
            startActivity(intent);
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
}

