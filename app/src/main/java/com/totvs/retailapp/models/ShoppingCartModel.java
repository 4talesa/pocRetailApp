package com.totvs.retailapp.models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.Date;

/**
 * Created by rond.borges on 16/12/2015.
 */
public class ShoppingCartModel extends AppRetailModelAbstract {

    static public String SHOPPING_CART_ID = "shoppingCartId";
    static public String SHOPPING_CART = "ShoppingCart";

    private static ShoppingCartModel shoppingCartModel = null;

    private Date date;
    private String idStore;
    private String idUser;
    private String store;
    @SerializedName("storeaddress") private String storeAddress;
    @SerializedName("totalamount") private Double totalAmount;
    @SerializedName("totalquantity") private Double totalQuantity;

    public static synchronized ShoppingCartModel getInstance(){
        if(null == shoppingCartModel){
            shoppingCartModel = new ShoppingCartModel();
        }
        return shoppingCartModel;
    }

    public Date getDate(){
        return this.date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public String getIdStore(){
        return this.idStore;
    }

    public void setIdStore(String idStore){
        this.idStore = idStore;
    }

    public String getIdUser(){
        return this.idUser;
    }

    public void setIdUser(String idUser){
        this.idUser = idUser;
    }

    public String getStore(){
        return this.store;
    }

    public void setStore(String store){
        this.store = store;
    }

    public String getStoreAddress() { return this.storeAddress; }

    public void setStoreAddress(String storeAddress){ this.storeAddress = storeAddress; }

    public Double getTotalAmount() { return this.totalAmount; }

    public void setTotalAmount(Double totalAmount){ this.totalAmount = totalAmount; }

    public Double getTotalQuantity() { return this.totalQuantity; }

    public void setTotalQuantity(Double totalQuantity){ this.totalQuantity = totalQuantity; }

    public ShoppingCartModel(String id, Date date, String idStore, String idUser, String store, String storeAddress, Double totalAmount, Double totalQuantity){
        this.id             = id;
        this.date           = date;
        this.idStore        = idStore;
        this.idUser         = idUser;
        this.store          = store;
        this.storeAddress   = storeAddress;
        this.totalAmount    = totalAmount;
        this.totalQuantity  = totalQuantity;
    }

    private ShoppingCartModel(){

    }

    static public ShoppingCartModel fromJson(JSONObject object) {
        Gson gson = new Gson();

        return gson.fromJson(object.toString(), ShoppingCartModel.class);
    }
}
