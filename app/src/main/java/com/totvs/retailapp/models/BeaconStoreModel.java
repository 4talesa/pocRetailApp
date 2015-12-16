package com.totvs.retailapp.models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

/**
 * Created by rond.borges on 11/12/2015.
 */
public class BeaconStoreModel extends AppRetailModelAbstract {

    static public String BEACONSTORE = "BeaconStore";

    @SerializedName("idstore") private String idStore;
    @SerializedName("namestore") private String nameStore;
    @SerializedName("addressstore") private String addressStore;
    @SerializedName("pictureurlstore") private String pictureUrlStore;

    public String getIdStore(){
        return idStore;
    }

    public void setIdStore(String idStore){
        this.idStore = idStore;
    }

    public String getNameStore() {
        return nameStore;
    }

    public void setNameStore(String nameStore){
        this.nameStore = nameStore;
    }

    public String getAddressStore(){
        return addressStore;
    }

    public void setAddressStore(String addressStore){
        this.addressStore = addressStore;
    }

    public String getPictureUrlStore(){
        return pictureUrlStore;
    }

    public void setPictureUrlStore(String pictureUrlStore){
        this.pictureUrlStore = pictureUrlStore;
    }

    public BeaconStoreModel(String id, String idStore, String nameStore, String addressStore, String pictureUrlStore){
        this.id             = id;
        this.idStore        = idStore;
        this.nameStore      = nameStore;
        this.addressStore   = addressStore;
        this.pictureUrlStore= pictureUrlStore;
    }

    public static BeaconStoreModel fromJson(JSONObject object) {
        Gson gson = new Gson();

        return gson.fromJson(object.toString(), BeaconStoreModel.class);
    }

}
