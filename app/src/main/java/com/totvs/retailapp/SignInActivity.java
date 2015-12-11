package com.totvs.retailapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.totvs.retailapp.models.UserModel;

import java.util.ArrayList;
import java.util.List;

public class SignInActivity extends AppCompatActivity {

    TextView textViewsSignInEmail;
    TextView textViewsSignInPassword;
    Button buttonSignIn;
    Button buttonSignUp;
    Button buttonFacebookSignIn;
    ImageButton imageViewFacebookSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ParseUser.logOut();
        ParseUser currentUser = ParseUser.getCurrentUser();

        textViewsSignInEmail = (TextView) findViewById(R.id.textViewsSignInEmail);
        textViewsSignInPassword = (TextView) findViewById(R.id.textViewsSignInPassword);

        textViewsSignInEmail.setText("");
        textViewsSignInPassword.setText("");

        buttonSignIn = (Button) findViewById(R.id.buttonSignInAct);
        buttonSignUp = (Button) findViewById(R.id.buttonOrSignUp);
        buttonFacebookSignIn = (Button) findViewById(R.id.buttonFacebookSignIn);
        imageViewFacebookSignIn = (ImageButton) findViewById(R.id.imageViewFacebookSignIn);

        buttonSignUp.setOnClickListener(getOnSignUpclick());

        buttonSignIn.setOnClickListener(getOnSignInClick());

        buttonFacebookSignIn.setOnClickListener(getOnFacebookClick());

        imageViewFacebookSignIn.setOnClickListener(getOnFacebookClick());

        textViewsSignInPassword.setOnKeyListener(getOnKeyListener());
    }

    @Override
    public void onBackPressed() {

        Intent it = new Intent(SignInActivity.this, WelcomeActivity.class);
        SignInActivity.this.startActivity(it);

        super.onBackPressed();
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
    }

    protected View.OnClickListener getOnFacebookClick(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ParseFacebookUtils.logInWithReadPermissionsInBackground(SignInActivity.this, UserModel.getPermissions(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException err) {
                        if (user == null) {
                            Log.d("MyApp", "Uh oh. The user cancelled the Facebook login.");
                        } else if (user.isNew()) {
                            Log.d("MyApp", "User signed up and logged in through Facebook!");
                            Intent it = new Intent(SignInActivity.this, StoreBrowseActivity.class);

                            SignInActivity.this.startActivity(it);
                        } else {
                            Log.d("MyApp", "User logged in through Facebook!");
                            Intent it = new Intent(SignInActivity.this, StoreBrowseActivity.class);

                            SignInActivity.this.startActivity(it);
                        }
                    }
                });
            }
        };
    }

    protected View.OnClickListener getOnSignInClick(){
        return new View.OnClickListener() {
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
        };
    }

    protected View.OnClickListener getOnSignUpclick(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(v.getContext(), SignUpActivity.class);

                v.getContext().startActivity(it);
            }
        };
    }

    protected View.OnKeyListener getOnKeyListener() {
        return new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press

                    buttonSignIn.performClick();

                    return true;
                }
                return false;
            }
        };
    }

}
