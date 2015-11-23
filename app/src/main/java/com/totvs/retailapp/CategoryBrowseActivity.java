package com.totvs.retailapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CategoryBrowseActivity extends AppRetailActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_browse);

        this.activityName = categorytBrowseActivity;

        ArrayList<String> items = new ArrayList<String>();
        for (int i = 0; i<10; i++){
            items.add("Category Product #"+i);
        }

        ArrayAdapter<String> aItems = new ArrayAdapter<String>(this, R.layout.product_related_item, items);
        ListView lvTest = (ListView) findViewById(R.id.listViewCategory);
        lvTest.setAdapter(aItems);
    }
}
