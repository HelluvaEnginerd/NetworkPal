package com.csci448.goldenrush.networkingpal;

/**
 * Created by Hayden on 2/28/17.
 */

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * v much in the style of 'CrimeLab'
 * basically a model for the application data.
 */
public class ApplicationLab {
    private static String TAG = "ApplicationLab";

    private static ApplicationLab sAppLab;
    private List<Application> mApps;

    public static ApplicationLab get(Context context){
        if (sAppLab == null){
            sAppLab = new ApplicationLab(context);
        }
        return sAppLab;
    }

    private ApplicationLab(Context context){
        mApps = new ArrayList<>();
        /**
         * TODO remove fake jobs
         */
        Date date = new Date();
        for (int i = 0; i < 50; i++) {
            String jobString = "Job: " + Integer.toString(i);
            Application app = new Application("App " + Integer.toString(i), jobString, "Contact", date, "Company " + Integer.toString(i));
            mApps.add(app);
        }
    }

    public Application getApplication(){
        for (Application app: mApps){
            //get application by ID
            /**
             * TODO ID the applications (UUID?)
             */
            return null;
        }
        return null;
    }

    public List<Application> getApps() {
        return mApps;
    }
}

