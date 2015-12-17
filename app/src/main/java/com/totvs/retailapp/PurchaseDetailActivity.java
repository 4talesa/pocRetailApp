package com.totvs.retailapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.totvs.retailapp.adapters.PurchaseItemListViewAdapter;
import com.totvs.retailapp.daos.PurchaseDao;
import com.totvs.retailapp.models.PurchaseItemModel;
import com.totvs.retailapp.models.PurchaseModel;
import com.totvs.retailapp.views.PurchaseView;

import java.util.ArrayList;

public class PurchaseDetailActivity extends AppRetailActivity {

    protected PurchaseView purchaseView;
    protected PurchaseDao purchaseDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_detail);

        this.activityName = PURCHASEDETAILACTIVITY;

        purchaseView = new PurchaseView(this, this.getWindow().getDecorView().getRootView());
        purchaseDao = new PurchaseDao(this, purchaseView);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            if (bundle.containsKey(PurchaseModel.PURCHASE_ID)) {
                Toast.makeText(PurchaseDetailActivity.this, "Purchase selected: " + bundle.getString(PurchaseModel.PURCHASE_ID), Toast.LENGTH_LONG).show();
                purchaseDao.get(bundle.getString(PurchaseModel.PURCHASE_ID));
            }else {
                Toast.makeText(PurchaseDetailActivity.this, "Purchase not selected!", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(PurchaseDetailActivity.this, "Purchase not selected!", Toast.LENGTH_LONG).show();
        }
    }
}
