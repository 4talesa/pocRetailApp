package com.totvs.retailapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ProductBrowseActivity extends AppRetailActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_browse);

        this.activityName = productBrowseActivity;

        ArrayList<String> items = new ArrayList<String>();
        for (int i = 0; i<10; i++){
            items.add("Product #"+i);
        }

        ArrayAdapter<String> aItems = new ArrayAdapter<String>(this, R.layout.product_related_item, items);
        ListView lvTest = (ListView) findViewById(R.id.listViewProduct);
        lvTest.setAdapter(aItems);
    }
}
