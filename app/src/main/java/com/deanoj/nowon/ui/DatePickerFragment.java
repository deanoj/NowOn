package com.deanoj.nowon.ui;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import com.deanoj.nowon.util.ResponseParser;

import java.util.Calendar;

/**
 * Created by deano on 29/01/15.
 */
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    private static final String TAG = "DatePickerFragment";

    private MyDatePickerListener listener;

    public interface MyDatePickerListener {
        public void updateResults();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
        dialog.getDatePicker().setMinDate(c.getTimeInMillis());
        c.add(Calendar.DAY_OF_MONTH, 7);
        dialog.getDatePicker().setMaxDate(c.getTimeInMillis());

        return dialog;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        Log.d(TAG, "date set " + day + "/" + month + "/" + year);
        Log.d(TAG, "" + view.getDayOfMonth());

            ResponseParser parser = ResponseParser.getInstance();

            parser.setStartDate(year, month, day);
            listener.updateResults();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (MyDatePickerListener) activity;

        } catch (ClassCastException castException) {
            /** The activity does not implement the listener. */
        }
    }
}
