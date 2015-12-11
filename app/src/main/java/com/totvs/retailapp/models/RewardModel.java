package com.totvs.retailapp.models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

/**
 * Created by rond.borges on 02/12/2015.
 */
public class RewardModel extends AppRetailModelAbstract {

    private String id;
    private String name;
    private String description;
    private String status;
    private Double amount;
    @SerializedName("pictureurl") private String pictureUrl;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {return status; }

    public void setStatus(String status) { this.status = status; }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public RewardModel(String id, String name, String description, String status, Double amount, String pictureUrl){
        this.id             = id;
        this.name           = name;
        this.description    = description;
        this.amount         = amount;
        this.pictureUrl = pictureUrl;
        this.status         = status;
    }

    static public RewardModel fromJson(JSONObject object) {
        Gson gson = new Gson();

        return gson.fromJson(object.toString(), RewardModel.class);
    }

}
