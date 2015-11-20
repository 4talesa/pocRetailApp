package com.totvs.retailapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WalkInAddActivity extends AppRetailActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk_in_add);

        Button buttonOk = (Button) findViewById(R.id.buttonRewardWalkInOk);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(v.getContext(), StoreBrowseActivity.class);

                v.getContext().startActivity(it);
            }
        });
    }
}
