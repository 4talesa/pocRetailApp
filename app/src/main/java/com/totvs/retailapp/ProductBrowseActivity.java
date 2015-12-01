package com.totvs.retailapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ListView;

import com.totvs.retailapp.adapters.ProductListViewAdapter;
import com.totvs.retailapp.adapters.ProductTwoWayViewAdapter;
import com.totvs.retailapp.models.ProductModel;

import org.lucasr.twowayview.widget.TwoWayView;

import java.util.ArrayList;
import java.util.List;

public class ProductBrowseActivity extends AppRetailActivity {

    private ProductListViewAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_browse);

        this.activityName = productBrowseActivity;

        List<ProductModel> objects = new ArrayList<ProductModel>();

        for (int i = 1; i <= 10; i++) {
            objects.add(
                    new ProductModel(
                            String.valueOf(i)
                            , getResources().getString(R.string.app_label_product)+" "+i
                            , String.valueOf(i), "http://lorempixel.com/175/175/food/Product/"
                            , 1.99
                            , getResources().getString(R.string.app_label_category)+" "+i
                            , getResources().getString(R.string.app_label_product_preview_unit)
                    ));
        }

        ProductTwoWayViewAdapter adapter = new ProductTwoWayViewAdapter(objects, R.layout.product_thumb_item, this);
        TwoWayView lvTest = (TwoWayView) findViewById(R.id.twoWayViewBrowseProduct);
        lvTest.setAdapter(adapter);

    }
}
