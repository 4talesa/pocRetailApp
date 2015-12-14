package com.totvs.retailapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.totvs.retailapp.models.PurchaseModel;

public class PurchaseRewardNotifyActivity extends AppRetailActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_reward_notify);

        Button buttonOk = (Button) findViewById(R.id.buttonRewardPurchaseOk);

        buttonOk.setOnClickListener(getOnClickOK());

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            if (bundle.containsKey(PurchaseModel.PURCHASE_ID)) {
                Toast.makeText(PurchaseRewardNotifyActivity.this, "Store selected: " + bundle.getString(PurchaseModel.PURCHASE_ID), Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(PurchaseRewardNotifyActivity.this, "Store not selected!", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(PurchaseRewardNotifyActivity.this, "Store not selected!", Toast.LENGTH_LONG).show();
        }

    }

    public View.OnClickListener getOnClickOK() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(v.getContext(), StoreBrowseActivity.class);

                v.getContext().startActivity(it);
            }
        };
    }
}
