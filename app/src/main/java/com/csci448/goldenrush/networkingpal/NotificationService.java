package com.csci448.goldenrush.networkingpal;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.SystemClock;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.LayoutInflater;

import java.util.Date;
import java.util.List;

/**
 * Created by Sarah on 5/7/2017.
 */

public class NotificationService extends IntentService {
    private static final int POLL_INTERVAL = 1000*60;//*60*6;

    private static final String TAG = "NotificationService";

    private Date curDate;
    private PendingIntent eventIntent;
    private PendingIntent pi;
    private Intent onTap;

    public static Intent newIntent(Context context){
        return new Intent(context, NotificationService.class);
    }

    @Override
    protected void onHandleIntent(Intent intent){
        Log.i(TAG, "Received and intent: "+intent);

        //clear old pending intents
        if(onTap!=null) {
            PendingIntent.getBroadcast(getApplicationContext(), 0, onTap, PendingIntent.FLAG_UPDATE_CURRENT).cancel();
        }

        //get the current date
        curDate = new Date();
        Log.i(TAG, curDate.toString());

        Resources resources = getResources();

        EventLab eventLab = EventLab.get(getApplicationContext());
        List<Event> events = eventLab.getEvents();

        //loop through events and see if one is coming up in the next day
        for (int j = 0; j<events.size(); j++){
            //Comparing dates
            long difference = Math.abs(events.get(j).getmEventDate().getTime() - curDate.getTime());
            long differenceDates = difference / (60 * 60 * 1000);
            Log.i(TAG, "Event Difference: "+differenceDates);

            //if within one hour - notify
            if(differenceDates<=24){
                Log.i(TAG, "Event found");

                //make intent for notification to go to
                onTap = NewEventActivity.newIntent(getApplicationContext(), events.get(j).getId(), null);
                eventIntent = PendingIntent.getActivity(this, 0, onTap , 0);

                Notification notification = new Notification.Builder(this)
                        .setTicker(resources.getString(R.string.default_notification))
                        .setSmallIcon(R.mipmap.ic_mynetworkpal)
                        .setContentTitle(resources.getString(R.string.default_notification)+ " "+events.get(j).getEventName())
                        .setContentText(resources.getString(R.string.default_details))
                        .setContentIntent(eventIntent)
                        .setAutoCancel(true)
                        .build();

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
                notificationManager.notify(0,notification);

                break;
            }
        }

        ApplicationLab appLab = ApplicationLab.get(getApplicationContext());
        List<Application> applications  = appLab.getApps();

        //loop through applications and see if one is coming up in the next day
        for (int j = 0; j<applications.size(); j++){
            //Comparing dates
            long difference = Math.abs(applications.get(j).getDateDue().getTime() - curDate.getTime());
            long differenceDates = difference / (60 * 60 * 1000);
            Log.i(TAG, "Application Difference: "+differenceDates);

            //if within one hour - notify
            if(differenceDates<=24 && !applications.get(j).isSubmitted()){
                Log.i(TAG, "Application found");
                //make intent for notification to go to
                onTap = NewApplicationActivity.newIntent(getApplicationContext(), applications.get(j).getId(), null);
                pi = PendingIntent.getActivity(this, 0, onTap , 0);

                Notification notification = new Notification.Builder(this)
                        .setTicker(resources.getString(R.string.default_notification))
                        .setSmallIcon(R.mipmap.ic_mynetworkpal)
                        .setContentTitle(resources.getString(R.string.app_notification)+ " "+applications.get(j).getJobTitle())
                        .setContentText("You have an application due with "+applications.get(j).getCompanyName())
                        .setContentIntent(pi)
                        .setAutoCancel(true)
                        .build();

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
                notificationManager.notify(0,notification);

                break;

            }
        }



    }

    public NotificationService(){
        super(TAG);
    }


    public static void setServiceAlarm(Context context, boolean isOn){
        Intent i = NotificationService.newIntent(context);
        PendingIntent pi = PendingIntent.getService(context, 0,i, 0);

        AlarmManager alarmManager= (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if(isOn){
            alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), POLL_INTERVAL, pi);
        } else {
            alarmManager.cancel(pi);
            pi.cancel();
        }
    }

    public static boolean isServiceAlarmOn(Context context){
        Intent i = NotificationService.newIntent(context);
        PendingIntent pi = PendingIntent.getService(context,0,i, PendingIntent.FLAG_NO_CREATE);
        return pi != null;
    }
}
