package com.csci448.goldenrush.networkingpal;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Hayden on 3/2/17.
 */

public class RecentActivityLab {
    private static String TAG = "RecentActivityLab";

    private static RecentActivityLab sRecentActivityLab;
    private List<RecentActivity> mRecentActivities;



    public static RecentActivityLab get(Context context){
        if (sRecentActivityLab == null){
            sRecentActivityLab = new RecentActivityLab(context);
        }
        return sRecentActivityLab;
    }

    private RecentActivityLab(Context context){
        mRecentActivities = new ArrayList<>();
        /**
         * TODO remove fake activities
         * TODO UUIDs calced off of 'EVENT'? change first inputvar
         */

        Date date = new Date();

        Intent intent1 = new Intent(context, NewEventActivity.class);
        RecentActivity activity1 = new RecentActivity("Event", "Interview", date, "Lockheed", intent1);
        mRecentActivities.add(activity1);

        Intent intent2 = new Intent(context, NewContactActivity.class);
        RecentActivity activity2 = new RecentActivity("Contact", "Steve", date, "NASA", intent2);
        mRecentActivities.add(activity2);

        Intent intent3 = new Intent(context, NewApplicationActivity.class);
        RecentActivity activity3 = new RecentActivity("Application", "Process Engineer", date, "SpaceX", intent3);
        mRecentActivities.add(activity3);

        Intent intent4 = new Intent(context, NewContactActivity.class);
        RecentActivity activity4 = new RecentActivity("Contact2", "Jane", date, "Schlumberger", intent4);
        mRecentActivities.add(activity4);

        Intent intent5 = new Intent(context, NewEventActivity.class);
        RecentActivity activity5 = new RecentActivity("Event2", "Dinner", date, "MillerCoors", intent5);
        mRecentActivities.add(activity5);

        Intent intent6 = new Intent(context, NewApplicationActivity.class);
        RecentActivity activity6 = new RecentActivity("Application2", "Chemical Engineer", date, "Chevron", intent6);
        mRecentActivities.add(activity6);
    }

    public RecentActivity getRecentActivity(UUID id){
        for (RecentActivity recentActivity : mRecentActivities){
            if (recentActivity.getUUID().equals(id)){
                return recentActivity;
            }
        }
        return null;
    }

    public List<RecentActivity> getRecentActivities() {
        return mRecentActivities;
    }

}
