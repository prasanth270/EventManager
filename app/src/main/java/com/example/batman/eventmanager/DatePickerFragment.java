package com.example.batman.eventmanager;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

import dbo.Domain;

/**
 * Created by Batman on 2/21/16.
 */
public class DatePickerFragment extends DialogFragment{

    private String selectedDate;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(getActivity(),
                dateSetListener, year, month, dayOfMonth);
        return dialog;
    }

    /**
     * Date Picker Dialog - OnDateSet Actions
     */
    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Toast.makeText(getContext(), "Select date:" + monthOfYear + "/" + dayOfMonth + "/"
                    + year, Toast.LENGTH_SHORT).show();
            selectedDate = "" + monthOfYear+ "/" + dayOfMonth + "/" + year;
            Log.d("Selected Date : ", selectedDate);
        }
    };

    public String getSelectedDate(){
        return this.selectedDate;
    }
}
