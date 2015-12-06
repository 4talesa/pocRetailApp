package com.totvs.retailapp;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.facebook.FacebookSdk;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FacebookSdk.sdkInitialize(this);

        Parse.initialize(this, getResources().getString(R.string.app_parse_app_id), getResources().getString(R.string.app_parse_app_key));
        ParseFacebookUtils.initialize(this);

        try {
            Log.d("KeyHash:", "Started");
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.totvs.retailapp",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.d("KeyHash:", "PackageManager.NameNotFoundException - " + e.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.d("KeyHash:", "NoSuchAlgorithmException - " + e.toString());
        }

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
