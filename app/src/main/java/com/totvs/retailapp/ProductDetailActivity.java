package com.totvs.retailapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.lucasr.twowayview.TwoWayView;

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

        ArrayList<String> items = new ArrayList<String>();
        for (int i = 0; i<10; i++){
            items.add("Related Product #"+i);
        }

        ArrayAdapter<String> aItems = new ArrayAdapter<String>(this, R.layout.product_related_item, items);
        TwoWayView lvTest = (TwoWayView) findViewById(R.id.listViewStore);
        lvTest.setAdapter(aItems);
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
