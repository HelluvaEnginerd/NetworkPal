package com.csci448.goldenrush.networkingpal;

import android.content.Intent;
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
        Log.d(TAG, "updateUI()");
        RecentActionLab recentActionLab = RecentActionLab.get(getActivity());
        List<RecentAction> recentActivities = recentActionLab.getRecentActivities();

        mAdapter = new RecentActionFragment.RecentActivityAdapter(recentActivities);
        mRecentActivityRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        super.onCreate(savedInstanceState);

        setReturnResult();
    }

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

        mRecentActivityRecyclerView = (RecyclerView) view.findViewById(R.id.list_recycler_view);
        mRecentActivityRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private class RecentActivityAdapter extends RecyclerView.Adapter<RecentActionFragment.RecentActivityHolder> {
        private static final String TAG = "RAF:RecentActAdapter";
        private List<RecentAction> mRecentActivities;

        public RecentActivityAdapter(List<RecentAction> recentActivities) {
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
            RecentAction recentAction = mRecentActivities.get(position);
            holder.bindRecentActivity(recentAction);
        }

        @Override
        public int getItemCount() {
            Log.d(TAG, "getItemCount() = " + Integer.toString(mRecentActivities.size()));
            return mRecentActivities.size();
        }
    }

    private class RecentActivityHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private static final String TAG = "RAF:RecentActHolder";
        private RecentAction mRecentAction;
        private TextView mCategoryTextView;
        private TextView mDateTextView;
        private TextView mCompanyTextView;
        private TextView mNameTextView;

        public void bindRecentActivity(RecentAction recentAction) {
            Log.d(TAG, "bindRecentActivity()");
            formatter = new SimpleDateFormat(pattern);
            mRecentAction = recentAction;
            mNameTextView.setText(mRecentAction.getName());
            mDateTextView.setText(formatter.format(mRecentAction.getDate()));
            mCompanyTextView.setText(mRecentAction.getCompany());
            mCategoryTextView.setText(mRecentAction.getCategory());
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
            Intent intent;
            String category = mRecentAction.getCategory();
            switch (category) {
                case "Contact":
                    intent = NewContactActivity.newIntent(getActivity(), null);
                    break;
                case "Company":
                    intent = NewCompanyActivity.newIntent(getActivity(), null);
                    break;
                case "Application":
                    intent = NewApplicationActivity.newIntent(getActivity(), null);
                    break;
                case "Event":
                    intent = NewEventActivity.newIntent(getActivity());
                    break;
                default:
                    intent = WelcomeActivity.newIntent(getContext());
                    break;
            }
            startActivity(intent);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        super.onSaveInstanceState(savedInstanceState);
        /**
         * Save things like so
         *
        savedInstanceState.putInt(EXTRA_NUM_PLAYERS, mNumPlayers);
        savedInstanceState.putSerializable(EXTRA_FIRST_PLAYER, mFirstPlayer);
        savedInstanceState.putSerializable(EXTRA_BB8_SCORE, mBB8Score);
        savedInstanceState.putSerializable(EXTRA_R2D2_SCORE, mR2D2Score);
        savedInstanceState.putSerializable(EXTRA_DRAW_SCORE, mDrawScore);
        savedInstanceState.putSerializable(EXTRA_BOARD, board);

        Log.d(TAG, "onSaveInstanceState()");
        Log.d(TAG, "first: " + mFirstPlayer +
                "\nNumPlay: " + mNumPlayers +
                "\nbb8: " + mBB8Score +
                "\nr2d2: " + mR2D2Score +
                "\ndraw: " + mDrawScore);
        for (int i = 0; i <=2; i++){
            for (int j = 0; j <= 2; j++){
                //boardView[i][j].setImageResource(android.R.color.transparent);
                Log.d(TAG, "board[" + i + "][" + j + "] = " + Integer.toString(board[i][j]));
            }
        }
        */
    }

    public void setReturnResult() {
        Log.d(TAG, "setReturnResult() called");

        Intent resultIntent = new Intent();
        /*
        resultIntent.putExtra(EXTRA_NUM_PLAYERS, mNumPlayers);
        resultIntent.putExtra(EXTRA_FIRST_PLAYER, mFirstPlayer);
        resultIntent.putExtra(EXTRA_BB8_SCORE, mBB8Score);
        resultIntent.putExtra(EXTRA_R2D2_SCORE, mR2D2Score);
        resultIntent.putExtra(EXTRA_DRAW_SCORE, mDrawScore);

        getActivity().setResult(GameActivity.RESULT_OK, resultIntent);
        */
    }
}
