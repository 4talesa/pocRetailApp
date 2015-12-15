package com.totvs.retailapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.koushikdutta.ion.Ion;
import com.totvs.retailapp.adapters.ProductTwoWayViewAdapter;
import com.totvs.retailapp.daos.ProductDao;
import com.totvs.retailapp.helpers.HelperJsonArrayRequest;
import com.totvs.retailapp.models.ProductModel;
import com.totvs.retailapp.models.StoreModel;
import com.totvs.retailapp.views.ProductView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.lucasr.twowayview.widget.TwoWayView;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends AppRetailActivity {

    protected ProductView productView;
    protected ProductDao productDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        this.activityName = productDetailActivity;

        productView = new ProductView(this, this.getWindow().getDecorView().getRootView());
        productDao = new ProductDao(this, productView);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            if (bundle.containsKey(ProductModel.PRODUCT_ID)) {
                //Toast.makeText(ProductDetailActivity.this, "Product selected: " + bundle.getString(ProductModel.PRODUCT_ID), Toast.LENGTH_LONG).show();
                productDao.get(bundle.getString(ProductModel.PRODUCT_ID));

            }else {
                Toast.makeText(ProductDetailActivity.this, "Product not selected!", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(ProductDetailActivity.this, "Product not selected!", Toast.LENGTH_LONG).show();
        }

    }

}
