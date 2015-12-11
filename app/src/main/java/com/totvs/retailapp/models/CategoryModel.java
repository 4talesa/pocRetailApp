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

    private String id;
    private String description;
    @SerializedName("pictureurl") private String pictureUrl;

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description= description;
    }

    public String getPictureUrl(){
        return this.pictureUrl;
    }

    public void setPictureUrl(String pictureUrl){
        this.pictureUrl = pictureUrl;
    }

    public CategoryModel(String id, String description, String pictureUrl){
        this.id             = id;
        this.description    = description;
        this.pictureUrl = pictureUrl;
    }

    static public CategoryModel fromJson(JSONObject object) {
        Gson gson = new Gson();

        return gson.fromJson(object.toString(), CategoryModel.class);
    }

}
