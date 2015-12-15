package com.totvs.retailapp.models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

/**
 * Created by rond.borges on 23/11/2015.
 */
public class StoreModel extends AppRetailModelAbstract {

    static public String STORE = "Store";
    static public String STORE_ID = "storeId";
    static public String POSTAL_CODE = "PostalCode";

    private static StoreModel store = null;

    private String name;
    private String address;
    private String distance;
    @SerializedName("pictureurl") private String pictureUrl;
    @SerializedName("postalcode") private String postalCode;

    public static synchronized StoreModel getInstance(){
        if(null == store){
            store = new StoreModel();
        }
        return store;
    }

    public static void setStoreModel(StoreModel model){
        store = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getPostalCode(){
        return this.postalCode;
    }

    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }

    private StoreModel(){

    }

    public StoreModel(String id, String name, String address, String distance, String pictureUrl, String postalCode){
        this.id             = id;
        this.name           = name;
        this.address        = address;
        this.distance       = distance;
        this.pictureUrl     = pictureUrl;
        this.postalCode     = postalCode;
    }

    static public StoreModel fromJson(JSONObject object) {
        Gson gson = new Gson();

        return gson.fromJson(object.toString(), StoreModel.class);
    }

}
