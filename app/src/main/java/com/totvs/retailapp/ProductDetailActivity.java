package com.totvs.retailapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.totvs.retailapp.adapters.TwoWayViewAdapterModel;
import com.totvs.retailapp.models.ProductModel;

import org.lucasr.twowayview.widget.TwoWayView;

import java.util.ArrayList;

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

        try {
            TwoWayViewAdapterModel adapter = new TwoWayViewAdapterModel(ProductModel.createContactsList(10));
            TwoWayView lvTest = (TwoWayView) findViewById(R.id.listViewRelatedProduct);
            lvTest.setAdapter(adapter);
            //lvTest.setLayoutManager(new LinearLayoutManager(this));
        }catch (Exception e){
            System.out.println("ProductDetailActivity - TwoWayViewAdapterModel - Error: " + e.toString());
        }
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
