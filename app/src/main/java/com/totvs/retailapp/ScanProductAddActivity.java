package com.totvs.retailapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScanProductAddActivity extends AppRetailActivity {

    EditText editTextScanProductBarcode;
    EditText editTextScanProductBarcodeFormat;
    IntentIntegrator integrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_product_add);

        this.activityName = PURCHASESCANPRODUCTADDACTIVITY;
        editTextScanProductBarcode = (EditText) findViewById(R.id.editTextScanProductBarcode);
        editTextScanProductBarcodeFormat = (EditText) findViewById(R.id.editTextScanProductBarcodeFormat);
        Button buttonScanProductAddManually = (Button) findViewById(R.id.buttonScanProductAddManually);
        ImageButton imageView = (ImageButton) findViewById(R.id.imageButtonScanProduct);

        integrator = new IntentIntegrator(ScanProductAddActivity.this);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                integrator.initiateScan();
            }
        });

        buttonScanProductAddManually.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ScanProductAddActivity.this, ScanProductRewardNotifyActivity.class);

                ScanProductAddActivity.this.startActivity(it);
            }
        });

    }

    public void onActivityResult(int request,int result,Intent i){
        IntentResult scan=IntentIntegrator.parseActivityResult(request,result,i);
        if (scan != null) {
            editTextScanProductBarcodeFormat.setText(scan.getFormatName());
            editTextScanProductBarcode.setText(scan.getContents());

            Intent it = new Intent(ScanProductAddActivity.this, ScanProductRewardNotifyActivity.class);

            ScanProductAddActivity.this.startActivity(it);
        }
    }

    @Override
    public void updateView() {

    }

}
