package com.totvs.retailapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.totvs.retailapp.models.UserModel;

import java.util.ArrayList;
import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    TextView textViewsSignUpFullName;
    TextView textViewsSignUpEmail;
    TextView textViewsSignUpPassword;
    Button buttonSignIn;
    Button buttonSignUp;
    Button buttonFacebookSignUp;
    ImageButton imageViewFacebookSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ParseUser.logOut();
        ParseUser currentUser = ParseUser.getCurrentUser();

        textViewsSignUpFullName = (TextView) findViewById(R.id.textViewsSignUpFullName);
        textViewsSignUpEmail = (TextView) findViewById(R.id.textViewsSignUpEmail);
        textViewsSignUpPassword = (TextView) findViewById(R.id.textViewsSignUpPassword);

        textViewsSignUpFullName.setText("");
        textViewsSignUpEmail.setText("");
        textViewsSignUpPassword.setText("");

        buttonSignIn = (Button) findViewById(R.id.buttonOrSignIn);
        buttonSignUp = (Button) findViewById(R.id.buttonSignUpAct);
        buttonFacebookSignUp = (Button) findViewById(R.id.buttonFacebookSignUp);
        imageViewFacebookSignUp = (ImageButton) findViewById(R.id.imageViewFacebookSignUp);

        buttonSignIn.setOnClickListener(getOnSignInClick());

        buttonSignUp.setOnClickListener(getOnSignUpClick());

        buttonFacebookSignUp.setOnClickListener(getOnFacebookClick());

        imageViewFacebookSignUp.setOnClickListener(getOnFacebookClick());

        textViewsSignUpPassword.setOnKeyListener(getOnKeyListener());

    }

    @Override
    public void onBackPressed() {

        Intent it = new Intent(SignUpActivity.this, WelcomeActivity.class);
        SignUpActivity.this.startActivity(it);

        super.onBackPressed();
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
    }

    protected View.OnClickListener getOnFacebookClick(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseFacebookUtils.logInWithReadPermissionsInBackground(SignUpActivity.this, UserModel.getPermissions(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException err) {
                        if (user == null) {
                            Log.d("MyApp", "Uh oh. The user cancelled the Facebook login.");
                        } else if (user.isNew()) {
                            Log.d("MyApp", "User signed up and logged in through Facebook!");
                            Intent it = new Intent(SignUpActivity.this, StoreBrowseActivity.class);

                            SignUpActivity.this.startActivity(it);
                        } else {
                            Log.d("MyApp", "User logged in through Facebook!");
                            Intent it = new Intent(SignUpActivity.this, StoreBrowseActivity.class);

                            SignUpActivity.this.startActivity(it);
                        }
                    }
                });
            }
        };
    }

    protected View.OnClickListener getOnSignUpClick(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser user = new ParseUser();
                user.setUsername(textViewsSignUpEmail.getText().toString());
                user.setPassword(textViewsSignUpPassword.getText().toString());
                user.setEmail(textViewsSignUpEmail.getText().toString());

                // other fields can be set just like with ParseObject
                user.put(UserModel.USER_PHONE, "650-555-0000");
                user.put(UserModel.USER_FULL_NAME, textViewsSignUpFullName.getText().toString());

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
        };
    }

    protected View.OnClickListener getOnSignInClick(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(v.getContext(), SignInActivity.class);

                v.getContext().startActivity(it);
            }
        };
    }

    protected View.OnKeyListener getOnKeyListener(){
        return new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press

                    buttonSignUp.performClick();

                    return true;
                }
                return false;
            }
        };
    }

}
