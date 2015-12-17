package com.totvs.retailapp.models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

/**
 * Created by rond.borges on 16/12/2015.
 */
public class ShoppingCartItemModel extends ProductItemModelAbstract {

    static public String SHOPPING_CART_ITEM = "ShoppingCartItem";

    @SerializedName("idshoppingcart") private String idShoppingCart;
    private String status;

    public String getIdShoppingCart(){
        return idShoppingCart;
    }

    public void setIdShoppingCart(String idShoppingCart){
        this.idShoppingCart = idShoppingCart;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public ShoppingCartItemModel(String id, String idProduct, String idShoppingCart, String description, String unit, Double unitValue, Double amount, String pictureUrl, String category, String brand, Double quantity, String status) {
        super(id, idProduct, description, unit, unitValue, amount, pictureUrl, category, brand, quantity);

        this.idShoppingCart = idShoppingCart;
        this.status = status;
    }

    static public ShoppingCartItemModel fromJson(JSONObject object) {
        Gson gson = new Gson();

        return gson.fromJson(object.toString(), ShoppingCartItemModel.class);
    }
}
