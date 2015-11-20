package com.totvs.retailapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button buttonSignIn = (Button) findViewById(R.id.buttonOrSignIn);
        Button buttonSignUp = (Button) findViewById(R.id.buttonSignUpAct);

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(v.getContext(), SignInActivity.class);

                v.getContext().startActivity(it);
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(v.getContext(), StoreBrowseActivity.class);

                v.getContext().startActivity(it);
            }
        });
    }

    @Override
    public void onBackPressed() {

        Intent it = new Intent(SignUpActivity.this, WelcomeActivity.class);
        SignUpActivity.this.startActivity(it);

        super.onBackPressed();
        finish();
    }
}
