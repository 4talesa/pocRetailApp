package com.totvs.retailapp.models;

/**
 * Created by rond.borges on 23/11/2015.
 */
public class PurchaseItemModel {

    private String id;
    private String idProduct;
    private String idPurchase;
    private String description;
    private String unit;
    private Double unitValue;
    private Double amount;

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

    public PurchaseItemModel(String id, String idProduct, String idPurchase, String description, String unit, Double unitValue, Double amount){
        this.id             = id;
        this.idProduct      = idProduct;
        this.idPurchase     = idPurchase;
        this.description    = description;
        this.unit           = unit;
        this.unitValue      = unitValue;
        this.amount         = amount;
    }
}
