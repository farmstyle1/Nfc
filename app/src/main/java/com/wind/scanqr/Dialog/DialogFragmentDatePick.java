package com.wind.scanqr.Dialog;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by 015240 on 5/24/2016.
 */
public class DialogFragmentDatePick extends DialogFragment {
    OnDateSetListener ondateSet;
    public DialogFragmentDatePick() {
    }

    public void setCallBack(OnDateSetListener ondate) {
        ondateSet = ondate;
    }

    private int year, month, day;

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        year = args.getInt("year");
        month = args.getInt("month");
        day = args.getInt("day");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new DatePickerDialog(getActivity(), ondateSet, year, month, day);
    }
}
