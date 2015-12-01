package com.totvs.retailapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class PurchaseHistoryAddActivity extends AppRetailActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_history_add);

        this.activityName = purchaseHistoryAddActivity;

        Button buttonPurchaseAddManually = (Button) findViewById(R.id.buttonPurchaseAddManually);

    }
}
