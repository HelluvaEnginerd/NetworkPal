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

import java.util.List;

/**
 * Created by Sarah on 5/7/2017.
 */

public class NotificationService extends IntentService {
    private static final int POLL_INTERVAL = 1000*60;//

    private static final String TAG = "NotificationService";


    public static Intent newIntent(Context context){
        return new Intent(context, NotificationService.class);
    }

    @Override
    protected void onHandleIntent(Intent intent){
        Log.i(TAG, "Received and intent: "+intent);

        Resources resources = getResources();
        Intent i = WelcomeActivity.newIntent(this);
        PendingIntent pi = PendingIntent.getActivity(this, 0, i , 0);

        EventLab eventLab = EventLab.get(getApplicationContext());
        List<Event> events = eventLab.getEvents();
        Log.i(events.get(i).getmEventName());

        Notification notification = new Notification.Builder(this)
                .setTicker(resources.getString(R.string.default_notification))
                .setSmallIcon(R.mipmap.ic_mynetworkpal)
                .setContentTitle(resources.getString(R.string.default_notification))
                .setContentText(resources.getString(R.string.default_details))
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(0,notification);



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
