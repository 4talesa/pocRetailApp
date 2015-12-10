package com.totvs.retailapp.models;

/**
 * Created by rond.borges on 23/11/2015.
 */
public class CategoryModel extends AppRetailModelAbstract {

    static public String CATEGORY_ID = "categoryId";
    static public String CATEGORY = "Category";

    private String id;
    private String description;
    private String urlPicture;

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

    public String getUrlPicture(){
        return this.urlPicture;
    }

    public void setUrlPicture(String urlPicture){
        this.urlPicture = urlPicture;
    }

    public CategoryModel(String id, String description, String urlPicture){
        this.id             = id;
        this.description    = description;
        this.urlPicture     = urlPicture;
    }

}
