package com.totvs.retailapp.models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

/**
 * Created by rond.borges on 23/11/2015.
 */
public class ProductModel extends AppRetailModelAbstract {

    static public String PRODUCT_ID = "productId";
    static public String PRODUCT = "Product";

    private String name;
    private String detail;
    @SerializedName("idcategory") private String idCategory;
    @SerializedName("pictureurl") private String pictureUrl;
    private Double value;
    private String category;
    private String unit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail){
        this.detail = detail;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Double getValue(){
        return this.value;
    }

    public void setValue(Double value){
        this.value = value;
    }

    public String getCategory(){
        return this.category;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public String getUnit(){
        return unit;
    }

    public void setUnit(String unit){
        this.unit = unit;
    }

    public ProductModel(String id, String name, String detail, String idCategory, String pictureUrl, Double value, String category, String unit){
        this.id             = id;
        this.name           = name;
        this.detail         = detail;
        this.idCategory     = idCategory;
        this.pictureUrl     = pictureUrl;
        this.value          = value;
        this.category       = category;
        this.unit           = unit;
    }

    static public ProductModel fromJson(JSONObject object) {
        Gson gson = new Gson();

        return gson.fromJson(object.toString(), ProductModel.class);
    }

}
