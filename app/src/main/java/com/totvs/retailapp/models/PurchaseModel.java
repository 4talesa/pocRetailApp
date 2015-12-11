package com.totvs.retailapp.models;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Date;

/**
 * Created by rond.borges on 23/11/2015.
 */
public class PurchaseModel extends AppRetailModelAbstract {

    static public String PURCHASE_ID = "purchaseId";
    static public String PURCHASE = "Purchase";

    private String id;
    private Date date;
    private String idStore;
    private String idUser;
    private String store;
    private String user;
    private String storeAddress;
    private Double totalAmount;
    private Double totalQuantity;

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
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

    public String getUser(){
        return this.user;
    }

    public void setUser(String user){
        this.user = user;
    }

    public String getStoreAddress() { return this.storeAddress; }

    public void setStoreAddress(String storeAddress){ this.storeAddress = storeAddress; }

    public Double getTotalAmount() { return this.totalAmount; }

    public void setTotalAmount(Double totalAmount){ this.totalAmount = totalAmount; }

    public Double getTotalQuantity() { return this.totalQuantity; }

    public void setTotalQuantity(Double totalQuantity){ this.totalQuantity = totalQuantity; }

    public PurchaseModel(String id, Date date, String idStore, String idUser, String store, String user, String storeAddress, Double totalAmount, Double totalQuantity){
        this.id             = id;
        this.date           = date;
        this.idStore        = idStore;
        this.idUser         = idUser;
        this.store          = store;
        this.user           = user;
        this.storeAddress   = storeAddress;
        this.totalAmount    = totalAmount;
        this.totalQuantity  = totalQuantity;
    }

    static public PurchaseModel fromJson(JSONObject object) {
        Gson gson = new Gson();

        return gson.fromJson(object.toString(), PurchaseModel.class);
    }

}
