package com.csci448.goldenrush.networkingpal;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.text.Layout;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Sarah on 5/7/2017.
 */

public class TimePickerFragment extends DialogFragment {
    private static final String TAG = "TimePickerFragment";

    private static final String ARG_HOUR = "hour";
    private static final String ARG_MIN = "min";

    private TimePicker mTimePicker;
    private TimeCallbacks mTimeCallbacks;


    public interface TimeCallbacks {
        void onTimeSelected(int hour, int min);
    }

    @Override
    public void onAttach(Activity activity){
        Log.d(TAG, "onAttach()");
        super.onAttach(activity);
        mTimeCallbacks = (TimePickerFragment.TimeCallbacks) activity;
    }
    @Override
    public void onDetach(){
        Log.d(TAG, "onDetach()");
        super.onDetach();
        mTimeCallbacks = null;
    }

    public static TimePickerFragment newInstance(int hour, int min){
        Log.d(TAG, "newInstance()");
        Bundle args = new Bundle();
        args.putSerializable(ARG_HOUR, hour);
        args.putSerializable(ARG_MIN, min);

        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_time, null);

        mTimePicker = (TimePicker) v.findViewById(R.id.dialog_time_picker);

        // Create a new instance of TimePickerDialog and return it
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int hour = mTimePicker.getHour();
                        int min = mTimePicker.getMinute();


                        //sendResult(Activity.RESULT_OK, date);
                        mTimeCallbacks.onTimeSelected(hour, min);
                        dismiss();
                    }
                })
                .create();
    }

    private void sendResult(int resultCode, int hour, int min){
        mTimeCallbacks.onTimeSelected(hour,min);
        dismiss();
    }
}
