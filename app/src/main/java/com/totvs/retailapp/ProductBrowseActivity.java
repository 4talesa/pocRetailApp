package com.totvs.retailapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ProductBrowseActivity extends AppRetailActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_browse);

        this.activityName = productBrowseActivity;
    }
}
