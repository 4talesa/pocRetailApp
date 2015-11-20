package com.totvs.retailapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent it = new Intent(MainActivity.this, WelcomeActivity.class);

                MainActivity.this.startActivity(it);
            }

        }, 1000L);

        TextView textViewMainTitle = (TextView)findViewById(R.id.textViewMainTitle);

        textViewMainTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(v.getContext(), WelcomeActivity.class);

                v.getContext().startActivity(it);
            }
        });
    }
}
