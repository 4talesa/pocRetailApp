package com.totvs.retailapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        TextView textViewsSignInEmail = (TextView) findViewById(R.id.textViewsSignInEmail);
        TextView textViewsSignInPassword = (TextView) findViewById(R.id.textViewsSignInPassword);

        Button buttonSignIn = (Button) findViewById(R.id.buttonSignInAct);
        Button buttonSignUp = (Button) findViewById(R.id.buttonOrSignUp);
        Button buttonFacebookSignIn = (Button) findViewById(R.id.buttonFacebookSignIn);
        ImageView imageViewFacebookSignIn = (ImageView) findViewById(R.id.imageViewFacebookSignIn);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(v.getContext(), SignUpActivity.class);

                v.getContext().startActivity(it);
            }
        });

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(v.getContext(), StoreBrowseActivity.class);

                v.getContext().startActivity(it);
            }
        });

        buttonFacebookSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(v.getContext(), StoreBrowseActivity.class);

                v.getContext().startActivity(it);
            }
        });

        imageViewFacebookSignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent it = new Intent(v.getContext(), StoreBrowseActivity.class);

                v.getContext().startActivity(it);
            }
        });
    }

    @Override
    public void onBackPressed() {

        Intent it = new Intent(SignInActivity.this, WelcomeActivity.class);
        SignInActivity.this.startActivity(it);

        super.onBackPressed();
        finish();
    }
}
