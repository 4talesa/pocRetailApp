package com.totvs.retailapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import org.lucasr.twowayview.TwoWayView;

import java.util.ArrayList;

public class ProductDetailActivity extends AppRetailActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ArrayList<String> items = new ArrayList<String>();
        for (int i = 0; i<10; i++){
            items.add("Item "+i);
        }

        ArrayAdapter<String> aItems = new ArrayAdapter<String>(this, R.layout.product_related_item, items);
        TwoWayView lvTest = (TwoWayView) findViewById(R.id.listViewStore);
        lvTest.setAdapter(aItems);
    }

}
