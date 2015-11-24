package com.totvs.retailapp.models;

/**
 * Created by rond.borges on 23/11/2015.
 */
public class StoreModel {

    private String id;
    private String name;
    private String address;
    private String distance;
    private String urlPicture;

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

    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }

    public StoreModel(String id, String name, String address, String distance, String urlPicture){
        this.id             = id;
        this.name           = name;
        this.address        = address;
        this.distance       = distance;
        this.urlPicture     = urlPicture;
    }

}
