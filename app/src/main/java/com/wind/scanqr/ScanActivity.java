package com.wind.scanqr;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_scan);
        mScannerView = new ZXingScannerView(ScanActivity.this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(ScanActivity.this);
        mScannerView.startCamera();

    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        AlertDialog.Builder  builder =  new AlertDialog.Builder(this);
        builder.setTitle(R.string.scan_title);
        builder.setMessage(result.getText());
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                mScannerView.resumeCameraPreview(ScanActivity.this);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
