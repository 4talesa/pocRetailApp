package com.totvs.retailapp.models;

import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * Created by rond.borges on 11/12/2015.
 */
public class BeaconStoreModel extends AppRetailModelAbstract {

    static public String BEACONSTORE = "BeaconStore";

    private String idStore;

    public String getIdStore(){
        return idStore;
    }

    public void setIdStore(String idStore){
        this.idStore = idStore;
    }

    public BeaconStoreModel(String id, String idStore){
        this.id             = id;
        this.idStore        = idStore;
    }

    static public BeaconStoreModel fromJson(JSONObject object) {
        Gson gson = new Gson();

        return gson.fromJson(object.toString(), BeaconStoreModel.class);
    }

}
