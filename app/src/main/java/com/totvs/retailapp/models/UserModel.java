package com.totvs.retailapp.models;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rond.borges on 07/12/2015.
 */
public class UserModel extends AppRetailModelAbstract {

    static public String USER_FULL_NAME = "fullName";
    static public String USER_PHONE = "phone";
    static public String USER_ADDRESS = "address";

    static public List<String> getPermissions(){

        List<String> permissions;

        permissions = new ArrayList<String>();

        permissions.add("public_profile");
        permissions.add("user_status");
        permissions.add("user_friends");
        permissions.add("email");
        permissions.add("user_about_me");
        permissions.add("user_photos");
        permissions.add("user_location");

        return permissions;
    }

    UserModel(){

    }

    static public UserModel fromJson(JSONObject object) {
        Gson gson = new Gson();

        return gson.fromJson(object.toString(), UserModel.class);
    }

}
