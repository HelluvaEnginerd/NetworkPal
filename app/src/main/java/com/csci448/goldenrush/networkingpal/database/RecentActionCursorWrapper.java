package com.csci448.goldenrush.networkingpal.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.csci448.goldenrush.networkingpal.RecentAction;
import com.csci448.goldenrush.networkingpal.database.RecentActionDbSchema.RecentActionTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Hayden on 3/30/17.
 */

public class RecentActionCursorWrapper extends CursorWrapper {

    public RecentActionCursorWrapper(Cursor cursor) {super(cursor);}

    public RecentAction getRecentAction(){
        String uuidString = getString(getColumnIndex(RecentActionTable.Cols.UUID));
        String name = getString(getColumnIndex(RecentActionTable.Cols.NAME));
        String category = getString(getColumnIndex(RecentActionTable.Cols.CATEGORY));
        String company = getString(getColumnIndex(RecentActionTable.Cols.COMPANY));
        String date = getString(getColumnIndex(RecentActionTable.Cols.DATE));

        RecentAction recentAction = new RecentAction(UUID.fromString(uuidString));
        recentAction.setCategory(category);
        recentAction.setCompany(company);
        recentAction.setDate(date);
        recentAction.setName(name);

        return recentAction;
    }
}
