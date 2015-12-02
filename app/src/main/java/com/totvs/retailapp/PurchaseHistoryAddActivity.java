package com.totvs.retailapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PurchaseHistoryAddActivity extends AppRetailPictureActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_history_add);

        this.activityName = purchaseHistoryAddActivity;

        Button buttonPurchaseAddManually = (Button) findViewById(R.id.buttonPurchaseAddManually);
        imageView = (ImageButton) findViewById(R.id.imageButtonScanPurchase);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent(ACTION_TAKE_PHOTO_B);
            }
        });

    }

}
