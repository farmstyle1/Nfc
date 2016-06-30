package com.wind.scanqr;

import android.Manifest;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract.Events;
import android.provider.CalendarContract.Calendars;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.wind.scanqr.Dialog.DialogFragmentDatePick;
import com.wind.scanqr.Fragment.DurationFragment;
import com.wind.scanqr.Fragment.SpecificFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txtDuration,txtSpecific;
    private int dayOfMonth, monthNum, year;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        if(savedInstanceState==null){

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentMain, new DurationFragment(), "DurationFragment")
                    .commit();

            SpecificFragment specificFragment = new SpecificFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentMain,specificFragment,"SpecificFragment")
                    .detach(specificFragment)
                    .commit();

        }

        initInstance();



    }

    private void initInstance() {

        txtDuration = (TextView)findViewById(R.id.txtDuration);
        txtDuration.setOnClickListener(this);
        txtSpecific = (TextView)findViewById(R.id.txtSpecific);
        txtSpecific.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v==txtDuration){

            DurationFragment durationFragment = (DurationFragment)getSupportFragmentManager().findFragmentByTag("DurationFragment");
            SpecificFragment specificFragment = (SpecificFragment)getSupportFragmentManager().findFragmentByTag("SpecificFragment");
            getSupportFragmentManager().beginTransaction()
                    .detach(specificFragment)
                    .attach(durationFragment)
                    .commit();

        }else if(v==txtSpecific){

            DurationFragment durationFragment = (DurationFragment)getSupportFragmentManager().findFragmentByTag("DurationFragment");
            SpecificFragment specificFragment = (SpecificFragment)getSupportFragmentManager().findFragmentByTag("SpecificFragment");
            getSupportFragmentManager().beginTransaction()
                    .detach(durationFragment)
                    .attach(specificFragment)
                    .commit();
        }


    }

    OnDateSetListener onDateSetListener = new OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Log.d("check", year+" "+monthOfYear+" "+dayOfMonth);
        }
    };

    private void showDatePicker() {
        DialogFragmentDatePick date = new DialogFragmentDatePick();
        Calendar calendar = Calendar.getInstance();
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        monthNum = calendar.get(Calendar.MONTH); // <----- month num
        year = calendar.get(Calendar.YEAR);
        Bundle args = new Bundle();
        args.putInt("year", year);
        args.putInt("month", monthNum);
        args.putInt("day", dayOfMonth);
        date.setArguments(args);
        date.setCallBack(onDateSetListener);
        date.show(getSupportFragmentManager(), "Date Picker          ");
    }








}
