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

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    TextView textViewsSignUpFullName;
    TextView textViewsSignUpEmail;
    TextView textViewsSignUpPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Parse.initialize(this, getResources().getString(R.string.app_parse_app_id), getResources().getString(R.string.app_parse_app_key));

        textViewsSignUpFullName = (TextView) findViewById(R.id.textViewsSignUpFullName);
        textViewsSignUpEmail = (TextView) findViewById(R.id.textViewsSignUpEmail);
        textViewsSignUpPassword = (TextView) findViewById(R.id.textViewsSignUpPassword);

        Button buttonSignIn = (Button) findViewById(R.id.buttonOrSignIn);
        Button buttonSignUp = (Button) findViewById(R.id.buttonSignUpAct);
        Button buttonFacebookSignUp = (Button) findViewById(R.id.buttonFacebookSignUp);
        ImageButton imageViewFacebookSignUp = (ImageButton) findViewById(R.id.imageViewFacebookSignUp);

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
                ParseUser user = new ParseUser();
                user.setUsername(textViewsSignUpEmail.getText().toString());
                user.setPassword(textViewsSignUpPassword.getText().toString());
                user.setEmail(textViewsSignUpEmail.getText().toString());

                // other fields can be set just like with ParseObject
                user.put("phone", "650-555-0000");
                user.put("FullName", textViewsSignUpFullName.getText().toString());

                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            // Hooray! Let them use the app now.
                            Intent it = new Intent(SignUpActivity.this, StoreBrowseActivity.class);

                            SignUpActivity.this.startActivity(it);
                        } else {
                            // Sign up didn't succeed. Look at the ParseException
                            // to figure out what went wrong
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

        buttonFacebookSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(v.getContext(), StoreBrowseActivity.class);

                v.getContext().startActivity(it);
            }
        });

        imageViewFacebookSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
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
