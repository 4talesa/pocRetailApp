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

        List<RewardModel> objects = new ArrayList<RewardModel>();

        for (int i = 1; i <= 10; i++) {

            Double rewardAmount = 1 + (Math.random() * 100);

            objects.add(
                    new RewardModel(
                            String.valueOf(i)
                            , getResources().getString(R.string.app_label_reward)+" "+i
                            , getResources().getString(R.string.app_label_reward_description)
                            , (rewardAmount < 50 ? getResources().getString(R.string.app_label_available) : getResources().getString(R.string.app_label_unavailable))
                            , rewardAmount
                            , "http://lorempixel.com/175/175/food/Product/"
                    ));
        }

        RewardTwoWayViewAdapter adapter = new RewardTwoWayViewAdapter(objects, R.layout.reward_balance_item, this);
        TwoWayView lvTest = (TwoWayView) findViewById(R.id.twoWayViewPurchaseDetail);
        lvTest.setAdapter(adapter);
    }
}
