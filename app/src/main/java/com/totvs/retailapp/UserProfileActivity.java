package com.totvs.retailapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.koushikdutta.ion.Ion;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserProfileActivity extends AppRetailPictureActivity {

    EditText editTextProfileFullName;
    EditText editTextProfilePhone;
    EditText editTextProfileEmail;
    EditText editTextProfileAddress;
    EditText editTextProfilePassword;
    ImageButton imageButtonProfilePicture;
    Profile currentProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        this.activityName = userProfileActivity;

        editTextProfileFullName = (EditText) findViewById(R.id.editTextProfileFullName);
        editTextProfilePhone = (EditText) findViewById(R.id.editTextProfilePhone);
        editTextProfileEmail = (EditText) findViewById(R.id.editTextProfileEmail);
        editTextProfileAddress = (EditText) findViewById(R.id.editTextProfileAddress);
        editTextProfilePassword = (EditText) findViewById(R.id.editTextProfilePassword);
        imageButtonProfilePicture = (ImageButton) findViewById(R.id.imageButtonProfilePicture);

        imageView = (ImageButton) findViewById(R.id.imageButtonProfilePicture);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent(ACTION_TAKE_PHOTO_B);
            }
        });

        try {
            final ParseUser currentUser = ParseUser.getCurrentUser();

            if (currentUser != null) {
                if (ParseFacebookUtils.isLinked(currentUser)){

                    currentProfile = Profile.getCurrentProfile();

                    // App code
                    editTextProfileFullName.setText(currentProfile.getFirstName() + " " + currentProfile.getLastName());
                    editTextProfilePhone.setText("");
                    editTextProfileEmail.setText("");
                    editTextProfileAddress.setText("");
                    editTextProfilePassword.setText("");

                    Ion.with(imageButtonProfilePicture)
                            .fitCenter()
                            .load(currentProfile.getProfilePictureUri(150, 150).toString());

                }else {
                    editTextProfileFullName.setText(currentUser.getString("fullName"));
                    editTextProfilePhone.setText(currentUser.getString("phone"));
                    editTextProfileEmail.setText(currentUser.getEmail());
                    editTextProfileAddress.setText(currentUser.getString("address"));
                    editTextProfilePassword.setText("");
                }
            }
        }catch (Exception e) {
            Toast.makeText(UserProfileActivity.this, "currentUser: " + e.toString(), Toast.LENGTH_LONG).show();
        }
    }

}
