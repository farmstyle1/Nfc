package com.wind.scanqr;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler, View.OnClickListener {
    private Button btnScan,btnNFC,btnCalendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initInstance();

    }

    @Override
    public void handleResult(Result result) {



    }
    public void initInstance(){
        btnNFC = (Button)findViewById(R.id.btnNFC);
        btnNFC.setOnClickListener(this);
        btnScan = (Button)findViewById(R.id.btnScan);
        btnScan.setOnClickListener(this);
        btnCalendar = (Button)findViewById(R.id.btnCalendar);
        btnCalendar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==btnScan){
            Log.d("check","open camera");
            Intent intent = new Intent(this,ScanActivity.class);
            startActivity(intent);
        }else if(v==btnNFC){
            Intent intent = new Intent(this,NfcActivity.class);
            startActivity(intent);
        }else if(v==btnCalendar){
            Intent intent = new Intent(this,CalendarActivity.class);
            startActivity(intent);

        }
    }





}
