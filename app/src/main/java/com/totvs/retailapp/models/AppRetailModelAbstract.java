package com.totvs.retailapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by rond.borges on 09/12/2015.
 */
public abstract class AppRetailModelAbstract {

    protected String id;
    protected String _id;
    @SerializedName("postdate") protected Date postDate;
    @SerializedName("putdate") protected Date putDate;
    @SerializedName("deletedate") protected Date deleteDate;

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

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Date getPutDate() {
        return putDate;
    }

    public void setPutDate(Date putDate) {
        this.putDate = putDate;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

}
