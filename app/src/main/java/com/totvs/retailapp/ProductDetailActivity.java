package com.totvs.retailapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.totvs.retailapp.adapters.ProductTwoWayViewAdapter;
import com.totvs.retailapp.models.ProductModel;

import org.lucasr.twowayview.widget.TwoWayView;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends AppRetailActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        this.activityName = productDetailActivity;

        Button buttonProductDetailMinus = (Button) findViewById(R.id.buttonProductDetailMinus);
        Button buttonProductDetailPlus = (Button) findViewById(R.id.buttonProductDetailPlus);

        buttonProductDetailMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProductAmount(-1);
            }
        });

        buttonProductDetailPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProductAmount(1);
            }
        });

        ImageButton buttonProductDetailArrowLeft = (ImageButton) findViewById(R.id.buttonProductDetailArrowLeft);
        ImageButton buttonProductDetailArrowRight = (ImageButton) findViewById(R.id.buttonProductDetailArrowRight);
        ImageView imageViewProductDetail = (ImageView) findViewById(R.id.imageViewProductDetail);

        List<ProductModel> contacts = new ArrayList<ProductModel>();

        for (int i = 1; i <= 10; i++) {
            contacts.add(new ProductModel(String.valueOf(i),"product "+i, String.valueOf(i), "http://lorempixel.com/175/175/food/Product/", 1.99, "Category "+i, "each"));
        }

        ProductTwoWayViewAdapter adapter = new ProductTwoWayViewAdapter(contacts, R.layout.product_thumb_item, this);
        TwoWayView lvTest = (TwoWayView) findViewById(R.id.twoWayViewRelatedProduct);
        lvTest.setAdapter(adapter);

    }

    private void updateProductAmount(Integer incValue){
        try {
            TextView editTextProductDetailAmount = (TextView) findViewById(R.id.editTextProductDetailAmount);
            Integer currentAmount = Integer.decode(editTextProductDetailAmount.getText().toString());
            currentAmount = currentAmount + incValue;
            if (currentAmount < 0){
                currentAmount = 1;
            }
            editTextProductDetailAmount.setText(currentAmount.toString());
        }catch (Exception e){
            System.out.println("Error - updateProductAmount: " + e.toString());
        }
    }

}
