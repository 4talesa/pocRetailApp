package com.totvs.retailapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.totvs.retailapp.adapters.PurchaseItemListViewAdapter;
import com.totvs.retailapp.models.PurchaseItemModel;
import com.totvs.retailapp.models.PurchaseModel;

import java.util.ArrayList;

public class PurchaseDetailActivity extends AppRetailActivity {

    PurchaseItemListViewAdapter purchaseItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_detail);

        this.activityName = purchaseDetailActivity;

        TextView textViewDateValue = (TextView) findViewById(R.id.textViewDateValue);
        textViewDateValue.setText("11/30/2015");

        Button buttonPurchaseDetailAddAllToCart = (Button) findViewById(R.id.buttonPurchaseDetailAddAllToCart);

        ListView listView = (ListView) findViewById(R.id.listViewPurchaseDetail);

        purchaseItemAdapter = new PurchaseItemListViewAdapter(this, R.layout.purchase_detail_item, new ArrayList<PurchaseItemModel>());
        listView.setAdapter(purchaseItemAdapter);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            if (bundle.containsKey(PurchaseModel.PURCHASE_ID)) {
                Toast.makeText(PurchaseDetailActivity.this, "Purchase selected: " + bundle.getString(PurchaseModel.PURCHASE_ID), Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(PurchaseDetailActivity.this, "Purchase not selected!", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(PurchaseDetailActivity.this, "Purchase not selected!", Toast.LENGTH_LONG).show();
        }
    }
}
