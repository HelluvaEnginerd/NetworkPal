package com.csci448.goldenrush.networkingpal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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
        ApplicationLab applicationLab = ApplicationLab.get(getActivity());
        List<Application> apps = applicationLab.getApps();

        mAdapter = new ApplicationAdapter(apps);
        mAppRecyclerView.setAdapter(mAdapter);
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

        mAppRecyclerView = (RecyclerView) view.findViewById(R.id.list_recycler_view);
        mAppRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private class ApplicationAdapter extends RecyclerView.Adapter<ApplicationHolder> {
        private static final String TAG = "CLF:CrimeAdapter";
        private List<Application> mApps;

        public ApplicationAdapter(List<Application> apps) {
            mApps = apps;
        }

        @Override
        public ApplicationHolder onCreateViewHolder( ViewGroup parent, int viewType) {
            Log.d(TAG, "onCreateViewHolder()");
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            Log.d(TAG, "inflate list_item_crime");
            View view = layoutInflater.inflate(R.layout.list_item_application, parent, false);
            return new ApplicationHolder(view);
        }

        @Override public void onBindViewHolder(ApplicationHolder holder, int position){
            Log.d(TAG, "onBindViewHolder()");
            Application crime = mApps.get(position);
            holder.bindCrime(crime);
        }

        @Override
        public int getItemCount() {
            Log.d(TAG, "getItemCount() = " + Integer.toString(mApps.size()));
            return mApps.size();
        }
    }

    private class ApplicationHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private static final String TAG = "ALF:ApplicationHolder";
        private Application mApp;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;

        public void bindCrime(Application app) {
            Log.d(TAG, "bindCrime()");
            formatter = new SimpleDateFormat(pattern);
            mApp = app;
            mTitleTextView.setText(mApp.getName());
            mDateTextView.setText(formatter.format(mApp.getDateDue()));
            //mSolvedCheckBox.setChecked(mCrime.isSolved());
        }

        public ApplicationHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "CrimeHolder()");
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_application_title_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_application_date_text_view);
            mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_application_check_box);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick()");
        }
    }

}

