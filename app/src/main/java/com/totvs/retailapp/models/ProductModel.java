package com.totvs.retailapp.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rond.borges on 23/11/2015.
 */
public class ProductModel {

    private String id;
    private String description;
    private String idCategory;
    private String urlPicture;
    private Double value;
    private String category;
    private String unit;

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
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

    public ProductModel(String id, String description, String idCategory, String urlPicture, Double value, String category, String unit){
        this.id             = id;
        this.description    = description;
        this.idCategory     = idCategory;
        this.urlPicture     = urlPicture;
        this.value          = value;
        this.category       = category;
        this.unit           = unit;
    }

}
