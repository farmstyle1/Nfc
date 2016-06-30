package com.wind.scanqr.Fragment;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.wind.scanqr.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by 015240 on 5/27/2016.
 */
public class SpecificFragment extends Fragment implements View.OnClickListener {
    private Button  btnCalendarProvide;
    private CheckBox cbSun,cbMon,cbTue,cbWed,cbThu,cbFri,cbSat;
    private String dateChoose,dateArray;
    private EditText edtStartDate,edtTitle,edtDescription,edtDayCount;
    long startMillis = 0;
    long endMillis = 0;
    public static final String[] EVENT_PROJECTION = new String[]{
            CalendarContract.Calendars._ID,
            CalendarContract.Calendars.ACCOUNT_NAME,
            CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,
            CalendarContract.Calendars.OWNER_ACCOUNT
    };
    private static final int PROJECTION_ID_INDEX = 0;
    private static final int PROJECTION_ACCOUNT_NAME_INDEX = 1;
    private static final int PROJECTION_DISPLAY_NAME_INDEX = 2;
    private static final int PROJECTION_OWNER_ACCOUNT_INDEX = 3;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_specific,container,false);
        Calendar beginTime = Calendar.getInstance();
        startMillis = beginTime.getTimeInMillis();
        endMillis=startMillis+60*60*1000;
        initInstance(rootView);

        return rootView;

    }

    private void initInstance(View rootView) {
        btnCalendarProvide = (Button)rootView.findViewById(R.id.btnCalendarProvide);
        btnCalendarProvide.setOnClickListener(this);
        edtStartDate = (EditText)rootView.findViewById(R.id.edtStartDate);
        edtStartDate.setOnClickListener(this);
        edtTitle = (EditText)rootView.findViewById(R.id.edtTitle);
        edtDescription = (EditText)rootView.findViewById(R.id.edtDescription);
        edtDayCount = (EditText)rootView.findViewById(R.id.edtDayCount);
        cbSun = (CheckBox)rootView.findViewById(R.id.cbSun);
        cbMon = (CheckBox)rootView.findViewById(R.id.cbMon);
        cbTue = (CheckBox)rootView.findViewById(R.id.cbTue);
        cbWed = (CheckBox)rootView.findViewById(R.id.cbWed);
        cbThu = (CheckBox)rootView.findViewById(R.id.cbThu);
        cbFri = (CheckBox)rootView.findViewById(R.id.cbFri);
        cbSat = (CheckBox)rootView.findViewById(R.id.cbSat);

    }


    @Override
    public void onClick(View v) {
       /* if (v == btnCalendarAddEvent) {
            Calendar calendarEvent = Calendar.getInstance();
            Intent i = new Intent(Intent.ACTION_EDIT);
            i.setType("vnd.android.cursor.item/event");
            i.putExtra("beginTime", calendarEvent.getTimeInMillis());
            i.putExtra("allDay", true);
            i.putExtra("rule", "FREQ=YEARLY");
            i.putExtra("endTime", calendarEvent.getTimeInMillis() + 60 * 60 * 1000 * 10);
            i.putExtra("title", "Sample Calender Event Android Application");
            Log.d("check", "date " + calendarEvent.getTimeInMillis());
            startActivity(i);} */
         if (v == btnCalendarProvide) {
            List<String> listDate = new ArrayList<String>();
            if (cbSun.isChecked()) {
                listDate.add("SU");
            }
            if (cbMon.isChecked()) {
                listDate.add("MO");
            }
            if (cbTue.isChecked()) {
                listDate.add("TU");
            }
            if (cbWed.isChecked()) {
                listDate.add("WE");
            }
            if (cbThu.isChecked()) {
                listDate.add("TH");
            }
            if (cbFri.isChecked()) {
                listDate.add("FR");
            }
            if (cbSat.isChecked()) {
                listDate.add("SA");
            }

            String[] stringArrayListDate = listDate.toArray(new String[0]);
            dateArray = String.valueOf(Arrays.toString(stringArrayListDate).substring(1, Arrays.toString(stringArrayListDate).length() - 1));
            dateChoose = dateArray.replaceAll("\\s+", "");
            if (!dateChoose.equals("")) {
                Log.d("check", dateChoose);
                Log.d("check", "Time " + startMillis);
                addEventCalendar(dateChoose);
            } else {
                Log.d("check", "Nulllllll");
            }

        } else if (v == edtStartDate) {
            final View dialogView = View.inflate(getActivity(), R.layout.custom_picker_dialog, null);
            final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();

            dialogView.findViewById(R.id.date_time_set).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    DatePicker datePicker = (DatePicker) dialogView.findViewById(R.id.date_picker);
                    TimePicker timePicker = (TimePicker) dialogView.findViewById(R.id.time_picker);

                    int pickYear, pickMonth, pickDay, pickHour, pickMinute = 0;

                    Calendar calendar = null;
                    if (android.os.Build.VERSION.SDK_INT >= 23) {
                        calendar = new GregorianCalendar(
                                pickYear = datePicker.getYear(),
                                pickMonth = datePicker.getMonth(),
                                pickDay = datePicker.getDayOfMonth(),
                                pickHour = timePicker.getHour(),
                                pickMinute = timePicker.getMinute());
                    } else {
                        calendar = new GregorianCalendar(
                                pickYear = datePicker.getYear(),
                                pickMonth = datePicker.getMonth(),
                                pickDay = datePicker.getDayOfMonth(),
                                pickHour = timePicker.getCurrentHour(),
                                pickMinute = timePicker.getCurrentMinute());
                        Log.i("check", "time " + datePicker.getYear() + " " + datePicker.getMonth() + " " + datePicker.getDayOfMonth());
                    }

                    Calendar timePick = Calendar.getInstance();
                    timePick.set(pickYear, pickMonth, pickDay, pickHour, pickMinute);
                    startMillis = timePick.getTimeInMillis();
                    endMillis = startMillis + 60 * 60 * 1000;
                    alertDialog.dismiss();

                }
            });
            alertDialog.setView(dialogView);
            alertDialog.show();
            //showDatePicker();

        }
    }

    private void calendarProvide() {
        Cursor cur = null;
        ContentResolver cr = getContext().getContentResolver();
        Uri uri = CalendarContract.Calendars.CONTENT_URI;
        String selection = "((" + CalendarContract.Calendars.ACCOUNT_NAME + " = ?) AND ("
                + CalendarContract.Calendars.ACCOUNT_TYPE + " = ?) AND ("
                + CalendarContract.Calendars.OWNER_ACCOUNT + " = ?))";
        String[] selectionArgs = new String[]{"farmstyle1@gmail.com", "com.google",
                "farmstyle1@gmail.com"};

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        cur = cr.query(uri, EVENT_PROJECTION, selection, selectionArgs, null);
        while (cur.moveToNext()) {
            long calID = 0;
            String displayName = null;
            String accountName = null;
            String ownerName = null;

            // Get the field values
            calID = cur.getLong(PROJECTION_ID_INDEX);
            displayName = cur.getString(PROJECTION_DISPLAY_NAME_INDEX);
            accountName = cur.getString(PROJECTION_ACCOUNT_NAME_INDEX);
            Log.d("check", calID + " " + displayName + " " + accountName + " " + ownerName);


        }
    }

    private void addEventCalendar(String dateChoose) {
        long calID = 1;
        ContentResolver cr = getContext().getContentResolver();
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.DTSTART, startMillis);
        values.put(CalendarContract.Events.DTEND, endMillis);
        values.put(CalendarContract.Events.RRULE,"FREQ=DAILY;BYDAY="+dateChoose+";COUNT="+edtDayCount.getText().toString());
        values.put(CalendarContract.Events.TITLE, edtTitle.getText().toString());
        values.put(CalendarContract.Events.DESCRIPTION, edtDescription.getText().toString());
        values.put(CalendarContract.Events.CALENDAR_ID, calID);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, "Thai");
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);

// get the event ID that is the last element in the Uri
        long eventID = Long.parseLong(uri.getLastPathSegment());
        Log.d("check", "Event ID " + eventID);
        getActivity().finish();
    }
}
