package com.totvs.retailapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.totvs.retailapp.adapters.CategoryTwoWayViewAdapter;
import com.totvs.retailapp.models.CategoryModel;

import org.lucasr.twowayview.widget.TwoWayView;

import java.util.ArrayList;
import java.util.List;

public class CategoryBrowseActivity extends AppRetailActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_browse);

        this.activityName = categorytBrowseActivity;

        List<CategoryModel> objects = new ArrayList<CategoryModel>();

        for (int i = 1; i <= 10; i++) {
            objects.add(
                    new CategoryModel(
                            String.valueOf(i)
                            , getResources().getString(R.string.app_label_category)+" "+i
                            , "http://lorempixel.com/175/175/food/Product/"
                    ));
        }

        CategoryTwoWayViewAdapter adapter = new CategoryTwoWayViewAdapter(objects, R.layout.product_category_list, this);
        TwoWayView lvTest = (TwoWayView) findViewById(R.id.twoWayViewBrowseCategory);
        lvTest.setAdapter(adapter);

    }

}
