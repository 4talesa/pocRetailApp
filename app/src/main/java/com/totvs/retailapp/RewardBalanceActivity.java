package com.totvs.retailapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.totvs.retailapp.adapters.RewardTwoWayViewAdapter;
import com.totvs.retailapp.models.RewardModel;

import org.lucasr.twowayview.widget.TwoWayView;

import java.util.ArrayList;
import java.util.List;

public class RewardBalanceActivity extends AppRetailActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward_balance);

        this.activityName = rewardBalanceActivity;

        RewardTwoWayViewAdapter adapter = new RewardTwoWayViewAdapter(new ArrayList<RewardModel>(), R.layout.reward_balance_item, this);
        TwoWayView lvTest = (TwoWayView) findViewById(R.id.twoWayViewPurchaseDetail);
        lvTest.setAdapter(adapter);
    }
}
