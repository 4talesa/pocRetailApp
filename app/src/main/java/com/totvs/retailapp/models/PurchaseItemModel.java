package com.totvs.retailapp.models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

/**
 * Created by rond.borges on 23/11/2015.
 */
public class PurchaseItemModel extends ProductItemModelAbstract {

    @SerializedName("idpurchase") private String idPurchase;

    public String getIdPurchase(){
        return this.idPurchase;
    }

    public void setIdPurchase(String idPurchase){
        this.idPurchase = idPurchase;
    }

    public PurchaseItemModel(String id, String idProduct, String idPurchase, String description, String unit, Double unitValue, Double totalItem, String pictureUrl, String category, String brand, Double amountPurchased, Double amountRequested){
        super(id, idProduct, description, unit, unitValue, totalItem, pictureUrl, category, brand, amountPurchased, amountRequested);

        this.idPurchase     = idPurchase;
    }

    static public PurchaseItemModel fromJson(JSONObject object) {
        Gson gson = new Gson();

        return gson.fromJson(object.toString(), PurchaseItemModel.class);
    }

}
