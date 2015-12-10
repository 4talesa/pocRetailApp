package com.totvs.retailapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.totvs.retailapp.adapters.PurchaseListViewAdapter;
import com.totvs.retailapp.models.PurchaseModel;

import java.util.ArrayList;
import java.util.Date;

public class PurchaseHistoryActivity extends AppRetailActivity {

    PurchaseListViewAdapter purchaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_history);

        this.activityName = purchaseHistoryActivity;

        Button buttonPurchaseHistoryAdd = (Button) findViewById(R.id.buttonPurchaseHistoryAdd);

        ListView listView = (ListView) findViewById(R.id.listViewPurchaseItem);

        purchaseAdapter = new PurchaseListViewAdapter(this, R.layout.purchase_history_item, new ArrayList<PurchaseModel>());
        listView.setAdapter(purchaseAdapter);

    }
}
