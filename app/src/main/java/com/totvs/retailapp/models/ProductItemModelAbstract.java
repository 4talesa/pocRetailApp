package com.totvs.retailapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rond.borges on 16/12/2015.
 */
public abstract class ProductItemModelAbstract extends AppRetailModelAbstract {

    @SerializedName("idproduct") protected String idProduct;
    protected String description;
    protected String unit;
    @SerializedName("unitprice") protected Double unitValue;
    @SerializedName("totalitem") protected Double totalItem;
    @SerializedName("pictureurl") protected String pictureUrl;
    @SerializedName("categoryname") protected String category;
    @SerializedName("brandname") protected String brand;
    @SerializedName("amountpurchased") protected Double amountPurchased;
    @SerializedName("amountrequested") protected Double amountRequested;

    public String getIdProduct(){
        return this.idProduct;
    }

    public void setIdProduct(String idProduct){
        this.idProduct = idProduct;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getUnit(){
        return this.unit;
    }

    public void setUnit(String unit){
        this.unit = unit;
    }

    public Double getUnitValue(){
        return this.unitValue;
    }

    public void setUnitValue(Double unitValue){
        this.unitValue = unitValue;
    }

    public Double getTotalItem(){
        return this.totalItem;
    }

    public void setTotalItem(Double totalItem){
        this.totalItem = totalItem;
    }

    public String getPictureUrl() { return this.pictureUrl; }

    public void setPictureUrl(String pictureUrl) { this.pictureUrl = pictureUrl; }

    public String getCategory() { return this.category; }

    public void setCategory( String category ) { this.category = category; }

    public String getBrand() { return this.brand; }

    public void setBrand( String brand ) { this.brand = brand; }

    public Double getAmountPurchased() { return this.amountPurchased; }

    public void setAmountPurchased(Double amountPurchased ) { this.amountPurchased = amountPurchased; }

    public Double getAmountRequested() { return this.amountRequested; }

    public void setAmountRequested(Double amountRequested ) { this.amountRequested = amountRequested; }

    public ProductItemModelAbstract(String id, String idProduct, String description, String unit, Double unitValue, Double totalItem, String pictureUrl, String category, String brand, Double amountPurchased, Double amountRequested){
        this.id             = id;
        this.idProduct      = idProduct;
        this.description    = description;
        this.unit           = unit;
        this.unitValue      = unitValue;
        this.totalItem      = totalItem;
        this.pictureUrl     = pictureUrl;
        this.category       = category;
        this.brand          = brand;
        this.amountPurchased= amountPurchased;
        this.amountRequested= amountRequested;
    }
}
