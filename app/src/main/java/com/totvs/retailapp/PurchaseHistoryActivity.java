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

        ArrayList<PurchaseModel> objects = new ArrayList<PurchaseModel>();

        purchaseAdapter = new PurchaseListViewAdapter(this, R.layout.purchase_history_item, objects);
        listView.setAdapter(purchaseAdapter);

        for (int i = 0; i<10; i++){
            objects.add(
                    new PurchaseModel(
                            String.valueOf(i)
                            , new Date()
                            , String.valueOf(i)
                            , String.valueOf(i)
                            , getResources().getString(R.string.app_label_store)+" "+i
                            , getResources().getString(R.string.app_label_user)+" "+i
                            , getResources().getString(R.string.app_label_address)+" "+i
                            , 115.75
                            , 11.00
                    ));
        }
    }
}
