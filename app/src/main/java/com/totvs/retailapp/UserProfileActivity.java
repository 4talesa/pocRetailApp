package com.totvs.retailapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.koushikdutta.ion.Ion;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.totvs.retailapp.models.UserModel;

import org.json.JSONException;
import org.json.JSONObject;

public class UserProfileActivity extends AppRetailPictureActivity {

    EditText editTextProfileFullName;
    EditText editTextProfilePhone;
    EditText editTextProfileEmail;
    EditText editTextProfileAddress;
    EditText editTextProfilePassword;
    ImageButton imageButtonProfilePicture;
    protected Profile currentProfile;
    protected ParseUser currentUser;
    Switch switchFacebookConnect;
    Boolean loadingProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        this.activityName = userProfileActivity;

        currentUser = ParseUser.getCurrentUser();
        loadingProfile = true;

        editTextProfileFullName = (EditText) findViewById(R.id.editTextProfileFullName);
        editTextProfilePhone = (EditText) findViewById(R.id.editTextProfilePhone);
        editTextProfileEmail = (EditText) findViewById(R.id.editTextProfileEmail);
        editTextProfileAddress = (EditText) findViewById(R.id.editTextProfileAddress);
        editTextProfilePassword = (EditText) findViewById(R.id.editTextProfilePassword);
        imageButtonProfilePicture = (ImageButton) findViewById(R.id.imageButtonProfilePicture);
        switchFacebookConnect = (Switch) findViewById(R.id.switchFacebookConnect);

        imageView = (ImageButton) findViewById(R.id.imageButtonProfilePicture);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent(ACTION_TAKE_PHOTO_B);
            }
        });

        switchFacebookConnect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                currentUser = ParseUser.getCurrentUser();

                if (isChecked) {
                    if (!ParseFacebookUtils.isLinked(currentUser)) {
                        ParseFacebookUtils.linkWithReadPermissionsInBackground(currentUser, UserProfileActivity.this, UserModel.getPermissions(), new SaveCallback() {
                            @Override
                            public void done(ParseException ex) {
                                if (ParseFacebookUtils.isLinked(currentUser)) {
                                    Log.d("RetailApp", "Woohoo, user logged in with Facebook!");
                                }
                            }
                        });
                    }
                } else {
                    if (currentUser.getEmail().length() > 8 && currentUser.getUsername().length() > 8) {
                        ParseFacebookUtils.unlinkInBackground(currentUser, new SaveCallback() {
                            @Override
                            public void done(ParseException ex) {
                                if (ex == null) {
                                    Log.d("RetailApp", "The user is no longer associated with their Facebook account.");
                                }
                            }
                        });
                    } else {
                        Log.d("RetailApp", "The user has no auth on parse, only Facebook account.");
                    }
                }
            }
        });

        try {

            if (currentUser != null) {
                if (ParseFacebookUtils.isLinked(currentUser)){

                    currentProfile = Profile.getCurrentProfile();

                    // App code
                    editTextProfileFullName.setText("wait for facebook api");
                    editTextProfilePhone.setText("");
                    editTextProfileEmail.setText("");
                    editTextProfileAddress.setText("");
                    editTextProfilePassword.setText("");
                    editTextProfilePassword.setEnabled(false);
                    imageView.setEnabled(false);
                    switchFacebookConnect.setChecked(true);
                    switchFacebookConnect.setEnabled(false);
                    editTextProfileEmail.setEnabled(false);
                    editTextProfileFullName.setEnabled(false);

                    GraphRequest request = GraphRequest.newMeRequest(
                            AccessToken.getCurrentAccessToken(),
                            new GraphRequest.GraphJSONObjectCallback() {
                                @Override
                                public void onCompleted(
                                        JSONObject object,
                                        GraphResponse response) {
                                    // Application code
                                    try {
                                        editTextProfileFullName.setText(object.getString("name"));
                                        editTextProfilePhone.setText("");
                                        editTextProfileEmail.setText(object.getString("email"));
                                        JSONObject location = object.getJSONObject("location");
                                        editTextProfileAddress.setText(location.getString("name"));
                                        JSONObject picture = object.getJSONObject("picture");
                                        picture = picture.getJSONObject("data");
                                        Ion.with(imageButtonProfilePicture)
                                                .resize(250, 250)
                                                .load(picture.getString("url"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                    Bundle parameters = new Bundle();
                    parameters.putString("fields", "id,name,link,location,gender,email,picture");
                    request.setParameters(parameters);
                    request.executeAsync();

                }else {
                    editTextProfileFullName.setText(currentUser.getString(UserModel.USER_FULL_NAME));
                    editTextProfilePhone.setText(currentUser.getString(UserModel.USER_PHONE));
                    editTextProfileEmail.setText(currentUser.getEmail());
                    editTextProfileAddress.setText(currentUser.getString(UserModel.USER_ADDRESS));
                    editTextProfilePassword.setText("");
                    editTextProfilePassword.setEnabled(true);
                    imageView.setEnabled(true);
                    switchFacebookConnect.setChecked(false);
                    switchFacebookConnect.setEnabled(true);
                    editTextProfileEmail.setEnabled(false);
                    editTextProfileFullName.setEnabled(true);
                }
            }
        }catch (Exception e) {
            Toast.makeText(UserProfileActivity.this, "currentUser: " + e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {

        // Save the User updates
        if (currentUser != null) {
            if (ParseFacebookUtils.isLinked(currentUser)){
                currentUser.put(UserModel.USER_FULL_NAME, editTextProfileFullName.getText().toString());
                currentUser.put(UserModel.USER_PHONE, editTextProfilePhone.getText().toString());
                currentUser.put(UserModel.USER_ADDRESS, editTextProfileAddress.getText().toString());
                if(editTextProfilePassword.getText().length()>4 && editTextProfilePassword.isEnabled()) {
                    currentUser.setPassword(editTextProfilePassword.getText().toString());
                }
                currentUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {

                    }
                });
            }else{
                currentUser.put(UserModel.USER_FULL_NAME, editTextProfileFullName.getText().toString());
                currentUser.put(UserModel.USER_PHONE, editTextProfilePhone.getText().toString());
                currentUser.put(UserModel.USER_ADDRESS, editTextProfileAddress.getText().toString());
                if(editTextProfilePassword.getText().length()>4 && editTextProfilePassword.isEnabled()) {
                    currentUser.setPassword(editTextProfilePassword.getText().toString());
                }
                currentUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {

                    }
                });
            }
        }

        super.onBackPressed();
        finish();
    }

}
