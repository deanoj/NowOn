package com.deanoj.nowon.ui;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TimePicker;

import com.deanoj.nowon.MainActivity;
import com.deanoj.nowon.util.ResponseParser;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    public static final String TAG = "TimePickerFragment";

    private MyTimePickerListener listener;

    public interface MyTimePickerListener {
        public void updateResults();
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        Log.d(TAG, "Set time: hour: " + hourOfDay + " : " + minute);

        ResponseParser parser = ResponseParser.getInstance();

        parser.setStartHour(hourOfDay);
        listener.updateResults();

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (MyTimePickerListener) activity;

        } catch (ClassCastException castException) {
            /** The activity does not implement the listener. */
        }
    }
}