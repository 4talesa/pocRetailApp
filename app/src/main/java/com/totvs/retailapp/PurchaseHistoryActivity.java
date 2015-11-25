package com.totvs.retailapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class PurchaseHistoryActivity extends AppRetailActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_history);

        this.activityName = purchaseHistoryActivity;

        ArrayList<String> items = new ArrayList<String>();
        for (int i = 0; i<10; i++){
            items.add("Store Sample #"+i);
        }

        ArrayAdapter<String> aItems = new ArrayAdapter<String>(this, R.layout.product_thumb_item, items);
        ListView lvTest = (ListView) findViewById(R.id.listViewPurchaseItem);
        lvTest.setAdapter(aItems);
    }
}
