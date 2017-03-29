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

public class RecentActionLab {
    private static String TAG = "RecentActionLab";

    private static RecentActionLab sRecentActionLab;
    private List<RecentActions> mRecentActivities;



    public static RecentActionLab get(Context context){
        if (sRecentActionLab == null){
            sRecentActionLab = new RecentActionLab(context);
        }
        return sRecentActionLab;
    }

    private RecentActionLab(Context context){
        mRecentActivities = new ArrayList<>();
        /**
         * TODO remove fake activities
         * TODO UUIDs calced off of 'EVENT'? change first inputvar
         */

        Date date = new Date();

        Intent intent1 = NewEventActivity.newIntent(context);
        RecentActions activity1 = new RecentActions("Event", "Interview", date, "Lockheed", intent1);
        mRecentActivities.add(activity1);

        Intent intent2 = NewContactActivity.newIntent(context, null);
        RecentActions activity2 = new RecentActions("Contact", "Steve", date, "NASA", intent2);
        mRecentActivities.add(activity2);

        Intent intent3 = NewApplicationActivity.newIntent(context, null);
        RecentActions activity3 = new RecentActions("Application", "Process Engineer", date, "SpaceX", intent3);
        mRecentActivities.add(activity3);

        Intent intent4 = NewContactActivity.newIntent(context, null);
        RecentActions activity4 = new RecentActions("Company", "Jane", date, "Schlumberger", intent4);
        mRecentActivities.add(activity4);
    }

    public RecentActions getRecentActivity(UUID id){
        for (RecentActions recentActions : mRecentActivities){
            if (recentActions.getUUID().equals(id)){
                return recentActions;
            }
        }
        return null;
    }

    public List<RecentActions> getRecentActivities() {
        return mRecentActivities;
    }

}
