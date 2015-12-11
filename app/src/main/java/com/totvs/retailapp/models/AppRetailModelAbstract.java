package com.totvs.retailapp.models;

/**
 * Created by rond.borges on 09/12/2015.
 */
public abstract class AppRetailModelAbstract {

    protected String id;
    protected String _id;

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String get_id(){
        return this._id;
    }

    public void set_id(String _id){
        this._id = _id;
    }

}
