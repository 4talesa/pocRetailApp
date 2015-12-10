package com.totvs.retailapp.models;

/**
 * Created by rond.borges on 23/11/2015.
 */
public class PurchaseItemModel extends AppRetailBaseAbstract {

    private String id;
    private String idProduct;
    private String idPurchase;
    private String description;
    private String unit;
    private Double unitValue;
    private Double amount;
    private String urlPicture;
    private String category;
    private String brand;
    private Double quantity;

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getIdProduct(){
        return this.idProduct;
    }

    public void setIdProduct(String idProduct){
        this.idProduct = idProduct;
    }

    public String getIdPurchase(){
        return this.idPurchase;
    }

    public void setIdPurchase(String idPurchase){
        this.idPurchase = idPurchase;
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

    public Double getAmount(){
        return this.amount;
    }

    public void setAmount(Double amount){
        this.amount = amount;
    }

    public Double getTotalValue(){
        return this.getUnitValue() * this.getAmount();
    }

    public String getUrlPicture() { return this.urlPicture; }

    public void setUrlPicture( String urlPicture ) { this.urlPicture = urlPicture; }

    public String getCategory() { return this.category; }

    public void setCategory( String category ) { this.category = category; }

    public String getBrand() { return this.brand; }

    public void setBrand( String brand ) { this.brand = brand; }

    public Double getQuantity() { return this.quantity; }

    public void setQuantity(Double quantity ) { this.quantity = quantity; }

    public PurchaseItemModel(String id, String idProduct, String idPurchase, String description, String unit, Double unitValue, Double amount, String urlPicture, String category, String brand, Double quantity){
        this.id             = id;
        this.idProduct      = idProduct;
        this.idPurchase     = idPurchase;
        this.description    = description;
        this.unit           = unit;
        this.unitValue      = unitValue;
        this.amount         = amount;
        this.urlPicture     = urlPicture;
        this.category       = category;
        this.brand          = brand;
        this.quantity       = quantity;
    }
}
