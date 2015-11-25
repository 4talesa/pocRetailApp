package com.totvs.retailapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.totvs.retailapp.adapters.ProductAdapter;
import com.totvs.retailapp.models.ProductModel;

import java.util.ArrayList;

public class ProductBrowseActivity extends AppRetailActivity {

    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_browse);

        this.activityName = productBrowseActivity;

        ListView listView = (ListView) findViewById(R.id.listViewProduct);

        ArrayList<ProductModel> arrayOfStores = new ArrayList<ProductModel>();
        productAdapter = new ProductAdapter(this, R.layout.product_browse_item, arrayOfStores);
        listView.setAdapter(productAdapter);

        for (int i = 0; i<10; i++){
            arrayOfStores.add(new ProductModel(String.valueOf(i),"product "+i, String.valueOf(i), "http://lorempixel.com/325/175/food/Product/", 1.99, "Category "+i, "each"));
        }
    }
}
