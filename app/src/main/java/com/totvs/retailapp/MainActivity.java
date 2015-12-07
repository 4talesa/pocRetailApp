package com.totvs.retailapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.estimote.sdk.EstimoteSDK;
import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.facebook.FacebookSdk;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FacebookSdk.sdkInitialize(this);

        Parse.initialize(this, getResources().getString(R.string.app_parse_app_id), getResources().getString(R.string.app_parse_app_key));
        ParseFacebookUtils.initialize(this);

        //  App ID & App Token can be taken from App section of Estimote Cloud.
        EstimoteSDK.initialize(this, getResources().getString(R.string.app_estimote_app_id), getResources().getString(R.string.app_estimote_app_token));
        // Optional, debug logging.
        EstimoteSDK.enableDebugLogging(true);

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
