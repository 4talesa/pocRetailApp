package com.totvs.retailapp.models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

/**
 * Created by rond.borges on 23/11/2015.
 */
public class StoreModel extends AppRetailModelAbstract {

    static public String STORE_ID = "storeId";
    static public String POSTAL_CODE = "PostalCode";

    private String id;
    private String name;
    private String address;
    private String distance;
    @SerializedName("pictureurl") private String pictureUrl;
    @SerializedName("postalcode") private String postalCode;

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
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
