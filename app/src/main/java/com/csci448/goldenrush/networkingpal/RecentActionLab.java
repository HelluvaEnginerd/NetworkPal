package com.csci448.goldenrush.networkingpal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.csci448.goldenrush.networkingpal.database.RecentActionBaseHelper;
import com.csci448.goldenrush.networkingpal.database.RecentActionDbSchema.RecentActionTable;

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
    private  static Context mContext;
    private static SQLiteDatabase mDatabase;


    public static RecentActionLab get(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new RecentActionBaseHelper(mContext).getWritableDatabase();
        if (sRecentActionLab == null){
            sRecentActionLab = new RecentActionLab(context);
        }
        return sRecentActionLab;
    }

    private RecentActionLab(Context context){
        /**
         * TODO remove fake activities
         * TODO UUIDs calced off of 'EVENT'? change first inputvar
         */

        Date date = new Date();
        /*
        RecentAction activity1 = new RecentAction("Event", "Interview", date, "Lockheed");

        Intent intent1 = NewEventActivity.newIntent(context,null); //todo:need to change
        RecentActions activity1 = new RecentActions("Event", "Interview", date, "Lockheed", intent1);
        mRecentActivities.add(activity1);

        RecentAction activity2 = new RecentAction("Contact", "Steve", date, "NASA");
        mRecentActivities.add(activity2);

        RecentAction activity3 = new RecentAction("Application", "Process Engineer", date, "SpaceX");
        mRecentActivities.add(activity3);

        RecentAction activity4 = new RecentAction("Company", "Jane", date, "Schlumberger");
        mRecentActivities.add(activity4);
        */
    }

    public void addRecentActivity(RecentAction recentAction){
        ContentValues values = getContentValues(recentAction);

        mDatabase.insert(RecentActionTable.NAME, null, values);
    }

    public List<RecentAction> getRecentActivities() {
        return new ArrayList<>();
    }

    public RecentAction getRecentAction(UUID uuid){
        return null;
    }

    public void updateRecentAction(RecentAction recentAction){
        String uuidString = recentAction.getUUID().toString();
        ContentValues values = getContentValues(recentAction);

        mDatabase.update(RecentActionTable.NAME, values, RecentActionTable.Cols.UUID + " = ?", new String[] {uuidString});
    }

    private static ContentValues getContentValues(RecentAction recentAction){
        ContentValues values = new ContentValues();

        values.put(RecentActionTable.Cols.UUID, recentAction.getUUID().toString());
        values.put(RecentActionTable.Cols.CATEGORY, recentAction.getCategory());
        values.put(RecentActionTable.Cols.COMPANY, recentAction.getCompany());
        values.put(RecentActionTable.Cols.DATE, recentAction.getDate().toString());
        values.put(RecentActionTable.Cols.NAME, recentAction.getName());

        return values;
    }

}
