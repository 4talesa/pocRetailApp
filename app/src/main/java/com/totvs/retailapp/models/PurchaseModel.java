package com.totvs.retailapp.models;

import java.util.Date;

/**
 * Created by rond.borges on 23/11/2015.
 */
public class PurchaseModel {

    private String id;
    private Date date;
    private String idStore;
    private String idUser;
    private String store;
    private String user;

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

    public PurchaseModel(String id, Date date, String idStore, String idUser, String store, String user){
        this.id         = id;
        this.date       = date;
        this.idStore    = idStore;
        this.idUser     = idUser;
        this.store      = store;
        this.user       = user;
    }
}
