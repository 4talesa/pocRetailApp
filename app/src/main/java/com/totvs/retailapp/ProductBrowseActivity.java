package com.totvs.retailapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.totvs.retailapp.adapters.ProductListViewAdapter;
import com.totvs.retailapp.adapters.ProductTwoWayViewAdapter;
import com.totvs.retailapp.models.CategoryModel;
import com.totvs.retailapp.models.ProductModel;
import com.totvs.retailapp.models.StoreModel;

import org.lucasr.twowayview.widget.TwoWayView;

import java.util.ArrayList;
import java.util.List;

public class ProductBrowseActivity extends AppRetailActivity {

    private ProductListViewAdapter productAdapter;
    TwoWayView lvTest;
    TextView textViewProductBrowseTitle;
    TextView textViewProductBrowseMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_browse);

        this.activityName = PRODUCTBROWSEACTIVITY;

        lvTest = (TwoWayView) findViewById(R.id.twoWayViewBrowseProduct);
        searchProduct(null);

        textViewProductBrowseTitle = (TextView) findViewById(R.id.textViewProductBrowseTitle);
        textViewProductBrowseMsg = (TextView) findViewById(R.id.textViewProductBrowseMsg);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            if (bundle.containsKey(CategoryModel.CATEGORY_ID)) {
                //Toast.makeText(ProductBrowseActivity.this, "Category selected: " + bundle.getString(CategoryModel.CATEGORY_ID), Toast.LENGTH_LONG).show();
                searchProduct( new String[] {CategoryModel.CATEGORY, bundle.getString(CategoryModel.CATEGORY_ID), StoreModel.getInstance().getId()});
                textViewProductBrowseTitle.setText(this.getResources().getString(R.string.app_label_category)+" #"+bundle.getString(CategoryModel.CATEGORY_ID));
            }else {
                Toast.makeText(ProductBrowseActivity.this, "Category not selected!", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(ProductBrowseActivity.this, "Category not selected!", Toast.LENGTH_LONG).show();
        }

    }

    private void searchProduct(String[] urlFilters){
        ProductTwoWayViewAdapter adapter = new ProductTwoWayViewAdapter(new ArrayList<ProductModel>(), R.layout.product_thumb_item, this, urlFilters);
        lvTest.setAdapter(adapter);
    }
}
