package com.totvs.retailapp.models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

/**
 * Created by rond.borges on 16/12/2015.
 */
public class ShoppingCartItemModel extends ProductItemModelAbstract {

    @SerializedName("idshoppingcart") private String idShoppingCart;

    public String getIdShoppingCart(){
        return this.idShoppingCart;
    }

    public void setIdShoppingCart(String idShoppingCart){
        this.idShoppingCart = idShoppingCart;
    }

    public ShoppingCartItemModel(String id, String idProduct, String idShoppingCart, String description, String unit, Double unitValue, Double amount, String pictureUrl, String category, String brand, Double quantity) {
        super(id, idProduct, description, unit, unitValue, amount, pictureUrl, category, brand, quantity);

        this.idShoppingCart = idShoppingCart;
    }

    static public ShoppingCartItemModel fromJson(JSONObject object) {
        Gson gson = new Gson();

        return gson.fromJson(object.toString(), ShoppingCartItemModel.class);
    }
}
