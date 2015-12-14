package com.totvs.retailapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class PurchaseHistoryAddActivity extends AppRetailActivity {

    EditText editTextPurchaseBarcode;
    EditText editTextPurchaseBarcodeFormat;
    IntentIntegrator integrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_history_add);

        this.activityName = purchaseHistoryAddActivity;

        editTextPurchaseBarcode = (EditText) findViewById(R.id.editTextPurchaseBarcode);
        editTextPurchaseBarcodeFormat = (EditText) findViewById(R.id.editTextPurchaseBarcodeFormat);
        Button buttonPurchaseAddManually = (Button) findViewById(R.id.buttonPurchaseAddManually);
        ImageButton imageView = (ImageButton) findViewById(R.id.imageButtonScanPurchase);

        integrator = new IntentIntegrator(PurchaseHistoryAddActivity.this);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                integrator.initiateScan();
            }
        });

        buttonPurchaseAddManually.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent it = new Intent(PurchaseHistoryAddActivity.this, PurchaseRewardNotifyActivity.class);

            PurchaseHistoryAddActivity.this.startActivity(it);
            }
        });

    }

    public void onActivityResult(int request,int result,Intent i){
        IntentResult scan=IntentIntegrator.parseActivityResult(request,result,i);
        if (scan != null) {
            editTextPurchaseBarcodeFormat.setText(scan.getFormatName());
            editTextPurchaseBarcode.setText(scan.getContents());

            Intent it = new Intent(PurchaseHistoryAddActivity.this, PurchaseRewardNotifyActivity.class);

            PurchaseHistoryAddActivity.this.startActivity(it);
        }
    }

}
