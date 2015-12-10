package com.totvs.retailapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.totvs.retailapp.adapters.CategoryTwoWayViewAdapter;
import com.totvs.retailapp.models.CategoryModel;
import com.totvs.retailapp.models.StoreModel;

import org.lucasr.twowayview.widget.TwoWayView;

import java.util.ArrayList;
import java.util.List;

public class CategoryBrowseActivity extends AppRetailActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_browse);

        this.activityName = categorytBrowseActivity;

        CategoryTwoWayViewAdapter adapter = new CategoryTwoWayViewAdapter(new ArrayList<CategoryModel>(), R.layout.product_category_list, this);
        TwoWayView lvTest = (TwoWayView) findViewById(R.id.twoWayViewBrowseCategory);
        lvTest.setAdapter(adapter);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            if (bundle.containsKey(StoreModel.STORE_ID)) {
                Toast.makeText(CategoryBrowseActivity.this, "Store selected: " + bundle.getString(StoreModel.STORE_ID), Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(CategoryBrowseActivity.this, "Store not selected!", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(CategoryBrowseActivity.this, "Store not selected!", Toast.LENGTH_LONG).show();
        }

    }

}
