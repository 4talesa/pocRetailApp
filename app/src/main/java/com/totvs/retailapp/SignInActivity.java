package com.totvs.retailapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignInActivity extends AppCompatActivity {

    TextView textViewsSignInEmail;
    TextView textViewsSignInPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //Parse.initialize(this, getResources().getString(R.string.app_parse_app_id), getResources().getString(R.string.app_parse_app_key));

        textViewsSignInEmail = (TextView) findViewById(R.id.textViewsSignInEmail);
        textViewsSignInPassword = (TextView) findViewById(R.id.textViewsSignInPassword);

        Button buttonSignIn = (Button) findViewById(R.id.buttonSignInAct);
        Button buttonSignUp = (Button) findViewById(R.id.buttonOrSignUp);
        Button buttonFacebookSignIn = (Button) findViewById(R.id.buttonFacebookSignIn);
        ImageButton imageViewFacebookSignIn = (ImageButton) findViewById(R.id.imageViewFacebookSignIn);

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

                ParseUser.logInInBackground(textViewsSignInEmail.getText().toString(), textViewsSignInPassword.getText().toString(), new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            // Hooray! The user is logged in.
                            Intent it = new Intent(SignInActivity.this, StoreBrowseActivity.class);

                            SignInActivity.this.startActivity(it);
                        } else {
                            // Signup failed. Look at the ParseException to see what happened.
                            Context context = getApplicationContext();
                            CharSequence text = e.toString();
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                    }
                });

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
