package com.totvs.retailapp.models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

/**
 * Created by rond.borges on 23/11/2015.
 */
public class CategoryModel extends AppRetailModelAbstract {

    static public String CATEGORY_ID = "categoryId";
    static public String CATEGORY = "Category";

    private String name;
    private String detail;
    @SerializedName("pictureurl") private String pictureUrl;

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPictureUrl(){
        return this.pictureUrl;
    }

    public void setPictureUrl(String pictureUrl){
        this.pictureUrl = pictureUrl;
    }

    public String getDetail(){
        return detail;
    }

    public void setDetail(String detail){
        this.detail = detail;
    }

    public CategoryModel(String id, String name, String pictureUrl, String detail){
        this.id             = id;
        this.name           = name;
        this.pictureUrl     = pictureUrl;
        this.detail         = detail;
    }

    static public CategoryModel fromJson(JSONObject object) {
        Gson gson = new Gson();

        return gson.fromJson(object.toString(), CategoryModel.class);
    }

}
