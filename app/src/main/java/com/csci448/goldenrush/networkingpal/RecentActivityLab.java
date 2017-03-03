package com.csci448.goldenrush.networkingpal;

import android.content.Context;

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
        RecentActivity activity1 = new RecentActivity("Event", "Interview", date, "Lockheed");
        mRecentActivities.add(activity1);
        RecentActivity activity2 = new RecentActivity("Contact", "Steve", date, "NASA");
        mRecentActivities.add(activity2);
        RecentActivity activity3 = new RecentActivity("Application", "Process Engineer", date, "SpaceX");
        mRecentActivities.add(activity3);
        RecentActivity activity4 = new RecentActivity("Contact2", "Jane", date, "Schlumberger");
        mRecentActivities.add(activity4);
        RecentActivity activity5 = new RecentActivity("Event2", "Dinner", date, "MillerCoors");
        mRecentActivities.add(activity5);
        RecentActivity activity6 = new RecentActivity("Application2", "Chemical Engineer", date, "Chevron");
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
