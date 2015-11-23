package com.totvs.retailapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class StoreBrowseActivity extends AppRetailActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_browse);

        this.activityName = storeBrowseActivity;

        ArrayList<String> items = new ArrayList<String>();
        for (int i = 0; i<10; i++){
            items.add("Store Sample #"+i);
        }

        ArrayAdapter<String> aItems = new ArrayAdapter<String>(this, R.layout.product_related_item, items);
        ListView lvTest = (ListView) findViewById(R.id.listViewStore);
        lvTest.setAdapter(aItems);
    }

}
