package com.csci448.goldenrush.networkingpal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.csci448.goldenrush.networkingpal.database.RecentActionBaseHelper;
import com.csci448.goldenrush.networkingpal.database.RecentActionCursorWrapper;
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
        Log.d(TAG, "RecentActionLab()");
        mContext = context.getApplicationContext();
        mDatabase = new RecentActionBaseHelper(mContext).getWritableDatabase();
    }

    public void addRecentActivity(RecentAction recentAction){
        ContentValues values = getContentValues(recentAction);

        mDatabase.insert(RecentActionTable.NAME, null, values);
    }

    public List<RecentAction> getRecentActions() {
        List<RecentAction> recentActions = new ArrayList<>();

        RecentActionCursorWrapper cursor  = queryRecentActions(null,null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                recentActions.add(cursor.getRecentAction());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return recentActions;
    }

    public RecentAction getRecentAction(UUID uuid){
        RecentActionCursorWrapper cursor = queryRecentActions(RecentActionTable.Cols.UUID + " = ?", new String[] {uuid.toString()});

        try {
            if (cursor.getCount() == 0)
                return  null;
            cursor.moveToFirst();
            return cursor.getRecentAction();
        } finally {
            cursor.close();
        }
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

    private RecentActionCursorWrapper queryRecentActions(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                RecentActionTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
                );
        return new RecentActionCursorWrapper(cursor);
    }

}
