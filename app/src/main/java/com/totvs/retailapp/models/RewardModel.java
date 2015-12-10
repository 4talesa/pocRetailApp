package com.totvs.retailapp.models;

/**
 * Created by rond.borges on 02/12/2015.
 */
public class RewardModel extends AppRetailBaseAbstract {

    private String id;
    private String name;
    private String description;
    private String status;
    private Double amount;
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

    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }

    public RewardModel(String id, String name, String description, String status, Double amount, String urlPicture){
        this.id             = id;
        this.name           = name;
        this.description    = description;
        this.amount         = amount;
        this.urlPicture     = urlPicture;
        this.status         = status;
    }
}
